package com.study.invertedindex.model

import scala.beans.BeanProperty
import scala.collection.mutable.ListBuffer

case class Index(
                  @BeanProperty word: String,
                  @BeanProperty textIds: ListBuffer[String]) {

  override def toString: String = s"{world = $word, textId = $textIds}"
}

case class Text(
                 @BeanProperty text: String,
                 @BeanProperty id: String = null) {

  override def toString: String = s"{textId = $id}"
}

