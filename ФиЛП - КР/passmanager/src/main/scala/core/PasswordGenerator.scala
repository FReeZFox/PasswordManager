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
  ): LazyList[String] = {
    Seq(
      includeLower,
      includeUpper,
      includeNumbers,
      includeSpecial
    ).zip(charSets)
      .collect { case (true, chars) => chars }
      .flatten
      .toVector match {
        case allChars if allChars.nonEmpty && length > 0 =>
          val random = new Random()

          LazyList
          .continually {
            Vector
            .fill(length)(
              allChars(
                random
                .nextInt(
                  allChars
                  .length)
                )
            ).mkString
          }
          
        case _ => LazyList.empty
      }
  }
}
