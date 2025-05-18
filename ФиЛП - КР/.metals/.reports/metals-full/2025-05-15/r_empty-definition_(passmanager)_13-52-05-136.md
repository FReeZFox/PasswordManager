error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordTableView.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordTableView.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 6667
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordTableView.scala
text:
```scala
// package UI

// import scalafx.scene.control._
// import scalafx.scene.layout._
// import storage.PasswordEntry
// import core.PasswordList

// object PasswordTableView {
//   def createView(): TableView[PasswordEntry] = {
//     val tableView = new TableView[PasswordEntry] {
//       prefWidth = 430
//       minWidth = 430
//       maxWidth = 430
//       prefHeight = 580
//       minHeight = 580
//       maxHeight = 580
//     }

//     tableView.items = PasswordList.entries

//     // Настраиваем колонки таблицы
//     tableView.columns ++= List(
//       new TableColumn[PasswordEntry, String]("Сервис") {
//         cellValueFactory = _.value.service
//         prefWidth = 130
//         resizable = false
        
//         cellFactory = { (col: TableColumn[PasswordEntry, String]) =>
//           new TableCell[PasswordEntry, String] {
//             item.onChange { (_, _, newValue) =>
//               text = if (newValue != null) newValue else ""
//               tooltip = new Tooltip(if (newValue != null) newValue else "")
//             }
//           }
//         }
//       },
//       new TableColumn[PasswordEntry, String]("Логин") {
//         cellValueFactory = _.value.username
//         prefWidth = 150
//         resizable = false
        
//         cellFactory = { (col: TableColumn[PasswordEntry, String]) =>
//           new TableCell[PasswordEntry, String] {
//             item.onChange { (_, _, newValue) =>
//               text = if (newValue != null) newValue else ""
//               tooltip = new Tooltip(if (newValue != null) newValue else "")
//             }
//           }
//         }
//       },
//       new TableColumn[PasswordEntry, String]("Пароль") {
//         sortable = false 
//         cellValueFactory = _.value.password
//         prefWidth = 150
//         resizable = false
        
//         cellFactory = { (col: TableColumn[PasswordEntry, String]) =>
//           new TableCell[PasswordEntry, String] {
//             private val toggleButton = new Button {
//               text = "🔓"
//               style = "-fx-background-color: transparent; -fx-padding: 0; -fx-font-size: 14px; -fx-font-weight: bold;"
//             }
            
//             private var passwordVisible = false
            
//             toggleButton.onAction = _ => {
//               passwordVisible = !passwordVisible
//               updatePasswordDisplay()
//               updateButtonEmoji()
//             }
            
//             private def updatePasswordDisplay(): Unit = {
//               if (item.value != null) {
//                 text = if (passwordVisible) item.value else "•" * 8
//                 style = if (passwordVisible) "" else "-fx-font-size: 14px; -fx-font-weight: bold;"
//                 tooltip = if (passwordVisible) new Tooltip(item.value) else null
//               } else {
//                 text = ""
//                 tooltip = null
//               }
//             }
            
//             private def updateButtonEmoji(): Unit = {
//               toggleButton.text = if (passwordVisible) "🔒" else "🔓"
//             }
            
//             override def startEdit(): Unit = {
//               super.startEdit()
//               graphic = null
//             }
            
//             override def cancelEdit(): Unit = {
//               super.cancelEdit()
//               updateGraphic()
//             }
            
//             private def updateGraphic(): Unit = {
//               if (item == null || item.value == null) {
//                 graphic = null
//               } else {
//                 graphic = toggleButton
//               }
//             }
            
//             item.onChange { (_, _, newValue) =>
//               if (newValue != null) {
//                 updatePasswordDisplay()
//                 updateButtonEmoji()
//                 updateGraphic()
//               } else {
//                 text = ""
//                 graphic = null
//                 tooltip = null
//               }
//             }
            
//             updatePasswordDisplay()
//             updateButtonEmoji()
//             updateGraphic()
//           }
//         }
//       }
//     )
    
//     tableView.columnResizePolicy = TableView.ConstrainedResizePolicy
//     tableView
//   }
// }

package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import storage.PasswordEntry
import core.PasswordList
import scalafx.beans.property.StringProperty

object PasswordTableView {
  def createView(): TableView[PasswordEntry] = {
    val tableView = new TableView[PasswordEntry] {
      prefWidth = 430
      minWidth = 430
      maxWidth = 430
      prefHeight = 580
      minHeight = 580
      maxHeight = 580
      columnResizePolicy = TableView.ConstrainedResizePolicy
      items = PasswordList.entries
    }

    def createTextColumn(title: String, columnWidth: Int, propertyExtractor: PasswordEntry => StringProperty): TableColumn[PasswordEntry, String] = {
      new TableColumn[PasswordEntry, String](title) {
        prefWidth = columnWidth
        resizable = false
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
      cellValueFactory = _.value.password
      cellFactory = { (_: TableColumn[PasswordEntry, String]) =>
        new TableCell[PasswordEntry, String] {
          private val toggleButton = new Button {
            text = "🔓"
            style = "-fx-background-color: transparent; -fx-padding: 0; -fx-font-size: 13px; -fx-font-weight: bold;"
          }

          private var passwordVisible = false

          toggleButton.onAction = _ => {
            passwordVisible = !passwordVisible
            updatePasswordDisplay()
            toggleButton.text = if (passwordVisible) "🔒" else "🔓"
          }

          private def updatePasswordDisplay(): Unit = {
            Option(item.value).foreach { value =>
              text = if (passwordVisible) value else "•" * 8
              style = if (passwordVisible) "" else "-fx-font-size: 14p@@x; -fx-font-weight: bold;"
              tooltip = if (passwordVisible) new Tooltip(value) else null
            }
          }

          item.onChange { (_, _, newValue) =>
            Option(newValue).fold {
              text = ""
              graphic = null
              tooltip = null
            } { _ =>
              updatePasswordDisplay()
              graphic = if (item.value != null && item.value.nonEmpty) toggleButton else null
            }
          }

          updatePasswordDisplay()
          graphic = if (item.value != null && item.value.nonEmpty) toggleButton else null
        }
      }
    }

    tableView.columns ++= List(serviceColumn, usernameColumn, passwordColumn)
    tableView
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.