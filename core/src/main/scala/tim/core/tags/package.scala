package tim.core

import zio.{Has, ZIO, UIO, IO}

package object tags {
  type Tags = Has[Tags.Service]

}
