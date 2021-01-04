package tim.core.tags

import tim.core.Tag
import tim.core.TagId
import tim.core.config.Config
import tim.core.files.Files

private class FileTags(config: Config.Service, files: Files.Service)
    extends Tags.Service {

  override def add(label: String, description: String): zio.UIO[Unit] = ???

  override def get(id: TagId): zio.IO[Tags.TagNotFound, Tag] = ???

  override def getAll(): zio.UIO[List[Tag]] = ???

  override def update(tag: Tag): zio.IO[Tags.TagNotFound, Boolean] = ???

  override def toggleDeprecation(
      id: TagId,
      isDeprecated: Boolean
  ): zio.IO[Tags.TagNotFound, Boolean] = ???

}
