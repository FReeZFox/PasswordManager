package UI

import scalafx.scene.control._
import scalafx.scene.input.{Clipboard, ClipboardContent}
import scalafx.Includes._
import storage.PasswordEntry
import core.PasswordList

object ContextMenuView {
  def setupContextMenu(tableView: TableView[PasswordEntry]): Unit = {
    def copyAction(getter: PasswordEntry => String): Unit = 
      tableView
      .selectionModel
      .value
      .getSelectedItem match {
        case null => ()
        case entry => 
          val content = new ClipboardContent()
          content.putString(getter(entry))
          Clipboard.systemClipboard.setContent(content)
      }
    
    def deleteAction(): Unit = 
      Option(
        tableView
        .selectionModel
        .value
        .getSelectedItem
      )
        .foreach(entry => 
          PasswordList
          .deleteEntry(Seq(entry))
        )
    
    val menuItems = Seq(
      new MenuItem("Копировать логин") { 
        onAction = _ => copyAction(_.username.value) 
      },
      new MenuItem("Копировать пароль") { 
        onAction = _ => copyAction(_.password.value) 
      },
      new MenuItem("Удалить") { 
        onAction = _ => deleteAction() 
      }
    )
    
    val contextMenu = new ContextMenu(menuItems*)
    
    contextMenu
    .onShowing = _ => 
      menuItems
      .foreach(_.disable = tableView.selectionModel.value.isEmpty)
    
    tableView.contextMenu = contextMenu
  }
}