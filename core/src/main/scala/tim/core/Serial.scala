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
  private implicit val tagIdCodec: Codec[TagId] = deriveUnwrappedCodec
  private implicit val conceptIdCodec: Codec[ConceptId] = deriveUnwrappedCodec
  private implicit val issueIdCodec: Codec[IssueId] = deriveUnwrappedCodec
  private implicit val userIdCodec: Codec[UserId] = deriveUnwrappedCodec
  private implicit val timestampCodec: Codec[Timestamp] = deriveUnwrappedCodec

  private def make[T: Decoder: Encoder]: Serial[T] = new Serial[T] {
    override def serialize(t: T): String = t.asJson.noSpaces

    override def deserialize(s: String): zio.IO[Error, T] =
      IO.fromEither(decode[T](s))
  }

  def serialize[T: Serial](t: T): String = implicitly[Serial[T]].serialize(t)
  def deserialize[T: Serial](s: String): zio.IO[Error, T] =
    implicitly[Serial[T]].deserialize(s)

  implicit val tag = make[Tag]
  implicit val concept = make[Concept]
  implicit val entry = make[Entry]
  implicit val user = make[User]

}
