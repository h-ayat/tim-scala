package tim.core.utils

import org.apache.commons.lang3.RandomStringUtils

object TextUtil {

  private val ID_LEN = 8
  private val ID_CHARS = ((0 to 9) ++ ('a' to 'z') ++ ('A' to 'Z')).mkString

  def newId() = RandomStringUtils.random(ID_LEN, ID_CHARS)
}
