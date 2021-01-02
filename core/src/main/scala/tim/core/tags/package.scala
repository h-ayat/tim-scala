package tim.core

import zio.{Has, ZIO, UIO, IO}
import zio.macros.accessible

package object tags {
  type Tags = Has[Tags.Service]

}
