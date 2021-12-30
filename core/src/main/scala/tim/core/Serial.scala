package tim.core

import zio.IO

sealed trait Serial[T] {
  def serialize(t: T): String
  def deserialize(s: String): IO[Error, T]
}

object Serial {

  def serialize[T](t: T)(using serial: Serial[T]): String =
    serial.serialize(t)

  def deserialize[T](s: String)(using serial: Serial[T]): zio.IO[Error, T] =
    serial.deserialize(s)


}
