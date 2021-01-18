package tim.core.config

import tim.core.UserId
import java.nio.file.Path
import java.util.Date

object FilePathConfig {
  private val tagsName = "tags.json"
  private val conceptsName = "concepts.json"
  private val entriesRoot = "entries"

  def userPath(userId: UserId): zio.URIO[Config, Path] =
    Config.homePath.map(_.resolve(userId.value))

  def tagsPath(userId: UserId): zio.URIO[Config, Path] =
    userPath(userId).map(_.resolve(tagsName))

  def conceptsPath(userId: UserId): zio.URIO[Config, Path] =
    userPath(userId).map(_.resolve(conceptsName))

  def entryPath(userId: UserId, date: Date): zio.URIO[Config, Path] = {

    ???
  }
}
