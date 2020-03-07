import Dependencies._

val Http4sVersion = "0.21.1"

ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

libraryDependencies ++= Seq(
  "io.kamon" %% "kamon-http4s" % "2.0.1",
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
  "org.http4s" %% "http4s-dsl" % Http4sVersion,
  "io.kamon" %% "kamon-influxdb" % "2.0.0",
  "ch.qos.logback"  %  "logback-classic"     % "1.2.3"
)

lazy val root = (project in file("."))
  .settings(
    name := "http4s-kamon-integration-example",
    libraryDependencies += scalaTest % Test
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings",
)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
