package tim.core.config

import zio.UIO
import zio.macros.accessible

@accessible
object Config {
  trait Service {
    def homePath: UIO[String]
  }
}
