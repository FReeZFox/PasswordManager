package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.geometry.Insets
import scalafx.beans.binding.Bindings
import core.PasswordGenerator

object PasswordGeneratorView {
  def createView(onUse: String => Unit): VBox = {
    val slider = new Slider(8, 32, 16) {
      showTickLabels = true; 
      showTickMarks = true; 
      snapToTicks = true
      majorTickUnit = 8; 
      minorTickCount = 7
    }

    val checkboxes = Seq(
      ("Нижний регистр (a-z)", true),
      ("Верхний регистр (A-Z)", true),
      ("Цифры (0-9)", true),
      ("Спецсимволы (!@#...)", true)
    ).map { 
      case (text, isSelected) =>
        new CheckBox(text) { selected = isSelected }
    }

    val passwordField = new TextField 
    { 
      editable = false 
      contextMenu = new ContextMenu()
    }

    checkboxes.foreach { checkbox =>
      checkbox
      .selected
      .onChange {
        val activeCount = checkboxes.count(_.selected.value)
        checkboxes.foreach { box =>
          box.disable = box.selected.value && activeCount == 1
        }
        if (activeCount == 0) checkboxes.head.selected = true
      }
    }

    val buttons = new HBox(10, 
      new Button("Сгенерировать") {
        onAction = _ => PasswordGenerator.generate(
          slider.value.toInt,
          checkboxes(0).selected.value,
          checkboxes(1).selected.value,
          checkboxes(2).selected.value,
          checkboxes(3).selected.value
        )
        .foreach(passwordField.text = _)
      },
      new Button("Использовать") {
        onAction = _ => Option(passwordField.text.value)
          .filter(_.nonEmpty)
          .foreach(onUse)
      }
    )

    new VBox(10) {
      padding = Insets(15)
      style = "-fx-border-color: lightgray; -fx-border-width: 1;"
      children = Seq(
        new Label("Генератор паролей") { style = "-fx-font-weight: bold;" },
        new HBox(10, 
          new Label("Длина:"), 
          new Label { text <== Bindings.createStringBinding(
            () => slider.value.toInt.toString, 
            slider.value
          )}
        ),
        slider
      ) ++ checkboxes ++ Seq(passwordField, buttons)
    }
  }
}