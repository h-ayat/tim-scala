import sbt.Keys._
import sbt._

object Core {
  lazy val dependencies =
    (Dependencies.typesafeConfig :: Nil) ++ Dependencies.Zio.base ++ Dependencies.Circe.base ++ Dependencies.ScalaTest.all

  lazy val settings = Common.commonSettings ++ Seq(
    version := "0.0.1",
    name := "core",
    libraryDependencies ++= dependencies
  )
}
