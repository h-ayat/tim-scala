package tim.core.utils

import zio.IO

trait BasicRepo[E, T] {
  def all: IO[E, List[T]]
  def append(t: T): IO[E, Unit]
  def appendAll(ts: Seq[T]): IO[E, Unit]
  def write(ts: Seq[T]): IO[E, Unit]
}
