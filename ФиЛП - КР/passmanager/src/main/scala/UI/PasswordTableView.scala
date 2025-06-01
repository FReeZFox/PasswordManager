package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.beans.property.StringProperty
import scalafx.Includes._
import scalafx.scene.input.KeyCode
import scala.jdk.CollectionConverters._
import javafx.scene.input.{Clipboard, ClipboardContent}
import javafx.scene.input.KeyEvent
import javafx.scene.input.KeyCode.TAB
import storage.PasswordEntry
import core.SearchBar

object PasswordTableView {
  def createView(): TableView[PasswordEntry] = {
    val tableView = new TableView[PasswordEntry] {
      minWidth = 425
      maxWidth = 425
      minHeight = 525
      maxHeight = 525
      fixedCellSize = 25
      items = SearchBar.filteredEntries
      columnResizePolicy = TableView.ConstrainedResizePolicy
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
    .addEventFilter(
      KeyEvent.KEY_PRESSED, event =>
      Option(event.getCode)
      .filter(_ == TAB)
      .filter(_ => tableView.items.value.nonEmpty)
      .foreach { _ =>
        val selModel = tableView.selectionModel()
        val currentIndex = selModel.getSelectedIndex
        val maxIndex = tableView.items.value.size - 1

        val nextIndex = (event.isShiftDown, currentIndex) match {
          case (true, i) if i <= 0                  => maxIndex
          case (true, i)                            => i - 1
          case (false, i) if i < 0 || i >= maxIndex => 0
          case (false, i)                           => i + 1
        }

        selModel.clearAndSelect(nextIndex)
        tableView.scrollTo(nextIndex)
        event.consume()
      }
    )


    tableView.onKeyPressed = event => {
      if (event.code == KeyCode.Enter) {
        Option(
          tableView
          .selectionModel()
          .getSelectedItem)
          .foreach { selectedItem =>
            val password = selectedItem.password.value
            val clipboard = Clipboard.getSystemClipboard
            val content = new ClipboardContent()
            content.putString(password)
            clipboard.setContent(content)
        }
        event.consume()
      }
    }

    tableView
  }
}

