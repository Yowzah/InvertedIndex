package com.study.invertedindex

import com.study.invertedindex.model.Index
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

import scala.collection.mutable

@SpringBootApplication(scanBasePackages = Array("com.study.invertedindex.config", "com.study.invertedindex.controller", "com.study.invertedindex.model", "com.study.invertedindex.service"))
@Configuration
@EnableAutoConfiguration
@ComponentScan
class Config() {
  @Bean
  def indexHashMap(): mutable.HashMap[String, Index] = new mutable.HashMap[String, Index]()
}

object InvertedIndexApplication {
  def main(args: Array[String]): Unit = SpringApplication.run(classOf[Config], args: _ *)
}
