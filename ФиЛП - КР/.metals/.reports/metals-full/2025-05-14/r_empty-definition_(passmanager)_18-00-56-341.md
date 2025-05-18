error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/MainView.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/MainView.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1829
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/MainView.scala
text:
```scala
// package UI

// import scalafx.scene.layout.{BorderPane, HBox, VBox, Priority}
// import scalafx.scene.control.Separator
// import scalafx.geometry.Orientation

// object MainView {
//   def createView(): BorderPane = {
//     new BorderPane {
//       // Создаем TableView один раз
//       val table = PasswordListView.createView()
      
//       center = new HBox {
//         // Левая часть - таблица
//         val leftPane = new BorderPane {
//           center = table
//         }
//         leftPane.prefWidth = 450
//         leftPane.minWidth = 450
//         leftPane.maxWidth = 450

//         val verticalSeparator = new Separator {
//           orientation = Orientation.Vertical
//         }

//         // Правая часть - формы и генератор
//         val rightPane = new VBox {
//           // Передаем тот же самый table в формы
//           val form = PasswordFormView.createView(table)
          
//           val horizontalSeparator = new Separator {
//             orientation = Orientation.Horizontal
//           }
          
//           val generator = PasswordGeneratorView.createView(password => {
//             PasswordFormView.setGeneratedPassword(password)
//           })
//           VBox.setVgrow(generator, Priority.Always)

//           children = Seq(form, horizontalSeparator, generator)
//         }
//         HBox.setHgrow(rightPane, Priority.Always)

//         children = Seq(leftPane, verticalSeparator, rightPane)
//       }
//     }
//   }
// }

package UI

import scalafx.scene.layout.{BorderPane, HBox, VBox, Priority}
import scalafx.scene.control.{Separator, TextInputDialog, Alert}
import scalafx.geometry.Orientation
import core.Crypto

object MainView {
  def createView(): BorderPane = {
    // Устанавливаем мастер-пароль
    val masterPassword = "freez-fox-fox" // Замени@@те на запрос у пользователя
    Crypto.setMasterPassword(masterPassword)

    // Создаем TableView один раз
    val table = PasswordListView.createView()

    new BorderPane {
      center = new HBox {
        // Левая часть - таблица
        val leftPane = new BorderPane {
          center = table
        }
        leftPane.prefWidth = 450
        leftPane.minWidth = 450
        leftPane.maxWidth = 450

        val verticalSeparator = new Separator {
          orientation = Orientation.Vertical
        }

        // Правая часть - формы и генератор
        val rightPane = new VBox {
          // Передаем тот же самый table в формы
          val form = PasswordFormView.createView(table)

          val horizontalSeparator = new Separator {
            orientation = Orientation.Horizontal
          }

          val generator = PasswordGeneratorView.createView(password => {
            PasswordFormView.setGeneratedPassword(password)
          })
          VBox.setVgrow(generator, Priority.Always)

          children = Seq(form, horizontalSeparator, generator)
        }
        HBox.setHgrow(rightPane, Priority.Always)

        children = Seq(leftPane, verticalSeparator, rightPane)
      }
    }
  }
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.