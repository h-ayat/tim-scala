package tim.core.config

import zio.{URIO,UIO}
import java.nio.file.Path
import tim.core.UserId
import zio.ZIO

object Config {
  trait Service {
    val homePath: UIO[Path]
  }
  val homePath : URIO[Config, Path] = ZIO.accessM[Config](_.get.homePath)
  
}
