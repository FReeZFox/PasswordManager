error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordGenerator.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordGenerator.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 703
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordGenerator.scala
text:
```scala
package core

import scala.util.Random
import java.nio.file.{Files, Paths, StandardOpenOption}
import scala.annotation.tailrec

object PasswordGenerator {
  private val LOWER = ('a' to 'z').toList
  private val UPPER = ('A' to 'Z').toList
  private val DIGITS = ('0' to '9').toList
  private val SPECIAL = "!@#$%^&*()".toList

  def generate(length: Int, 
              includeLower: Boolean, 
              includeUpper: Boolean,
              includeNumbers: Boolean, 
              includeSpecial: Boolean): String = {
    
    // Проверка, что хотя бы один чекбокс активен
    require(includeLower || includeUpper || includeNumbers || includeSpecial, 
           "Должен быть выбран хотя бы один наб@@ор символов")
    
    val charSets = List(
      if (includeLower) LOWER else Nil,
      if (includeUpper) UPPER else Nil,
      if (includeNumbers) DIGITS else Nil,
      if (includeSpecial) SPECIAL else Nil
    )
    
    val allChars = charSets.flatten

    @tailrec
    def generateRec(remaining: Int, acc: List[Char]): String = {
      if (remaining <= 0) acc.mkString
      else generateRec(remaining - 1, allChars(Random.nextInt(allChars.length)) :: acc)
    }

    generateRec(length, Nil)
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.