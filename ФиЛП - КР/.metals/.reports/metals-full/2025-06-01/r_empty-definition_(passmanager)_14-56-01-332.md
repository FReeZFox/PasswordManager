error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordForm.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordForm.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 571
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/core/PasswordForm.scala
text:
```scala
package core

import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import storage.PasswordEntry

object PasswordForm {
  def validateEntry(
    service: String,
    username: String,
    password: String,
    entries: ObservableBuffer[PasswordEntry]
  ): Either[String, Unit] = {
    Seq(service, username, password)
      .find(_.isEmpty)
      .fold {
        entries
          .find(e => e.service.value == service && e.username.value == username)
          .fold(Right(()))(_ => Left("Запись с таким логином и сер@@висом уже существует."))
      }(_ => Left("Все поля обязательны для заполнения."))
  }

  def addEntry(
    service: String,
    username: String,
    password: String,
    entries: ObservableBuffer[PasswordEntry]
  ): Either[String, Unit] = {
    validateEntry(
      service, 
      username, 
      password, 
      entries
    ).map { _ =>
      entries += PasswordEntry(
        StringProperty(service),
        StringProperty(username), 
        StringProperty(password)
      )
      PasswordList.saveToStorage()
    }
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.