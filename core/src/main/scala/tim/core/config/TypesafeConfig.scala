package tim.core.config

import com.typesafe.config.ConfigFactory
import zio.ZIO
import java.nio.file.Path
import java.nio.file.Paths

private object TypesafeConfig extends Config.Service {
  private val config = ConfigFactory.load()

  override def homePath: zio.UIO[Path] =
    ZIO.succeed(Paths.get(config.getString("home")))
}
