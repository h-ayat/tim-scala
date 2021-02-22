package tim.core.auth

import zio.IO
import tim.core.User
import zio.macros.accessible

sealed trait AuthError
case object UnAuthorized extends AuthError

@accessible
object Auth {
  trait Service {
    val currentUser: IO[AuthError, User]
  }
}
