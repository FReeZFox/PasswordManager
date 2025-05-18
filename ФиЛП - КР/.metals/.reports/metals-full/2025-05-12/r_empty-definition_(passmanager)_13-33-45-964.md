error id: models/`<import>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/models/PasswordEntry.scala
empty definition using pc, found symbol in pc: models/`<import>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 85
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/models/PasswordEntry.scala
text:
```scala
package models

import scalafx.beans.property.{StringProperty, ObjectProperty}
import@@ java.time.LocalDateTime
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

  // Публичные методы преобразования
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
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: models/`<import>`.