name := "InvertedIndex"

version := "0.1"

scalaVersion := "2.13.1"

lazy val springVersion = "2.1.8.RELEASE"

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-web" % springVersion,
  "org.springframework.boot" % "spring-boot-starter" % springVersion,
  "org.springframework.boot" % "spring-boot-configuration-processor" % springVersion
)