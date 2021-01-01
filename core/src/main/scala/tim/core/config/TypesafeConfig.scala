package tim.core.config

import com.typesafe.config.ConfigFactory
import zio.ZIO

private object TypesafeConfig extends Config.Service {
  private val config = ConfigFactory.load()

  override def homePath: zio.UIO[String] = {
    ZIO.succeed(config.getString("home"))
  }
}
