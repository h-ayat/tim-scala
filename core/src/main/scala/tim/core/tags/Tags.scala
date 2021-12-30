package tim.core.tags

import zio.{Has, ZIO, UIO, IO, URIO}
import tim.core.TagId
import tim.core.Tag
import zio.ZLayer
import tim.core.config.Config
import tim.core.files.Files

object Tags {
  final case class TagNotFound(id: TagId)
  trait Service {
    def add(label: String, description: String): UIO[Unit]
    def get(id: TagId): IO[TagNotFound, Tag]
    def getAll(): UIO[List[Tag]]

    /** Updates the tag
      *
      * @param tag updated version of tag
      * @return [[TagNotFound]] if this tag (specified by id) is not registered yet,
      * [[false]] if the tag is registered, but is the same with the one provided.
      * [[true]] otherwise.
      */
    def update(tag: Tag): IO[TagNotFound, Boolean]

    /** Deprecates given tag, so that it won't appear in search results.
      *
      * @param id The id of target tag
      * @param isDeprecated should be deprecated
      * @return [[TagNotFound]] if the provided id is wrong
      * [[false]] if ID is ok, but the action won't change the state
      * [[true]] otherwise
      */
    def toggleDeprecation(
        id: TagId,
        isDeprecated: Boolean
    ): IO[TagNotFound, Boolean]

  }

  def add(label: String, description: String): URIO[Tags, Unit] = ZIO.accessM[Tags](_.get.add(label, description))
  def get(id: TagId): ZIO[Tags, TagNotFound, Tag] = ZIO.accessM[Tags](_.get.get(id))
  def getAll(): URIO[Tags, List[Tag]] = ZIO.accessM[Tags](_.get.getAll())

  /** Updates the tag
    *
    * @param tag updated version of tag
    * @return [[TagNotFound]] if this tag (specified by id) is not registered yet,
    * [[false]] if the tag is registered, but is the same with the one provided.
    * [[true]] otherwise.
    */
  def update(tag: Tag): ZIO[Tags, TagNotFound, Boolean] = ZIO.accessM[Tags](_.get.update(tag))

  /** Deprecates given tag, so that it won't appear in search results.
    *
    * @param id The id of target tag
    * @param isDeprecated should be deprecated
    * @return [[TagNotFound]] if the provided id is wrong
    * [[false]] if ID is ok, but the action won't change the state
    * [[true]] otherwise
    */
  def toggleDeprecation(
      id: TagId,
      isDeprecated: Boolean
  ): ZIO[Tags, TagNotFound, Boolean] = ZIO.accessM[Tags](_.get.toggleDeprecation(id, isDeprecated))

  val fileBased: ZLayer[Config with Files, Nothing, Tags] =
    ZLayer.fromServices[Config.Service, Files.Service, Tags.Service](
      (config, files) => new FileBasedTags(config, files)
    )
}
