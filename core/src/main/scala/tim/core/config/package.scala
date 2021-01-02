package tim.core

import zio.Has

package object config {
  type Config = Has[Config.Service]
}
