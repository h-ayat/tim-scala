package tim.core.config

import zio.UIO
import zio.macros.accessible
import java.nio.file.Path
import tim.core.UserId

@accessible
object Config {
  trait Service {
    def homePath: UIO[Path]
  }
}
