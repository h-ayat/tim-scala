package tim.core.files

import zio.macros.accessible
import zio.Task
import java.nio.file.Paths

@accessible
object Files {
  trait Service {
    def readFile(path: String): Task[String]
    def writeFile(path: String, contents: String): Task[Unit]
  }
}

private class NIOFiles extends Files.Service {
  override def readFile(pathString: String): zio.Task[String] = Task {
    import scala.jdk.CollectionConverters._
    val path = Paths.get(pathString)
    java.nio.file.Files.readAllLines(path).asScala.mkString("\n")
  }

  override def writeFile(
      pathString: String,
      contents: String
  ): zio.Task[Unit] = Task {
    val path = Paths.get(pathString)
    java.nio.file.Files.write(path, contents.getBytes)
  }.as(())
}
