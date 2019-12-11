import sbtassembly.MergeStrategy

name := "InvertedIndex"

version := "0.1"

scalaVersion := "2.13.1"

lazy val springVersion = "2.2.0.RELEASE"

mainClass in Compile := Some("com.study.invertedindex.InvertedIndexApplication")

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-web" % springVersion,
  "org.springframework.boot" % "spring-boot-starter" % springVersion
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}