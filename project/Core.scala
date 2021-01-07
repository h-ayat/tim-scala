import sbt.Keys._
import sbt._

object Core {
  lazy val dependencies =
    (Dependencies.typesafeConfig :: Nil) ++ Dependencies.Zio.baseLibs ++ Dependencies.Zio.testLibs ++ Dependencies.Circe.base

  lazy val settings = Common.commonSettings ++ Seq(
    version := "0.0.1",
    name := "core",
    libraryDependencies ++= dependencies,
    testFrameworks += new TestFramework(Dependencies.Zio.testFramework)
  )
}
