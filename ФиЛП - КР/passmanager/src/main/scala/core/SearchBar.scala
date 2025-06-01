package core

import scalafx.collections.ObservableBuffer
import storage.PasswordEntry

object SearchBar {
  val filteredEntries: ObservableBuffer[PasswordEntry] = ObservableBuffer[PasswordEntry]() ++ PasswordList.entries

  private var currentFilter: String = ""

  PasswordList.entries.onChange { (_, _) =>
    filterEntries(currentFilter)
  }

  def filterEntries(query: String): Unit = {
    currentFilter = query.trim.toLowerCase

    filteredEntries
    .setAll(
      Option
      .when(currentFilter.nonEmpty)
      (PasswordList.entries.filter { entry =>
        Seq(
          entry.service.value, 
          entry.username.value
        )
        .exists(_.toLowerCase.contains(currentFilter))
      }
      ).getOrElse(PasswordList.entries)
    )
  }

  def reset(): Unit = filterEntries("")
}
