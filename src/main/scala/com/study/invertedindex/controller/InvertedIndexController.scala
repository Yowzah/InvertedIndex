package com.study.invertedindex.controller

import java.util

import com.study.invertedindex.model.Text
import com.study.invertedindex.service.InvertedIndexService
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import collection.JavaConverters._

@RestController
@RequestMapping(path = Array("/api/invertedindex"))
class InvertedIndexController @Autowired()(invertedIndexService: InvertedIndexService) {

  val logger: Logger = LoggerFactory.getLogger(classOf[InvertedIndexController])

  @GetMapping
  def getTexts(@RequestParam words: String): util.List[Text] = {
    logger.info(s"GET request with param: $words")
    invertedIndexService.findTexts(List(words)).asJava
  }

  @PostMapping
  def addText(@RequestBody content: String): Unit = {
    logger.info(s"POST request with body: $content")
    try invertedIndexService.addText(content) catch {
      case e: Exception => {
        logger.error(e.getMessage())
        e.printStackTrace()
      }
    }
  }
}
