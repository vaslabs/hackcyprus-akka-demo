name := "hcs-akka-demo"

version := "0.1"

scalaVersion := "2.12.4"

val akkaVersion = "2.5.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % "10.0.10",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.0.10" % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-cluster" % "2.5.6",
  "com.typesafe.akka" %% "akka-cluster-sharding" % "2.5.6",
  "org.scalatest" %% "scalatest" % "3.0.4" % Test
)
        