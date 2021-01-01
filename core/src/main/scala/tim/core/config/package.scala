package tim.core

import zio.{Has}
import zio.UIO

package object config {
  type Config = Has[Config.Service]

  object Config {
    trait Service {
      def homePath: UIO[String]
    }
  }
}
