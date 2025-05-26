error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordGenerator.scala:core/`<import>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordGenerator.scala
empty definition using pc, found symbol in pc: core/`<import>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 18
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordGenerator.scala
text:
```scala
package core

impo@@rt scala.util.Random

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
          val rng = new Random()
          LazyList.continually {
            Vector.fill(length)(allChars(rng.nextInt(allChars.length))).mkString
          }
        case _ => LazyList.empty
      }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: core/`<import>`.