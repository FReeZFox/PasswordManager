error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordFormView.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordFormView.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1498
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordFormView.scala
text:
```scala
package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.geometry.Insets
import scalafx.Includes._
import core.{PasswordList, PasswordForm}
import storage.PasswordEntry

object PasswordFormView {
  private var passwordFieldRef: Option[TextField] = None

  def setGeneratedPassword(password: String): Unit = 
    passwordFieldRef.foreach(_.text = password)

  def createView(table: TableView[PasswordEntry]): VBox = {
    val errorLabel = new Label {
      textFill = Color.Red
      style = "-fx-font-size: 12px;"
    }

    val (service, username, password) = (
      createTextField("Сервис"), 
      createTextField("Логин"), 
      createTextField("Пароль")
    )
    passwordFieldRef = Some(password)

    new VBox(10) {
      padding = Insets(15)
      children = Seq(
        new VBox(5, service, username, password),
        errorLabel,
        new HBox(10, new Button("Добавить") {
          focusTraversable = false
          onAction = _ => PasswordForm.addEntry(
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

  private def createTextField(prompt: String): TextField = @@new TextField {
    promptText = prompt
    focusTraversable = false
    contextMenu = {
      val field = this
      new ContextMenu(
        new MenuItem("Копировать") {
          onAction = _ => if (field.selectedText.value.nonEmpty) field.copy()
        },
        new MenuItem("Вставить") {
          onAction = _ => field.paste()
        },
        new MenuItem("Вырезать") {
          onAction = _ => if (field.selectedText.value.nonEmpty) field.cut()
        }
      )
    }
  }
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.