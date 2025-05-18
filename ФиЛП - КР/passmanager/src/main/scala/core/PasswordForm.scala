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
          .fold(Right(()))(_ => Left("Запись с таким логином и сервисом уже существует."))
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