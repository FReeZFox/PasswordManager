package core

import scala.util.Random

object PasswordGenerator {
  private val charSets: Seq[Seq[Char]] = Seq(
    ('a' to 'z').toSeq,
    ('A' to 'Z').toSeq,
    ('0' to '9').toSeq,
    "!@#$%^&*()_+-=[]{}|;:'/\",.<>?".toSeq
  )

  def generate(
      length: Int,
      includeLower: Boolean,
      includeUpper: Boolean,
      includeNumbers: Boolean,
      includeSpecial: Boolean
  ): Option[String] = {
    Seq(
      includeLower, 
      includeUpper, 
      includeNumbers, 
      includeSpecial
    )
      .zip(charSets)
      .collect { case (true, chars) => chars }
      .flatten match {
        case Nil => None
        case allChars => 
          val random = new Random()
          Some(
            (1 to length)
            .map(_ => 
              allChars(
                random
                .nextInt(allChars.length)
              )
            ).mkString
          )
      }
  }
}