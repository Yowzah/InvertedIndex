package com.study.invertedindex.model

import scala.collection.mutable.ListBuffer

case class Index(word: String, textIds: ListBuffer[String]) {

  def getTextIds = textIds

  def getWord = word

  override def toString: String = s"{world = $word, textId = $textIds}"
}

case class Text(text: String, id: String = null) {

  def getId = id

  def getText = text

  override def toString: String = s"{textId = $id}"
}

