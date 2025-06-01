error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/SearchBarView.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/SearchBarView.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 246
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/SearchBarView.scala
text:
```scala
package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.geometry.Insets
import core.SearchBar

object SearchBarView {
  def createView(): TextField = {
    new TextField {
      promptText = "Поиск по сервису или л@@огину"
      maxWidth = 300
      minHeight = 30
      padding = Insets(5)
      style = "-fx-font-size: 13px;"

      text.onChange { (_, _, newValue) =>
        SearchBar.filterEntries(newValue)
      }
    }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.