lazy val circe = new {
  private val group = "io.circe"
  private val version = "0.14.1"

  val core = group %% "circe-core" % version
  val generic = group %% "circe-generic" % version
  val parser = group %% "circe-parser" % version
  /* val literal = group %% "circe-literal" % version */
  /* val genericExtras = group %% "circe-generic-extras" % version */

  val base = core :: generic :: parser :: Nil
}

lazy val zio = new {
  private val group = "dev.zio"
  private val version = "1.0.9"

  val core = group %% "zio" % version

  val test = group %% "zio-test" % version % Test
  val testSbtPlugin = group %% "zio-test-sbt" % version % Test
  val testMagnolia = group %% "zio-test-magnolia" % version % Test

  val testLibs = test :: testSbtPlugin :: testMagnolia :: Nil
  val testFramework = "zio.test.sbt.ZTestFramework"
}

lazy val `commons-text` =
  "org.apache.commons" % "commons-text" % "1.9"

lazy val typesafeConfig = "com.typesafe" % "config" % "1.4.1"

version := "0.0.1"
name := "core"
libraryDependencies ++=
  `commons-text` :: typesafeConfig :: zio.core :: (zio.testLibs ++ circe.base)

testFrameworks += new TestFramework(Dependencies.Zio.testFramework)

