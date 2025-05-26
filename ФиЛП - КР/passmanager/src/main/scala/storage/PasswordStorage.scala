package storage

import java.nio.file.{Files, Paths, StandardOpenOption}
import io.circe.syntax._
import io.circe.parser._
import scala.util.{Try, Success, Failure}
import scalafx.beans.property.StringProperty
import scala.io.Source
import core.CryptoIO

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
    if (!Files.exists(StoragePath)) return Seq.empty

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
      println("Ошибка чтения из хранилища паролей")
      Seq.empty
    }
  }

  private def decryptEntry(data: PasswordEntry.EntryData): PasswordEntry = {
    val decryptedPassword = CryptoIO
      .TryCryptoInterpreter
      .decrypt(data.password)
      .getOrElse("") 

    PasswordEntry.fromData(
      data.copy(password = decryptedPassword)
    )
  }

  def save(entries: Seq[PasswordEntry]): Try[Unit] = Try {
    initStorage()

    val json = entries.map { entry =>
      val encryptedPassword = CryptoIO
        .TryCryptoInterpreter
        .encrypt(entry.password.value)
        .getOrElse("***") 

      PasswordEntry.toData(
        entry.copy(password = StringProperty(encryptedPassword))
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
