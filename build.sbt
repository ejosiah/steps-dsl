import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "tagless-eval",
    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.3.3",
      "com.typesafe.akka" %% "akka-http" % "10.1.3",
      "com.typesafe.akka" %% "akka-actor" % "2.5.14",
      "com.typesafe.akka" %% "akka-stream" % "2.5.14"
    ),
    libraryDependencies += scalaTest % Test
  )
