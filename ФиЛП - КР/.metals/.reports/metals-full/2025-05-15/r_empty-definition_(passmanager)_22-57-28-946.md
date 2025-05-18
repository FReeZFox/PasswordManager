error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordFormView.scala:
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordFormView.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 650
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordFormView.scala
text:
```scala
// package UI

// import scalafx.scene.control._
// import scalafx.scene.layout._
// import scalafx.geometry.Insets
// import scalafx.scene.paint.Color
// import scalafx.Includes._
// import core.{PasswordList, PasswordForm}
// import storage.PasswordEntry

// object PasswordFormView {
//   private var passwordFieldRef: Option[TextField] = None

//   def setGeneratedPassword(password: String): Unit = {
//     passwordFieldRef.foreach(_.text = password)
//   }

//   private def setupContextMenu(textField: TextField): Unit = {
//     if (textField.contextMenu.value == null) {
//       textField.contextMenu = new ContextMenu()
//     }

//     v@@al actions = Map(
//       "Копировать" -> (() => if (textField.selectedText.value.nonEmpty) textField.copy()),
//       "Вставить"   -> (() => textField.paste()),
//       "Вырезать"   -> (() => if (textField.selectedText.value.nonEmpty) textField.cut())
//     )

//     textField.contextMenu.value.items.clear()
//     actions.foreach { case (label, action) =>
//       textField.contextMenu.value.items.add(new MenuItem(label) {
//         onAction = _ => action()
//       })
//     }
//   }

//   private def createTextField(prompt: String): TextField = new TextField {
//     promptText = prompt
//     setupContextMenu(this)
//   }

//   private def createFormFields(): (TextField, TextField, TextField) = {
//     val serviceField = createTextField("Сервис")
//     val usernameField = createTextField("Логин")
//     val passwordField = createTextField("Пароль")
//     passwordFieldRef = Some(passwordField)
//     (serviceField, usernameField, passwordField)
//   }

//   private def createErrorLabel(): Label = new Label {
//     textFill = Color.Red
//     visible = false
//     style = "-fx-font-size: 12px; -fx-padding: 0 0 0 0;"
//   }

//   private def createAddButton(
//     serviceField: TextField,
//     usernameField: TextField,
//     passwordField: TextField,
//     errorLabel: Label
//   ): Button = new Button("Добавить") {
//     onAction = _ => {
//       PasswordForm.validateAndAddEntry(
//         serviceField.text.value.trim,
//         usernameField.text.value.trim,
//         passwordField.text.value,
//         PasswordList.entries
//       ) match {
//         case Left(errorMsg) =>
//           errorLabel.text = errorMsg
//           errorLabel.visible = true
//         case Right(_) =>
//           Seq(serviceField, usernameField, passwordField).foreach(_.clear())
//           errorLabel.visible = false
//       }
//     }
//   }

//   def createView(table: TableView[PasswordEntry]): VBox = {
//     val errorLabel = createErrorLabel()
//     val (serviceField, usernameField, passwordField) = createFormFields()
//     val addButton = createAddButton(serviceField, usernameField, passwordField, errorLabel)

//     new VBox(10,
//       new VBox(5, serviceField, usernameField, passwordField),
//       errorLabel,
//       new HBox(10, addButton)
//     ) {
//       padding = Insets(15)
//     }
//   }

package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.geometry.Insets
import scalafx.scene.paint.Color
import scalafx.Includes._
import core.{PasswordList, PasswordForm}
import storage.PasswordEntry

object PasswordFormView {
  private var passwordFieldRef: Option[TextField] = None

  def setGeneratedPassword(password: String): Unit = 
    passwordFieldRef.foreach(_.text = password)

  def createView(table: TableView[PasswordEntry]): VBox = {
    val errorLabel = new Label {
      textFill = Color.Red; visible = false
      style = "-fx-font-size: 12px;"
    }

    val (service, username, password) = (
      newTextField("Сервис"), 
      newTextField("Логин"), 
      newTextField("Пароль")
    )
    passwordFieldRef = Some(password)

    new VBox(10) {
      padding = Insets(15)
      children = Seq(
        new VBox(5, service, username, password),
        errorLabel,
        new HBox(10, new Button("Добавить") {
          onAction = _ => PasswordForm.validateAndAddEntry(
            service.text.value.trim,
            username.text.value.trim,
            password.text.value,
            PasswordList.entries
          ) match {
            case Left(error) => errorLabel.text = error; errorLabel.visible = true
            case Right(_)    => Seq(service, username, password).foreach(_.text = ""); errorLabel.visible = false
          }
        })
      )
    }
  }

  private def newTextField(prompt: String): TextField = new TextField {
    promptText = prompt
    contextMenu = new ContextMenu(Seq(
      "Копировать" -> (() => if (selectedText.value.nonEmpty) delegate.copy()),
      "Вставить"   -> (() => delegate.paste()),
      "Вырезать"   -> (() => if (selectedText.value.nonEmpty) delegate.cut())
    ).map { case (label, action) => 
      new MenuItem(label) { onAction = _ => action() }
    }*)
  }
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: 