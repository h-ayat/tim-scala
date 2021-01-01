import sbt.Keys._
import sbt._

object Shell {
  lazy val settings = Common.commonSettings ++ Seq(
    version := "0.0.1",
    name := "shell",
    libraryDependencies ++= Seq(
    )
  )
}
