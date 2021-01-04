import sbt.Keys._
import sbt._

object Dependencies {

  object Zio {
    private val group = "dev.zio"

    val core = group %% "zio" % Versions.zio
    val macros = group %% "zio-macros" % Versions.zio

    val base = core :: macros :: Nil
  }

  object Circe {
    private val group = "io.circe"

    val core = group %% "circe-core" % Versions.circe
    val generic = group %% "circe-generic" % Versions.circe
    val parser = group %% "circe-parser" % Versions.circe

    val base = core :: generic :: parser :: Nil
  }

  val typesafeConfig = "com.typesafe" % "config" % "1.4.1"
}

object Versions {
  val zio = "1.0.3"
  val circe = "0.13.0"
}
