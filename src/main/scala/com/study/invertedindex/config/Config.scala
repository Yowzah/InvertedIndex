package com.study.invertedindex.config

import com.study.invertedindex.model.Index
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.{Bean, Configuration}

import scala.collection.mutable

@Configuration
case class Config() {
  @Bean
  def indexHashMap(): mutable.HashMap[String, Index] = new mutable.HashMap[String, Index]()
}
