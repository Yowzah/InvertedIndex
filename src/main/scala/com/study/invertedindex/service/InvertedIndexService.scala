package com.study.invertedindex.service

import java.io.{FileNotFoundException, PrintWriter}
import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util.UUID

import com.study.invertedindex.model.{Index, Text}
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

@Service
class InvertedIndexService {

  val logger = LoggerFactory.getLogger(classOf[InvertedIndexService])

  @Autowired
  var indexHashMap: mutable.HashMap[String, Index] = new mutable.HashMap[String, Index]()

  def findTexts(words: List[String]): List[Text] = {
    logger.info(s"Find texts by words ${words.toString}")

    readIndexHashMap

    val processedWords = words.map(_.toLowerCase).distinct

    val indexes = findIndex(processedWords)

    if (indexes.isEmpty || indexes == null) {
      List[Text]()
    } else {
      logger.info(s"Found indexes $indexes")
      val textIds: mutable.HashSet[String] = new mutable.HashSet[String]()
      indexes.map(item => textIds ++= item.getTextIds)
      textIds.map(item => Text(readFile(item), item)).filter(_.text != null).toList
    }
  }

  def addText(content: String): Unit = {
    val fileName = UUID.randomUUID() + ".txt";
    val text = Text(content, fileName)

    readIndexHashMap

    try {
      val out = new PrintWriter(".//texts//" + fileName)
      out.println(text.text)
      out.close()
    } catch {
      case e: FileNotFoundException => {
        logger.error("Can not create file: $fileName")
        e.printStackTrace();
      }
    }

    val words: List[String] = text.text.replace("[^a-zA-Z0-9 ]", "").split("\\s+").toList.map(_.toLowerCase).distinct

    for (word <- words) {
      val index = indexHashMap.get(word) match {
        case Some(index) => index
        case _ => Index(word, ListBuffer[String]())
      }
      val textIds = index.getTextIds
      if (!textIds.contains(text.getId)) {
        textIds += text.getId
      }
      indexHashMap.put(index.getWord, index)
      saveIndexHashMap
    }
  }

  private def findIndex(words: List[String]): List[Index] = words.map(item => indexHashMap.get(item)).filter(_.isDefined).map(_.get)

  private def readFile(path: String): String = {
    try {
      new String(Files.readAllBytes(Paths.get(".//texts//" + path)))
    } catch {
      case e: NoSuchFileException => {
        logger.error(s"Path $path not found", e.printStackTrace())
        null
      }
    }
  }

  private def saveIndexHashMap: Unit = {
    val fileName = "indexMap.txt"
    try {
      val out = new PrintWriter(fileName)
      out.flush()
      for (item <- indexHashMap.toList) {
        out.println(s"${item._1}=${item._2.textIds.mkString(",")}")
      }
      out.close()
    } catch {
      case e: FileNotFoundException => {
        logger.error("Can not create file: $fileName")
        e.printStackTrace();
      }
    }
  }

  private def readIndexHashMap: Unit = {
    indexHashMap = new mutable.HashMap[String, Index]()
    try {
      val source = Source.fromFile("indexMap.txt")
      val input = source.getLines()
      for (item <- input) {
        val t = item.split("=")
        val listbuf: ListBuffer[String] = ListBuffer[String]()
        t(1).split(",").toList.foreach(i => listbuf += i)
        indexHashMap += t(0) -> Index(t(0), listbuf)
      }
      source.close()
    } catch {
      case e: FileNotFoundException => logger.info("File not found")
    }

  }
}
