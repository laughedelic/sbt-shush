name         := "sbt-shush"
organization := "laughedelic"
description  := "Shush, scalac! You are too noisy!"

sbtPlugin := true
scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "fastparse" % "0.3.7",
  "com.lihaoyi" %% "scalaparse" % "0.3.7",
  compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  "org.scalatest" %% "scalatest" % "2.2.6" % Test
)

dependencyOverrides ++= Set(
  "org.scalamacros" %% "quasiquotes" % "2.0.1"
)

disablePlugins(ResolverSettings)

wartremoverErrors in (Compile, compile) -= Wart.Any
wartremoverErrors in (Test,    compile) -= Wart.Any

// shushFilter := { x => x + "\n FOOOOOO!" }
