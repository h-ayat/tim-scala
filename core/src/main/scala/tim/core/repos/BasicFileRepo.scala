package tim.core.repos

import tim.core.files.Files
import tim.core.Serial
import tim.core.utils.BasicRepo
import java.nio.file.Path
import zio.ZIO
import zio.IO

class BasicFileRepo[T: Serial](
    files: Files.Service,
    path: Path
) extends BasicRepo[Throwable, T] {

  override def all: zio.IO[Throwable, List[T]] =
    files.readFile(path).flatMap { source =>
      ZIO.collectAll(
        source
          .split("/n")
          .toList
          .map(_.trim)
          .filter(_.nonEmpty)
          .map(Serial.deserialize(_))
      )
    }

  override def append(t: T): zio.IO[Throwable, Unit] = {
    val source = Serial.serialize(t) + "\n"
    files.appendToFile(path, source)
  }

  override def appendAll(ts: Seq[T]): zio.IO[Throwable, Unit] = {
    val source = ts.map(Serial.serialize(_)).mkString("\n") + "\n"
    files.appendToFile(path, source)
  }

  override def write(ts: Seq[T]): zio.IO[Throwable, Unit] = {
    val source = ts.map(Serial.serialize(_)).mkString("\n") + "\n"
    files.writeFile(path, source)
  }

}
