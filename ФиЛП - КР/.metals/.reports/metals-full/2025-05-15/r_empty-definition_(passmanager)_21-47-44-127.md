error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordList.scala:core/`<import>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordList.scala
empty definition using pc, found symbol in pc: core/`<import>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1223
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordList.scala
text:
```scala
// package core

// import scalafx.beans.property.StringProperty
// import storage.PasswordEntry
// import storage.PasswordStorage
// import scalafx.collections.ObservableBuffer
// import scala.jdk.CollectionConverters._

// object PasswordList {
//   private val _entries: ObservableBuffer[PasswordEntry] = ObservableBuffer.from(
//     PasswordStorage.load().toSeq
//   )

//   def entries: ObservableBuffer[PasswordEntry] = _entries

//   def addEntry(service: String, username: String, password: String): Either[String, Unit] = {
//     if (service.isEmpty) Left("Название сервиса не может быть пустым.")
//     else {
//       _entries += PasswordEntry(
//         StringProperty(service),
//         StringProperty(username),
//         StringProperty(password)
//       )
//       saveToStorage()
//       Right(())
//     }
//   }

//   def deleteEntries(selected: Seq[PasswordEntry]): Option[Unit] = {
//     Option.when(selected.nonEmpty) {
//       _entries --= selected
//       saveToStorage()
//     }
//   }

//   def saveToStorage(): Unit = {
//     PasswordStorage.save(_entries.toSeq)
//   }
// }

package core

import scalafx.beans.property.StringProperty
import storage.{PasswordEntry, PasswordStorage}
@@import scalafx.collections.ObservableBuffer
import scala.jdk.CollectionConverters._

object PasswordList {
  private val _entries: ObservableBuffer[PasswordEntry] = ObservableBuffer.from(
    PasswordStorage.load().toSeq
  )

  def entries: ObservableBuffer[PasswordEntry] = _entries

  def addEntry(
    service: String, 
    username: String, 
    password: String
  ): Either[String, Unit] = {
    Either.cond(
      service.nonEmpty,
      {
        _entries += PasswordEntry(
          StringProperty(service),
          StringProperty(username),
          StringProperty(password)
        )
        saveToStorage()
      },
      "Название сервиса не может быть пустым."
    )
  }

  def deleteEntries(selected: Seq[PasswordEntry]): Option[Unit] = {
    Option.when(selected.nonEmpty) {
      _entries --= selected
      saveToStorage()
    }
  }

  def saveToStorage(): Unit = {
    PasswordStorage.save(_entries.toSeq)
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: core/`<import>`.