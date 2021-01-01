import sbt.Keys._
import sbt._

object Http {
  lazy val settings = Common.commonSettings ++ Seq(
    version := "0.0.1",
    name := "http",
    libraryDependencies ++= Seq(
    )
  )
}
