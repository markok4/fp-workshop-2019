package io.lambdaworks.workshop.functions
import com.lightbend.emoji.ShortCodes.Defaults._
import com.lightbend.emoji.ShortCodes.Implicits._

import scala.util.Try

object EmojifyText {

  def emojify(sentence: String): String = {
    sentence.dropRight(1).split(" ").filterNot(_.isEmpty).map(emojiOrWord).mkString(" ")
  }

  private def emojiOrWord(word: String): String =
    Try(word.toLowerCase.emoji.toString).getOrElse(word)

  private def isLetter(char: Char) = char.isLetter

}
