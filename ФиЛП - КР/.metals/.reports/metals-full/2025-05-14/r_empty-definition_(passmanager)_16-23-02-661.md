error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordTableFeatures.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordTableFeatures.scala
empty definition using pc, found symbol in pc: `<none>`.
semanticdb not found
empty definition using fallback
non-local guesses:
	 -scalafx/scene/control/col.
	 -scalafx/scene/control/col#
	 -scalafx/scene/control/col().
	 -scalafx/Includes.col.
	 -scalafx/Includes.col#
	 -scalafx/Includes.col().
	 -col.
	 -col#
	 -col().
	 -scala/Predef.col.
	 -scala/Predef.col#
	 -scala/Predef.col().
offset: 1466
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordTableFeatures.scala
text:
```scala
package UI

import scalafx.scene.control._
import scalafx.scene.input.{Clipboard, ClipboardContent}
import models.PasswordEntry
import scalafx.Includes._

object PasswordTableFeatures {
  def addContextMenu(tableView: TableView[PasswordEntry]): Unit = {
    val contextMenu = new ContextMenu {
      items ++= Seq(
        createCopyLoginItem(tableView),
        createCopyPasswordItem(tableView),
        new SeparatorMenuItem(),
        createDeleteItem(tableView)
      )
    }
    
    contextMenu.onShowing = _ => updateMenuItems(contextMenu, tableView)
    tableView.contextMenu = contextMenu
  }

  def addPasswordToggle(tableView: TableView[PasswordEntry]): Unit = {
    tableView.columns(2).cellFactory = createPasswordCellFactory()
  }

  private def createCopyLoginItem(tableView: TableView[PasswordEntry]) = 
    new MenuItem("Копировать логин") {
      onAction = _ => copyToClipboard(tableView, _.username.value)
    }

  private def createCopyPasswordItem(tableView: TableView[PasswordEntry]) =
    new MenuItem("Копировать пароль") {
      onAction = _ => copyToClipboard(tableView, _.password.value)
    }

  private def createDeleteItem(tableView: TableView[PasswordEntry]) =
    new MenuItem("Удалить") {
      style = "-fx-text-fill: red;"
      onAction = _ => deleteSelected(tableView)
    }

    private def createPasswordCellFactory(): javafx.util.Callback[TableColumn[PasswordEntry, String], TableCell[PasswordEntry, String]] = {
        (co@@l: TableColumn[PasswordEntry, String]) =>
          new TableCell[PasswordEntry, String] {
            private val toggleButton = createToggleButton()
            private var passwordVisible = false
    
            override def updateItem(item: String, empty: Boolean): Unit = {
              super.updateItem(item, empty)
              if (empty || item == null) {
                text = ""
                graphic = null
                tooltip = null
              } else {
                updatePasswordDisplay()
                graphic = toggleButton
              }
            }
    
            private def createToggleButton(): Button = new Button {
              text = "🔓"
              style = "-fx-background-color: transparent; -fx-padding: 0; -fx-font-size: 14px; -fx-font-weight: bold;"
              onAction = _ => {
                passwordVisible = !passwordVisible
                updatePasswordDisplay()
                updateButtonEmoji()
              }
            }
    
            private def updatePasswordDisplay(): Unit = {
              val entry = getTableView.getItems.get(getIndex)
              if (entry != null) {
                text = if (passwordVisible) entry.password.value else "•" * 8
                style = if (passwordVisible) "" else "-fx-font-size: 14px; -fx-font-weight: bold;"
                tooltip = if (passwordVisible) new Tooltip(entry.password.value) else null
              }
            }
    
            private def updateButtonEmoji(): Unit = {
              toggleButton.text = if (passwordVisible) "🔒" else "🔓"
            }
          }
      }

  private def copyToClipboard(tableView: TableView[PasswordEntry], field: PasswordEntry => String): Unit = {
    tableView.selectionModel.value.getSelectedItem match {
      case entry: PasswordEntry =>
        val content = new ClipboardContent()
        content.putString(field(entry))
        Clipboard.systemClipboard.setContent(content)
      case _ =>
    }
  }
  
  private def deleteSelected(tableView: TableView[PasswordEntry]): Unit = {
    tableView.selectionModel.value.getSelectedItem match {
      case entry: PasswordEntry => core.PasswordList.deleteEntries(Seq(entry))
      case _ =>
    }
  }
  
  private def updateMenuItems(menu: ContextMenu, tableView: TableView[PasswordEntry]): Unit = {
    val hasSelection = !tableView.selectionModel.value.isEmpty
    menu.items.foreach(_.disable = !hasSelection)
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.