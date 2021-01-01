package tim.core

import zio.{Has, ZIO, UIO, IO}

package object tags {
  type Tags = Has[Tags.Service]

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
  }
}
