package tim.core

import tim.core.Tag
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.extras.semiauto._
import io.circe.generic.extras.defaults._
import zio.IO

sealed trait Serial[T] {
  def serialize(t: T): String
  def deserialize(s: String): IO[Error, T]
}

object Serial {
  private implicit val tagIdEncoder: Codec[TagId] = deriveUnwrappedCodec
  private implicit val conceptIdEncoder: Codec[ConceptId] = deriveUnwrappedCodec

  private def make[T: Decoder: Encoder]: Serial[T] = new Serial[T] {
    override def serialize(t: T): String = t.asJson.noSpaces

    override def deserialize(s: String): zio.IO[Error, T] =
      IO.fromEither(decode[T](s))
  }

  implicit val tag = make[Tag]
  implicit val concept = make[Concept]
  implicit val entry = make[Entry]

  def serialize[T: Serial](t: T): String = implicitly[Serial[T]].serialize(t)
  def deserialize[T: Serial](s: String): zio.IO[Error, T] =
    implicitly[Serial[T]].deserialize(s)
}
