error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordTableView.scala:UI/`<import>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordTableView.scala
empty definition using pc, found symbol in pc: UI/`<import>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 76
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordTableView.scala
text:
```scala
package UI

import scalafx.scene.control._
import scalafx.scene.layout._
imp@@ort storage.PasswordEntry
import core.SearchBar
import scalafx.beans.property.StringProperty
import scala.jdk.CollectionConverters._
import scalafx.Includes._
import scalafx.scene.input.KeyCode

object PasswordTableView {
  def createView(): TableView[PasswordEntry] = {
    val tableView = new TableView[PasswordEntry] {
      minWidth = 425
      maxWidth = 425
      minHeight = 525
      maxHeight = 525
      fixedCellSize = 25
      columnResizePolicy = TableView.ConstrainedResizePolicy
      items = SearchBar.filteredEntries
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

    // Обработчик нажатия Tab — для переключения выделения
    tableView.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, (event: javafx.scene.input.KeyEvent) => {
      if (event.getCode == javafx.scene.input.KeyCode.TAB) {
        val items = tableView.items.value.asScala
        if (items.nonEmpty) {
          val selModel = tableView.selectionModel()
          val currentIndex = selModel.getSelectedIndex
          val nextIndex =
            if (event.isShiftDown)
              if (currentIndex <= 0) items.size - 1 else currentIndex - 1
            else
              if (currentIndex < 0 || currentIndex >= items.size - 1) 0 else currentIndex + 1

          selModel.clearAndSelect(nextIndex)
          tableView.scrollTo(nextIndex)
          event.consume()  // Чтобы Tab не переключал фокус на другие компоненты
        }
      }
    })

    // Обработчик нажатия Enter — копирует пароль выделенной записи в буфер обмена
    tableView.onKeyPressed = (event: scalafx.scene.input.KeyEvent) => {
      if (event.code == KeyCode.Enter) {
        val selectedItem = tableView.selectionModel().getSelectedItem
        if (selectedItem != null) {
          val password = selectedItem.password.value
          val clipboard = javafx.scene.input.Clipboard.getSystemClipboard
          val content = new javafx.scene.input.ClipboardContent()
          content.putString(password)
          clipboard.setContent(content)
          // Опционально: можно показать уведомление о копировании пароля
          println(s"Пароль для '${selectedItem.service.value}' скопирован в буфер обмена")
        }
        event.consume()
      }
    }

    tableView
  }
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: UI/`<import>`.