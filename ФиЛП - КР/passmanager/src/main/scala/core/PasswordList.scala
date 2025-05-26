package core

import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scala.jdk.CollectionConverters._
import scala.util.chaining._
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
    _entries += Seq(service, username, password)
      .map(new StringProperty(_))
      .pipe {
        case Seq(s, u, p) => PasswordEntry(s, u, p)
      }

    saveToStorage()
  }

  def deleteEntry(selected: Seq[PasswordEntry]): Unit = {
    Option(selected)
      .filter(_.nonEmpty)
      .foreach { toRemove =>
        _entries --= _entries.filter(toRemove.contains)
        saveToStorage()
      }
  }

  def saveToStorage(): Unit = {
    PasswordStorage.save(_entries.toSeq)
  }
}
