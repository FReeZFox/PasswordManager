error id: `<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/MainView.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 287
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/MainView.scala
text:
```scala
package UI

import scalafx.scene.layout.BorderPane
import scalafx.scene.control.SplitPane

object MainView {
  def createView(): BorderPane = {
    // Создаем SplitPane для разделения таблицы и генератора
    new BorderPane {
      center = new SplitPane {
        dividerPositions = 0.5@@  // Таблица занимает 60%, генератор 40%
        items.addAll(
          PasswordListView.createView(),
          PasswordGeneratorView.createView(passwordField => {
            // Колбэк для вставки пароля в поле ввода
            PasswordListView.setGeneratedPassword(passwordField)
          })
        )
      }
    }
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.