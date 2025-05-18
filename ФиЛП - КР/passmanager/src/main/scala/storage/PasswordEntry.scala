package storage

import scalafx.beans.property.StringProperty
import io.circe._
import io.circe.generic.semiauto._

case class PasswordEntry(
  service: StringProperty,
  username: StringProperty,
  password: StringProperty
)

object PasswordEntry {
  case class EntryData(
    service: String,
    username: String,
    password: String
  )

  implicit val dataEncoder: Encoder[EntryData] = deriveEncoder
  implicit val dataDecoder: Decoder[EntryData] = deriveDecoder

  def toData(entry: PasswordEntry): EntryData = EntryData(
    entry.service.value,
    entry.username.value,
    entry.password.value
  )

  def fromData(data: EntryData): PasswordEntry = PasswordEntry(
    StringProperty(data.service),
    StringProperty(data.username),
    StringProperty(data.password)
  )

  def apply(
    service: String, 
    username: String, 
    password: String
  ): PasswordEntry = 
    PasswordEntry(
      StringProperty(service),
      StringProperty(username),
      StringProperty(password)
    )
}
