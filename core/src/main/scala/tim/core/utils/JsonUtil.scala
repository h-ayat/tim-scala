package tim.core.utils

import spray.json._
import DefaultJsonProtocol._
import tim.core.{
  User,
  Timestamp,
  IssueId,
  TagId,
  Tag,
  IdValue,
  UserId,
  Entry,
  Concept,
  ConceptId
}

object JsonUtil:
  given timestampHandler: RootJsonFormat[Timestamp] = jsonFormat1(
    Timestamp.apply
  )
  given userIdHandler: JsonFormat[UserId] = generateValueClass(UserId.apply)
  given userHandler: RootJsonFormat[User] = jsonFormat3(User.apply)
  given issueIdHandler: JsonFormat[IssueId] = generateValueClass(IssueId.apply)
  given tagIdHandler: JsonFormat[TagId] = generateValueClass(TagId.apply)
  given conceptIdHandler: JsonFormat[ConceptId] = generateValueClass(
    ConceptId.apply
  )
  given tagHandler: RootJsonFormat[Tag] = jsonFormat5(Tag.apply)
  given conceptHandler: RootJsonFormat[Concept] = jsonFormat4(Concept.apply)

  given entryHandler: RootJsonFormat[Entry] =
    val simpleHandler = jsonFormat4(Entry.Simple.apply)
    val eodHandler = jsonFormat1(Entry.EndOfDay.apply)
    new RootJsonFormat[Entry]:
      private val MODEL = "_model"
      private val SIMPLE = "Simple"
      private val EOD = "EndOfDay"
      def read(json: JsValue): Entry =
        val format = StringJsonFormat.read(json.asJsObject.fields(MODEL)) match
          case `SIMPLE` => simpleHandler
          case `EOD`    => eodHandler
        format.read(json)
      def write(obj: Entry): JsValue =
        val (model, value) = obj match
          case s: Entry.Simple =>
            SIMPLE -> simpleHandler.write(s)
          case e: Entry.EndOfDay =>
            EOD -> eodHandler.write(e)
        JsObject(value.asJsObject.fields + (MODEL -> JsString(model)))

  def map[A, B](from: B => A, to: A => B)(using
      format: JsonFormat[B]
  ): JsonFormat[A] = new JsonFormat[A]:
    def read(json: JsValue): A = from(format.read(json))
    def write(obj: A): JsValue = format.write(to(obj))

  def generateValueClass[T <: IdValue](app: String => T): JsonFormat[T] =
    map[T, String](app, _.value)
