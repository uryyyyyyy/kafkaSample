
name := """kafkaSample"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
	"org.apache.kafka" %% "kafka" % "0.8.2.1",
	"log4j" % "log4j" % "1.2.16",
	"com.typesafe" % "config" % "1.2.1",
	"com.typesafe.play" %% "play-json" % "2.4.0-M2",
	"org.scalatest" %% "scalatest" % "2.2.1" % "test",
	"org.twitter4j" % "twitter4j-core" % "4.0.2",
	"org.twitter4j" % "twitter4j-stream" % "4.0.2"
)
