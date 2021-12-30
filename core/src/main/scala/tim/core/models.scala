package tim.core


trait IdValue extends Any with Product with Serializable {
  def value: String
}

final case class Timestamp(value: String) extends AnyVal:
  def parse: (Int, Int, Int) =
    val array = value.split(":").map(_.toInt)
    (array(0), array(1), array(2))

  def hour: Int = parse._1
  def minute: Int = parse._2
  def second: Int = parse._3

final case class ConceptId(value: String) extends AnyVal with IdValue
final case class Concept(
    id: ConceptId,
    label: String,
    description: String,
    isDeprecated: Boolean
)

final case class TagId(value: String) extends AnyVal with IdValue
final case class Tag(
    id: TagId,
    label: String,
    concept: Option[ConceptId],
    description: String,
    isDeprecated: Boolean
)

final case class IssueId(value: String) extends AnyVal with IdValue

sealed trait Entry {
  val start: Timestamp
}

object Entry:
  final case class Simple(
      start: Timestamp,
      tagId: Option[TagId],
      description: String,
      issueId: Option[IssueId]
  ) extends Entry
  final case class EndOfDay(start: Timestamp) extends Entry

final case class UserId(value: String) extends AnyVal with IdValue
final case class User(id: UserId, name: String, enabled: String)
