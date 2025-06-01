package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.geometry.Insets
import core.SearchBar

object SearchBarView {
  def createView(): TextField = {
    new TextField {
      promptText = "Поиск по сервису или логину"
      maxWidth = 370
      minHeight = 23
      focusTraversable = false

      text.onChange { (_, _, newValue) =>
        SearchBar.filterEntries(newValue)
      }
    }
  }
}
