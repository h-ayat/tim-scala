package tim.core.auth

import zio.{ZIO,IO}
import tim.core.User

sealed trait AuthError
case object UnAuthorized extends AuthError

object Auth {
  trait Service {
    val currentUser: IO[AuthError, User]
  }

  val currentUser : ZIO[Auth, AuthError, User] = ??? // TODO IMPL
}
