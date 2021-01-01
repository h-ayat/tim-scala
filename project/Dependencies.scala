import sbt.Keys._
import sbt._

object Dependencies {

  val zio = new {
    val core = "dev.zio" %% "zio" % Versions.ZIO_VERSION
  }

  val typesafeConfig = "com.typesafe" % "config" % "1.4.1"
}

object Versions {
  val ZIO_VERSION = "1.0.3"
}
