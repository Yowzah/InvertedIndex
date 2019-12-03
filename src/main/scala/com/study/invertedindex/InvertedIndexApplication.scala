package com.study.invertedindex

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = Array("com.study.invertedindex.config", "com.study.invertedindex.controller", "com.study.invertedindex.model", "com.study.invertedindex.service"))
class InvertedIndexApplication {}

object InvertedIndexApplication {
  def main(args: Array[String]): Unit = SpringApplication.run(classOf[InvertedIndexApplication], args: _ *)
}
