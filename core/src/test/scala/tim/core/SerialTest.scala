package tim.core

import org.scalatest.matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import org.scalatest.flatspec.AnyFlatSpec

class SerialTest
    extends AnyFlatSpec
    with ScalaCheckDrivenPropertyChecks
    with should.Matchers {

  "Serializer" should "Serialize and deserialize Tags" in {
    forAll {
      (
          idString: String,
          label: String,
          conceptIdString: Option[String],
          desc: String,
          deprecated: Boolean
      ) =>
        val id = TagId(idString)
        val conceptId = conceptIdString.map(ConceptId)

        val sourceTag = Tag(id, label, conceptId, desc, deprecated)
        val json = Circe.tag.serialize(sourceTag)
        val test = ??? // TODO Test literal parsing for value classes
        // TODO Test with ZIO
        println(json)
        json.split("\n").length shouldBe 1

    }
  }
}
