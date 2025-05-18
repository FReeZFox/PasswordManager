file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordGeneratorView.scala
### java.lang.AssertionError: assertion failed: duplicate class $anon#33952; previous was class $anon#33952

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 2385
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordGeneratorView.scala
text:
```scala
package UI

import scalafx.scene.control._
import scalafx.scene.layout.{VBox, HBox}
import scalafx.geometry.Insets
import core.PasswordGenerator
import scalafx.beans.binding.Bindings
import scalafx.beans.property.BooleanProperty

object PasswordGeneratorView {
  def createView(onPasswordGenerated: String => Unit): VBox = {
    val lengthSlider = new Slider(8, 48, 16) {
      showTickLabels = true
      showTickMarks = true
      snapToTicks = true
    }
    
    // Чекбоксы с привязкой свойств
    val includeLower = new CheckBox("Нижний регистр (a-z)") { selected = true }
    val includeUpper = new CheckBox("Верхний регистр (A-Z)") { selected = true }
    val includeNumbers = new CheckBox("Цифры (0-9)") { selected = true }
    val includeSpecial = new CheckBox("Спецсимволы (!@#...)") { selected = true }
    
    // Проверка, что хотя бы один чекбокс активен
    def validateCheckboxes(): Unit = {
      val atLeastOneSelected = includeLower.selected.value || 
                              includeUpper.selected.value || 
                              includeNumbers.selected.value || 
                              includeSpecial.selected.value
      
      if (!atLeastOneSelected) {
        includeLower.selected = true // Автоматически включаем нижний регистр
      }
    }
    
    // Назначаем обработчики для чекбоксов
    includeLower.selected.onChange { validateCheckboxes() }
    includeUpper.selected.onChange { validateCheckboxes() }
    includeNumbers.selected.onChange { validateCheckboxes() }
    includeSpecial.selected.onChange { validateCheckboxes() }
    
    val generateButton = new Button("Сгенерировать")
    val useButton = new Button("Использовать")

    generateButton.onAction = _ => {
      validateCheckboxes() // Дополнительная проверка перед генерацией
      
      val password = PasswordGenerator.generate(
        length = lengthSlider.value.toInt,
        includeLower = includeLower.selected.value,
        includeUpper = includeUpper.selected.value,
        includeNumbers = includeNumbers.selected.value,
        includeSpecial = includeSpecial.selected.value
      )
      useButton.text = password
    }

    useButton.onAction = _ => {
      if (!useButton.text.value.isEmpty) {
        onPasswordGenerated(useButton.text.value)
      }
    }

    new VBox(10,
      new Label("Генератор паролей") { style = "-fx-font-weight: bold;"@@ },
      new HBox(10,
        new Label("Длина:"),
        new Label() {
          text <== Bindings.createStringBinding(
            () => lengthSlider.value.toInt.toString,
            lengthSlider.value
          )
        }
      ),
      lengthSlider,
      includeLower,
      includeUpper,
      includeNumbers,
      includeSpecial,
      new HBox(10, generateButton, useButton)
    ) {
      padding = Insets(15)
      style = "-fx-border-color: lightgray; -fx-border-width: 1;"
    }
  }
}
```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.core.Scopes$MutableScope.enter(Scopes.scala:281)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.enterNoReplace(SymDenotations.scala:2083)
	dotty.tools.dotc.core.SymDenotations$ClassDenotation.enter(SymDenotations.scala:2074)
	dotty.tools.dotc.core.ContextOps$.enter(ContextOps.scala:21)
	dotty.tools.dotc.interactive.Interactive$.contextOfPath$$anonfun$2(Interactive.scala:302)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.interactive.Interactive$.contextOfPath(Interactive.scala:301)
	dotty.tools.dotc.interactive.Interactive$.contextOfPath(Interactive.scala:283)
	dotty.tools.dotc.interactive.Interactive$.contextOfPath(Interactive.scala:283)
	dotty.tools.dotc.interactive.Interactive$.contextOfPath(Interactive.scala:283)
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:95)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:363)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: duplicate class $anon#33952; previous was class $anon#33952