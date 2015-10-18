name := "data_uploader"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.5" % "test",
  "com.typesafe" % "config" % "1.2.1",
  "org.slf4j" % "slf4j-simple" % "1.6.4",
  "org.apache.activemq" % "activemq-all" % "5.5.0"
)

    