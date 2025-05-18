error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordList.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordList.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 559
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordList.scala
text:
```scala
package core

import scalafx.beans.property.StringProperty
import storage.PasswordEntry
import storage.PasswordStorage
import scalafx.collections.ObservableBuffer
import scala.jdk.CollectionConverters._

object PasswordList {
  private val _entries: ObservableBuffer[PasswordEntry] = ObservableBuffer.from(
    PasswordStorage.load().toSeq
  )

  def entries: ObservableBuffer[PasswordEntry] = _entries

  def addEntry(service: String, username: String, password: String): Unit = {
    if (service.nonEmpty) {
      // Добавляем запись с незашифрованным парол@@ем (шифрование произойдет при сохранении)
      _entries += PasswordEntry(
        StringProperty(service),
        StringProperty(username),
        StringProperty(password)
      )
      saveToStorage()
    }
  }

  def deleteEntries(selected: Seq[PasswordEntry]): Unit = {
    if (selected.nonEmpty) {
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

empty definition using pc, found symbol in pc: `<none>`.