import sbt.Keys._
import sbt._

object Common {
  val ORG = ""
  val SCALA_VERSION = "2.13.4"

  val commonSettings = Seq(
    organization := ORG,
    scalaVersion := SCALA_VERSION,
    scalacOptions ++= Seq("-deprecation", "-feature", "-Ymacro-annotations")
  )
}
