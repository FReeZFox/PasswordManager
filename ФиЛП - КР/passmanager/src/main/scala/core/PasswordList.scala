package core

import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scala.jdk.CollectionConverters._
import storage.{PasswordEntry, PasswordStorage}

object PasswordList {
  private val _entries: ObservableBuffer[PasswordEntry] = ObservableBuffer.from(
    PasswordStorage.load().toSeq
  )

  def entries: ObservableBuffer[PasswordEntry] = _entries

  def addEntry(
    service: String, 
    username: String, 
    password: String
  ): Unit = {
    _entries += PasswordEntry(
      StringProperty(service),
      StringProperty(username),
      StringProperty(password)
    )
    saveToStorage()
  }

  def deleteEntry(selected: Seq[PasswordEntry]): Unit = {
    if (selected.nonEmpty) {
      _entries --= selected
      saveToStorage()
    }
  }

  def saveToStorage(): Unit = {
    PasswordStorage.save(_entries.toSeq)
  }
}

