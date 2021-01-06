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
    val literal = group %% "circe-literal" % Versions.circe
    val genericExtras = group %% "circe-generic-extras" % Versions.circe

    val base = core :: generic :: parser :: literal :: genericExtras :: Nil
  }

  val typesafeConfig = "com.typesafe" % "config" % "1.4.1"

  object ScalaTest {
    val scalaCheck =
      "org.scalatestplus" %% "scalacheck-1-14" % Versions.ScalaTest.scalaCheck % Test

    val core = "org.scalatest" %% "scalatest" % Versions.ScalaTest.core % Test

    val all = scalaCheck :: core :: Nil
  }
}

object Versions {
  val zio = "1.0.3"
  val circe = "0.13.0"

  object ScalaTest {
    val scalaCheck = "3.2.2.0"
    val core = "3.2.2"
  }

}
