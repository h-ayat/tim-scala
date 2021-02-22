package tim.core.config

import tim.core.UserId
import java.nio.file.Path
import java.util.Date
import tim.core.auth.Auth
import zio.ZIO
import tim.core.auth.AuthError

object FilePathConfig {
  private val tagsName = "tags.json"
  private val conceptsName = "concepts.json"
  private val entriesRoot = "entries"

  def userPath: ZIO[Auth with Config, AuthError, Path] =
    for {
      user <- Auth.currentUser
      userId = user.id
      homePath <- Config.homePath
    } yield homePath.resolve(userId.value)

  def tagsPath: ZIO[Auth with Config, AuthError, Path] =
    userPath.map(_.resolve(tagsName))

  def conceptsPath(
      userId: UserId
  ): ZIO[Auth with Config, AuthError, Path] =
    userPath.map(_.resolve(conceptsName))

  def entryPath(
      userId: UserId,
      date: Date
  ): zio.URIO[Auth with Config, Path] = {
    ???
  }
}
