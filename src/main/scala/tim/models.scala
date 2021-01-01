package tim

final case class Timestamp(value: String) extends AnyVal {
  def parse: (Int, Int, Int) = {
    val array = value.split(":").map(_.toInt)
    (array(0), array(1), array(2))
  }

  def hour: Int = parse._1
  def minute: Int = parse._2
  def second: Int = parse._3
}

final case class ConceptId(value: String) extends AnyVal
final case class Concept(
    id: ConceptId,
    label: String,
    description: String,
    isDeprecated: Boolean
)

final case class TagId(value: String) extends AnyVal
final case class Tag(
    id: TagId,
    label: String,
    concept: Option[ConceptId],
    description: String,
    isDeprecated: Boolean
)

final case class IssueId(value: String) extends AnyVal

sealed trait Entry {
  val start: Timestamp
}

object Entry {
  final case class Simple(
      start: Timestamp,
      tagId: Option[TagId],
      description: String,
      issueId: Option[IssueId]
  ) extends Entry

  final case class EndOfDay(start: Timestamp) extends Entry
}
