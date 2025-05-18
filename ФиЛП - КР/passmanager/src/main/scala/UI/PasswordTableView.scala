package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import storage.PasswordEntry
import core.PasswordList
import scalafx.beans.property.StringProperty

object PasswordTableView {
  def createView(): TableView[PasswordEntry] = {
    val tableView = new TableView[PasswordEntry] {
      minWidth = 425
      maxWidth = 425
      minHeight = 575
      maxHeight = 575
      fixedCellSize = 25
      columnResizePolicy = TableView.ConstrainedResizePolicy
      items = PasswordList.entries
      style = "-fx-focus-color: grey; -fx-faint-focus-color: transparent;"
      placeholder = new Label("Записей нет")
    }

    def createTextColumn(
      title: String, 
      columnWidth: Int, 
      propertyExtractor: PasswordEntry => StringProperty
    ): TableColumn[PasswordEntry, String] = {
      new TableColumn[PasswordEntry, String](title) {
        prefWidth = columnWidth
        resizable = false
        delegate.setReorderable(false)
        cellValueFactory = { data => propertyExtractor(data.value) }
        cellFactory = { (_: TableColumn[PasswordEntry, String]) =>
          new TableCell[PasswordEntry, String] {
            item.onChange { (_, _, newValue) =>
              text = Option(newValue).getOrElse("")
              tooltip = Option(newValue).map(new Tooltip(_)).orNull
            }
          }
        }
      }
    }

    val serviceColumn = createTextColumn("Сервис", 130, _.service)
    val usernameColumn = createTextColumn("Логин", 150, _.username)

    val passwordColumn = new TableColumn[PasswordEntry, String]("Пароль") {
      sortable = false
      prefWidth = 150
      resizable = false
      delegate.setReorderable(false)
      cellValueFactory = _.value.password
      cellFactory = { (_: TableColumn[PasswordEntry, String]) =>
        new TableCell[PasswordEntry, String] {
          private val toggleButton = new Button {
            text = "🔓"
            style = "-fx-background-color: transparent; -fx-padding: 0; -fx-font-size: 14px; -fx-font-weight: bold;"
          }

          private var passwordVisible = false

          toggleButton.onAction = _ => {
            passwordVisible = !passwordVisible
            updatePasswordDisplay()
            toggleButton.text = if (passwordVisible) "🔒" else "🔓"
          }

          private def updatePasswordDisplay(): Unit = {
            Option(item.value).foreach { value =>
              text = Option(value).fold("•" * 8)(v => if (passwordVisible) v else "•" * 8)
              tooltip = Option(value).filter(_ => passwordVisible).map(new Tooltip(_)).orNull
            }
          }

          item.onChange { (_, _, newValue) =>
            Option(newValue).fold {
              text = ""
              graphic = null
              tooltip = null
            } { _ =>
              updatePasswordDisplay()
              graphic = Option(item.value).filter(_.nonEmpty).map(_ => toggleButton).orNull
            }
          }

          updatePasswordDisplay()
          graphic = Option(item.value).filter(_.nonEmpty).map(_ => toggleButton).orNull
        }
      }
    }

    tableView.columns ++= List(serviceColumn, usernameColumn, passwordColumn)
    tableView
  }
}

