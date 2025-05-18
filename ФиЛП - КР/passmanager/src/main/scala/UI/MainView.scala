package UI

import scalafx.scene.layout.{BorderPane, HBox, VBox, Priority}
import scalafx.scene.control.Separator
import scalafx.geometry.Orientation

object MainView {
  def createView(): BorderPane = {
    new BorderPane {
      val table = PasswordListView.createView()
      
      center = new HBox {
        val leftPane = new BorderPane { center = table }
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

