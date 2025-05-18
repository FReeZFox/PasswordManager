error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordFormView.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordFormView.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 582
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordFormView.scala
text:
```scala
package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.geometry.Insets
import scalafx.scene.paint.Color
import core.{PasswordList, PasswordForm}
import models.PasswordEntry

object PasswordFormView {
  private var passwordFieldRef: Option[TextField] = None

  def setGeneratedPassword(password: String): Unit = {
    passwordFieldRef.foreach(_.text = password)
  }

  def createView(table: TableView[PasswordEntry]): VBox = {
    val errorLabel = new Label {
      textFill = Color.Red
      visible = false
      style = "-fx-font-size: 12px; -fx-p@@adding: 0 0 0 0;"
    }

    val serviceField = new TextField 
    { 
      promptText = "Сервис" 
      //contextMenu = new ContextMenu()
    }
    val usernameField = new TextField 
    { 
      promptText = "Логин" 
      contextMenu = new ContextMenu()
    }
    val passwordField = new TextField 
    { 
      promptText = "Пароль" 
      contextMenu = new ContextMenu()
    }
    passwordFieldRef = Some(passwordField)

    val addButton = new Button("Добавить") {
      onAction = _ => {
        val (success, errorMsg) = PasswordForm.validateAndAddEntry(
          serviceField.text.value.trim,
          usernameField.text.value.trim,
          passwordField.text.value,
          PasswordList.entries
        )

        if (success) {
          serviceField.clear()
          usernameField.clear()
          passwordField.clear()
          errorLabel.visible = false
        } else {
          errorLabel.text = errorMsg
          errorLabel.visible = true
        }
      }
    }

    new VBox(10,
      new VBox(5, serviceField, usernameField, passwordField),
      errorLabel,  
      new HBox(10, addButton)
    ) {
      padding = Insets(15)
    }
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.