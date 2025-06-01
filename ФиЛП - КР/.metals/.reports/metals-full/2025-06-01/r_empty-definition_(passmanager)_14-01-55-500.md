error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/MainView.scala:UI/`<import>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/MainView.scala
empty definition using pc, found symbol in pc: UI/`<import>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 120
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/MainView.scala
text:
```scala
package UI

import scalafx.scene.layout.{BorderPane, HBox, VBox, Priority}
import scalafx.scene.control.Separator
import@@ scalafx.geometry.Orientation
import scalafx.geometry.Pos
import scalafx.geometry.Insets

object MainView {
  def createView(): BorderPane = {
    new BorderPane {
      val table = PasswordListView.createView()
      
      center = new HBox {
        val leftPane = new VBox {
          alignment = Pos.Center
          spacing = 8
          padding = Insets(8)

          val searchBar = SearchBarView.createView()
          val horizontalSeparator = new Separator {
            orientation = Orientation.Horizontal
          }

          val centeredTable = new HBox {
            alignment = Pos.Center
            children = Seq(table)
          }

          children = Seq(searchBar, horizontalSeparator, centeredTable)
        }
        leftPane.prefWidth = 450
        leftPane.minWidth = 450
        leftPane.maxWidth = 450

        val verticalSeparator = new Separator {
          orientation = Orientation.Vertical
        }

        val rightPane = new VBox {
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

empty definition using pc, found symbol in pc: UI/`<import>`.