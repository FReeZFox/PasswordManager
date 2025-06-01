error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/SearchBar.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/SearchBar.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 675
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/SearchBar.scala
text:
```scala
package core

import scalafx.collections.ObservableBuffer
import storage.PasswordEntry

object SearchBar {
  val filteredEntries: ObservableBuffer[PasswordEntry] = ObservableBuffer[PasswordEntry]() ++ PasswordList.entries

  def filterEntries(query: String): Unit = {
    val q = query.trim.toLowerCase
    filteredEntries.setAll(
      if (q.isEmpty) PasswordList.entries
      else PasswordList.entries.filter { entry =>
        val values = Seq(
          entry.service.value,
          entry.username.value,
          entry.password.value
        )
        values.exists(_.toLowerCase.contains(q))
      }
    )
  }

  // Для синхронизации при обновлении PasswordList.ent@@ries
  def reset(): Unit = filterEntries("")
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.