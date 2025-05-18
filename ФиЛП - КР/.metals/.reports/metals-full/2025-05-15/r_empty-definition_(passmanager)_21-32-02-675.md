error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordGenerator.scala:
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordGenerator.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 233
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordGenerator.scala
text:
```scala
// package core

// import scala.util.Random

// object PasswordGenerator {
//   private val LOWER = ('a' to 'z').toList
//   private val UPPER = ('A' to 'Z').toList
//   private val DIGITS = ('0' to '9').toList
//   private val SPEC@@IAL = "!@#$%^&*()".toList

//   def generate(
//       length: Int,
//       includeLower: Boolean,
//       includeUpper: Boolean,
//       includeNumbers: Boolean,
//       includeSpecial: Boolean
//   ): String = {
//     require(includeLower || includeUpper || includeNumbers || includeSpecial,
//             "Должен быть выбран хотя бы один набор символов")

//     val charSets = List(
//       if (includeLower) LOWER else Nil,
//       if (includeUpper) UPPER else Nil,
//       if (includeNumbers) DIGITS else Nil,
//       if (includeSpecial) SPECIAL else Nil
//     )"!@#$%^&*()_+-=[]{}|;:'/\",.<>?".

//     val allChars = charSets.flatten

//     List.fill(length)(allChars(Random.nextInt(allChars.length))).mkString
//   }
// }

package core

import scala.util.Random

object PasswordGenerator {
  private val CHAR_SETS = Seq(
    ('a' to 'z').toSeq,
    ('A' to 'Z').toSeq,
    ('0' to '9').toSeq,
    "!@#$%^&*()".toSeq
  )

  def generate(
      length: Int,
      includeLower: Boolean,
      includeUpper: Boolean,
      includeNumbers: Boolean,
      includeSpecial: Boolean
  ): Option[String] = {
    val enabledSets = Seq(
      includeLower -> 0,
      includeUpper -> 1,
      includeNumbers -> 2,
      includeSpecial -> 3
    ).collect { case (true, index) => CHAR_SETS(index) }

    Option.when(enabledSets.nonEmpty) {
      val allChars = enabledSets.flatten
      val random = new Random()
      (1 to length).map(_ => allChars(random.nextInt(allChars.length))).mkString
    }
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 