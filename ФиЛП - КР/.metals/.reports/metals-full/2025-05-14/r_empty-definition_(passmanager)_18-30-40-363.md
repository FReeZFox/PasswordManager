error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/ContextMenuView.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/ContextMenuView.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb

found definition using fallback; symbol PasswordEntry
offset: 119
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/ContextMenuView.scala
text:
```scala
package UI

import scalafx.scene.control._
import scalafx.scene.input.{Clipboard, ClipboardContent}
import storage.@@PasswordEntry
import core.PasswordList
import scalafx.Includes._

object ContextMenuView {
  def setupContextMenu(tableView: TableView[PasswordEntry]): Unit = {
    val contextMenu = new ContextMenu {
      // Пункт "Копировать логин"
      items += new MenuItem("Копировать логин") {
        onAction = _ => {
          tableView.selectionModel.value.getSelectedItem match {
            case entry: PasswordEntry =>
              val content = new ClipboardContent()
              content.putString(entry.username.value)
              Clipboard.systemClipboard.setContent(content)
            //case _ =>
          }
        }
      }

      // Пункт "Копировать пароль"
      items += new MenuItem("Копировать пароль") {
        onAction = _ => {
          tableView.selectionModel.value.getSelectedItem match {
            case entry: PasswordEntry =>
              val content = new ClipboardContent()
              content.putString(entry.password.value)
              Clipboard.systemClipboard.setContent(content)
            //case _ =>
          }
        }
      }

      // Разделитель
      items += new SeparatorMenuItem()

      // Пункт "Удалить"
      items += new MenuItem("Удалить") {
        style = "-fx-text-fill: red;"
        onAction = _ => {
          tableView.selectionModel.value.getSelectedItem match {
            case entry: PasswordEntry =>
              PasswordList.deleteEntries(Seq(entry))
            //case _ =>
          }
        }
      }
    }

    // Настраиваем поведение контекстного меню
    contextMenu.onShowing = _ => {
      val hasSelection = !tableView.selectionModel.value.isEmpty
      contextMenu.items.foreach { item =>
        item.disable = !hasSelection
      }
    }

    // Устанавливаем контекстное меню для таблицы
    tableView.contextMenu = contextMenu
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.