package tim.core

import tim.core.Tag
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
// import io.circe.generic.extras.semiauto._
// import io.circe.generic.extras.defaults._
import zio.IO

sealed trait Serial[T] {
  def serialize(t: T): String
  def deserialize(s: String): IO[Error, T]
}

object Serial {
  // private val tagIdCodec: Codec[TagId] = deriveUnwrappedCodec
  // private val conceptIdCodec: Codec[ConceptId] = deriveUnwrappedCodec
  // private val issueIdCodec: Codec[IssueId] = deriveUnwrappedCodec
  // private val userIdCodec: Codec[UserId] = deriveUnwrappedCodec
  // private val timestampCodec: Codec[Timestamp] = deriveUnwrappedCodec

  private def make[T: Decoder: Encoder]: Serial[T] = new Serial[T] {
    override def serialize(t: T): String = t.asJson.noSpaces

    override def deserialize(s: String): zio.IO[Error, T] =
      IO.fromEither(decode[T](s))
  }

  def serialize[T: Serial](t: T): String = implicitly[Serial[T]].serialize(t)
  def deserialize[T: Serial](s: String): zio.IO[Error, T] =
    implicitly[Serial[T]].deserialize(s)

  // val tag = make[Tag]
  // val concept = make[Concept]
  // val entry = make[Entry]
  // val user = make[User]

}
