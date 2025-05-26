error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/storage/PasswordEntry.scala:
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/storage/PasswordEntry.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 3
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/storage/PasswordEntry.scala
text:
```scala
// @@package storage

// import scalafx.beans.property.StringProperty
// import io.circe._
// import io.circe.generic.semiauto._

// case class PasswordEntry(
//   service: StringProperty,
//   username: StringProperty,
//   password: StringProperty
// )

// object PasswordEntry {
//   case class EntryData(
//     service: String,
//     username: String,
//     password: String
//   )

//   implicit val dataEncoder: Encoder[EntryData] = deriveEncoder
//   implicit val dataDecoder: Decoder[EntryData] = deriveDecoder

//   def toData(entry: PasswordEntry): EntryData = EntryData(
//     entry.service.value,
//     entry.username.value,
//     entry.password.value
//   )

//   def fromData(data: EntryData): PasswordEntry = PasswordEntry(
//     StringProperty(data.service),
//     StringProperty(data.username),
//     StringProperty(data.password)
//   )

//   def apply(
//     service: String, 
//     username: String, 
//     password: String
//   ): PasswordEntry = 
//     PasswordEntry(
//       StringProperty(service),
//       StringProperty(username),
//       StringProperty(password)
//     )
// }

// storage/PasswordEntry.scala
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

  def fromDataF[F[_]](dataF: F[EntryData])(implicit F: cats.Functor[F]): F[PasswordEntry] = {
    F.map(dataF) { data =>
      PasswordEntry(
        StringProperty(data.service),
        StringProperty(data.username),
        StringProperty(data.password)
      )
    }
  }

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
```


#### Short summary: 

empty definition using pc, found symbol in pc: 