error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/storage/PasswordStorage.scala:core/Crypto.decrypt().
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/storage/PasswordStorage.scala
empty definition using pc, found symbol in pc: core/Crypto.decrypt().
empty definition using semanticdb

found definition using fallback; symbol decrypt
offset: 1236
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/storage/PasswordStorage.scala
text:
```scala
package storage

import java.nio.file.{Files, Paths, StandardOpenOption}
import io.circe.syntax._
import io.circe.parser._
import scala.util.{Try, Success, Failure}
import scalafx.beans.property.StringProperty
import scala.io.Source
import core.Crypto

object PasswordStorage {
  private val StoragePath = Paths.get(
    System.getProperty("user.home"),
    ".passmanager",
    "passwords.json"
  )

  private def initStorage(): Unit = 
    Files.createDirectories(StoragePath.getParent)

  def load(): Seq[PasswordEntry] = {
    initStorage()
    Files
    .exists(StoragePath) match {
      case false => Seq.empty
      case true =>
        Try {
          val jsonString = Source.fromFile(StoragePath.toFile, "UTF-8").mkString
          parse(jsonString) match {
            case Right(json) => 
              json
              .as[Seq[PasswordEntry.EntryData]]
              .getOrElse(Seq.empty)
              .map(decryptEntry)
            case Left(_) => Seq.empty
          }
        }.getOrElse {
          println("Error.")
          Seq.empty
        }
    }
  }

  private def decryptEntry(data: PasswordEntry.EntryData): PasswordEntry =
    PasswordEntry
    .fromData(
      data
      .copy(
        password = Crypto.d@@ecrypt(data.password)
      )
    )


  def save(entries: Seq[PasswordEntry]): Try[Unit] = Try {
    initStorage()
    val json = entries.map { entry =>
      PasswordEntry
      .toData(
        entry
        .copy(
          password = StringProperty(Crypto.encrypt(entry.password.value))
        )
      )
    }.asJson.spaces2
    
    Files.write(
      StoragePath,
      json.getBytes("UTF-8"),
      StandardOpenOption.CREATE,
      StandardOpenOption.TRUNCATE_EXISTING
    )
  }
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: core/Crypto.decrypt().