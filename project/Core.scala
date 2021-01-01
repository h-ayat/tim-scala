import sbt.Keys._
import sbt._

object Core {
  lazy val settings = Common.commonSettings ++ Seq(
    version := "0.0.1",
    name := "core",
    libraryDependencies ++= Seq(
      Dependencies.zio.core,
      Dependencies.typesafeConfig
    )
  )
}
