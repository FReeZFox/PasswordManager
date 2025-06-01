package UI

import scalafx.scene.layout.{BorderPane, HBox, VBox, Priority}
import scalafx.scene.control.Separator
import scalafx.geometry.{Orientation, Pos, Insets}

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

