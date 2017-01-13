name := "Service2"

version := "1.0"

scalaVersion := "2.12.1"

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray Repository" at "http://repo.spray.io")

//resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

libraryDependencies ++= {
  val akkaVersion = "2.4.16"
  val sprayVersion = "1.3.2"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-camel" % "2.4.16",
  "com.typesafe.akka" %% "akka-cluster" % "2.4.16",
  "com.typesafe.akka" %% "akka-cluster-metrics" % "2.4.16",
  "com.typesafe.akka" %% "akka-cluster-sharding" % "2.4.16",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.4.16",
  "com.typesafe.akka" %% "akka-contrib" % "2.4.16",
  "com.typesafe.akka" %% "akka-multi-node-testkit" % "2.4.16",
  "com.typesafe.akka" %% "akka-osgi" % "2.4.16",
  "com.typesafe.akka" %% "akka-persistence" % "2.4.16",
  "com.typesafe.akka" %% "akka-persistence-tck" % "2.4.16",
  "com.typesafe.akka" %% "akka-remote" % "2.4.16",
  "com.typesafe.akka" %% "akka-slf4j" % "2.4.16",
  "com.typesafe.akka" %% "akka-stream" % "2.4.16",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.4.16",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.16",
  "com.typesafe.akka" %% "akka-distributed-data-experimental" % "2.4.16",
  "com.typesafe.akka" %% "akka-typed-experimental" % "2.4.16",
  "com.typesafe.akka" %% "akka-persistence-query-experimental" % "2.4.16",
  "com.typesafe.akka" %% "akka-http" % "10.0.1",
  "com.typesafe.akka" %% "akka-http-core" % "10.0.1",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.1"


    //"io.spray" %% "spray-can" % sprayVersion,
    //"io.spray" %% "spray-routing" % sprayVersion,
    //"io.spray" %% "spray-json" % "1.3.1",
    //"com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    //"ch.qos.logback" % "logback-classic" % "1.1.2",
    //"com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    //"io.spray" %% "spray-testkit" % sprayVersion % "test",
    //"org.specs2" %% "specs2" % "2.3.13" % "test"
  )
  //"com.typesafe.akka" %% "akka-agent" % "2.4.16"


}
    