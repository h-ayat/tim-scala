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

// TODO better name
final case class ActivityModeId(value: String) extends AnyVal
final case class ActivityMode(
    id: ActivityModeId,
    label: String,
    description: String,
    isDeprecated: Boolean
)

final case class ActivityId(value: String) extends AnyVal
final case class Activity(
    id: ActivityId,
    label: String,
    modeId: ActivityModeId,
    description: String,
    isDeprecated: Boolean
)

sealed trait Record {
  val start: Timestamp
}

object Record {
  final case class Simple(
      start: Timestamp,
      end: Timestamp,
      activityId: Option[String],
      description: String,
      issueId: Option[String]
  ) extends Record

  final case class EndOfDay(start: Timestamp) extends Record
}
