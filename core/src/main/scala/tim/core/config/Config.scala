package tim.core.config

import zio.UIO
import zio.macros.accessible
import java.nio.file.Path

@accessible
object Config {
  trait Service {
    def homePath: UIO[Path]
  }
}
