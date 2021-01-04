package tim.core

import zio.Has

package object files {
  type Files = Has[Files.Service]
}
