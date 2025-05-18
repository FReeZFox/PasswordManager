file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordGeneratorView.scala
### java.lang.AssertionError: assertion failed: duplicate class $anon#75042; previous was class $anon#75042

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 3833
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
    val lengthSlider = new Slider(8, 32, 16) {
      showTickLabels = true
      showTickMarks = true
      snapToTicks = true
      majorTickUnit = 8    // Расстояние между основными метками
      minorTickCount = 7   // Количество промежуточных меток между основными
      blockIncrement = 1   // Шаг изменения при клике
    }
    
    // Чекбоксы с привязкой свойств
    val includeLower = new CheckBox("Нижний регистр (a-z)") { selected = true }
    val includeUpper = new CheckBox("Верхний регистр (A-Z)") { selected = true }
    val includeNumbers = new CheckBox("Цифры (0-9)") { selected = true }
    val includeSpecial = new CheckBox("Спецсимволы (!@#...)") { selected = true }
    
        // Функция для обновления состояния чексбоксов
    def updateCheckboxStates(): Unit = {
      val checkboxes = List(includeLower, includeUpper, includeNumbers, includeSpecial)
      val selectedCount = checkboxes.count(_.selected.value)
      
      checkboxes.foreach { checkbox =>
        // Отключаем чексбокс, если он последний выбранный
        checkbox.disable = checkbox.selected.value && selectedCount == 1
        
        // Стиль для отключенного чексбокса
        if (checkbox.disable.value) {
          checkbox.style = "-fx-opacity: 1; -fx-text-fill: gray;"
        } else {
          checkbox.style = ""
        }
      }
    }

    // Назначаем обработчики для обновления состояния
    includeLower.selected.onChange { updateCheckboxStates() }
    includeUpper.selected.onChange { updateCheckboxStates() }
    includeNumbers.selected.onChange { updateCheckboxStates() }
    includeSpecial.selected.onChange { updateCheckboxStates() }

    // Инициализация состояния
    updateCheckboxStates()

    // Поле для отображения сгенерированного пароля
    val passwordField = new TextField {
      editable = false
      contextMenu = new ContextMenu()
      //style = "-fx-font-family: monospace; -fx-font-weight: bold;"
    }
    
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
      passwordField.text = password
    }

    useButton.onAction = _ => {
      if (!passwordField.text.value.isEmpty) {
        onPasswordGenerated(passwordField.text.value)
      }
    }

    new VBox(10,
      new Label("Генератор паролей") { style = "-fx-font-wei@@ght: bold;" },
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
      passwordField, // Добавляем поле для отображения пароля
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

java.lang.AssertionError: assertion failed: duplicate class $anon#75042; previous was class $anon#75042