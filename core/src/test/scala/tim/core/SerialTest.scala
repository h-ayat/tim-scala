package tim.core

import zio.test.DefaultRunnableSpec

import zio.test._
import zio.test.Assertion._
import zio.test.environment._

import zio.random.Random
import zio.test.magnolia._
import tim.core.Serial
import zio.IO

// import io.circe._, io.circe.parser._

// object SerialTest extends DefaultRunnableSpec {

//   private val genTag: Gen[Random with Sized, Tag] = DeriveGen[Tag]

//   private def tagMirroringTest(tag: Tag): IO[_, TestResult] = {
//     val jsonString = Serial.tag.serialize(tag)
//     Serial.tag.deserialize(jsonString).map { newTag =>
//       assert(newTag)(equalTo(tag))
//     }
//   }

//   private def conceptMirroringTest(concept: Concept): IO[_, TestResult] = {
//     val json = Serial.concept.serialize(concept)
//     Serial.concept.deserialize(json).map { newConcept =>
//       assert(newConcept)(equalTo(concept))
//     }
//   }

//   private def conceptIdInTagValueClassTest(tag: Tag): TestResult = {
//     val jsonString = Serial.tag.serialize(tag)
//     assert(
//       parse(Serial.tag.serialize(tag)).map {
//         _.asObject.map {
//           _("concept").map(
//             _.asString
//           )
//         }
//       }
//     )(
//       isRight(
//         isSome(
//           isSome(
//             equalTo(tag.concept.map(_.value))
//           )
//         )
//       )
//     )
//   }

//   private def tagIdInTagValueClassTest(tag: Tag): TestResult = {
//     val jsonString = Serial.tag.serialize(tag)
//     assert(
//       parse(Serial.tag.serialize(tag)).map {
//         _.asObject.map {
//           _("id").map(
//             _.asString
//           )
//         }
//       }
//     )(isRight(isSome(isSome(isSome(equalTo(tag.id.value))))))
//   }

//   override def spec = suite("tag serialSpec")(
//     testM("Serialization and Deserialization")(
//       checkM(genTag)(tagMirroringTest)
//     ),
//     testM("tagId valueClass")(check(genTag)(tagIdInTagValueClassTest)),
//     testM("conceptId valueClass")(check(genTag)(conceptIdInTagValueClassTest))
//   )
// }
