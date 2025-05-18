package UI

import scalafx.scene.control.TableView
import storage.PasswordEntry

object PasswordListView {
  def createView(): TableView[PasswordEntry] = {
    val tableView = PasswordTableView.createView()
    ContextMenuView.setupContextMenu(tableView)
    tableView
  }
}
