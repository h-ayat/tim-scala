package tim.core.files

import zio.{ZIO,Task}
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.nio.file.Path

object Files {
  trait Service {
    def readFile(path: Path): Task[String]
    def writeFile(path: Path, contents: String): Task[Unit]
    def appendToFile(path: Path, contents: String): Task[Unit]
  }

  def readFile(path: Path): ZIO[Files,Throwable,String] = ???
  def writeFile(path: Path, contents: String): ZIO[Files, Throwable, Unit] = ???
  def appendToFile(path: Path, contents: String): ZIO[Files, Throwable, Unit] = ???
}

private class NIOFiles extends Files.Service {
  override def readFile(path: Path): zio.Task[String] = Task {
    import scala.jdk.CollectionConverters._
    java.nio.file.Files.readAllLines(path).asScala.mkString("\n")
  }

  override def writeFile(
      path: Path,
      contents: String
  ): zio.Task[Unit] = Task {
    java.nio.file.Files.write(
      path,
      contents.getBytes,
      StandardOpenOption.CREATE,
      StandardOpenOption.TRUNCATE_EXISTING
    )
  }.as(())

  override def appendToFile(
      path: Path,
      contents: String
  ): zio.Task[Unit] =
    Task {
      java.nio.file.Files.write(
        path,
        contents.getBytes,
        StandardOpenOption.APPEND,
        StandardOpenOption.CREATE
      )
    }.as(())
}
