error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/storage/PasswordEntry.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/storage/PasswordEntry.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 707
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/storage/PasswordEntry.scala
text:
```scala
package models

import scalafx.beans.property.{StringProperty, ObjectProperty}
import java.time.LocalDateTime
import io.circe._
import io.circe.generic.semiauto._

case class PasswordEntry(
  service: StringProperty,
  username: StringProperty,
  password: StringProperty,
  createdAt: ObjectProperty[LocalDateTime] = ObjectProperty(LocalDateTime.now)
)

object PasswordEntry {
  // Публичный DTO для сериализации
  case class EntryData(
    service: String,
    username: String,
    password: String,
    createdAt: String
  )

  // Автоматические JSON-кодеки
  implicit val dataEncoder: Encoder[EntryData] = deriveEncoder
  implicit val dataDecoder: Decoder[EntryData] = deriveDecoder

  // Публичные мет@@оды преобразования
  def toData(entry: PasswordEntry): EntryData = EntryData(
    entry.service.value,
    entry.username.value,
    entry.password.value,
    entry.createdAt.value.toString
  )

  def fromData(data: EntryData): PasswordEntry = PasswordEntry(
    StringProperty(data.service),
    StringProperty(data.username),
    StringProperty(data.password),
    ObjectProperty(LocalDateTime.parse(data.createdAt))
  )

  def apply(service: String, username: String, password: String): PasswordEntry = 
    PasswordEntry(
      StringProperty(service),
      StringProperty(username),
      StringProperty(password)
    )
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.