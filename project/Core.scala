import sbt.Keys._
import sbt._

object Core {
  lazy val dependencies = Seq(
    Dependencies.typesafeConfig
  ) ++ Dependencies.Zio.base ++ Dependencies.Circe.base

  lazy val settings = Common.commonSettings ++ Seq(
    version := "0.0.1",
    name := "core",
    libraryDependencies ++= dependencies
  )
}
