name := "Project2"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatest" %% "scalatest" % "3.0.3" % "test",
  "org.apache.kafka" %% "kafka" % "1.1.1",
  "net.manub" %% "scalatest-embedded-kafka" % "1.1.1" % Test
)
