package tim

final case class ActivityMode(id: String, label: String, description: String, isDeprecated: Boolean)

final case class Activity(id: String, label: String, modeId: String, description: String, isDeprecated: Boolean)

sealed trait Record{
  val start: Long
}
object Record {
final case class Simple(start: Long, end: Long, activityId: Option[String], description: String, issueId: Option[String])
}

