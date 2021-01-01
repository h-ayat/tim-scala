import sbt.Keys._
import sbt._

object Cli {
  lazy val settings = Common.commonSettings ++ Seq(
    version := "0.0.1",
    name := "cli",
    libraryDependencies ++= Seq(
    )
  )
}
