package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.geometry.Insets
import scalafx.beans.binding.Bindings
import core.PasswordGenerator

object PasswordGeneratorView {
  def createView(onUse: String => Unit): VBox = {
    val slider = new Slider(8, 32, 16) {
      showTickLabels = true
      showTickMarks = true
      snapToTicks = true
      majorTickUnit = 8
      minorTickCount = 7
      focusTraversable = false
    }

    val checkboxes = Seq(
      ("Нижний регистр (a-z)", true),
      ("Верхний регистр (A-Z)", true),
      ("Цифры (0-9)", true),
      ("Спецсимволы (!@#...)", true)
    ).map { case (text, isSelected) =>
      new CheckBox(text) 
      { 
        selected = isSelected 
        focusTraversable = false
      }
    }

    val passwordField = new TextField {
      editable = false
      focusTraversable = false
      contextMenu = new ContextMenu()
    }

    var currentStream: LazyList[String] = LazyList.empty

    def resetStream(): Unit = currentStream = LazyList.empty

    checkboxes
    .foreach { checkbox =>
      checkbox
      .selected
      .onChange {
        resetStream()
        val activeCount = checkboxes.count(_.selected.value)

        checkboxes
        .foreach { box =>
          box.disable = box.selected.value && activeCount == 1
        }
        if (activeCount == 0) checkboxes.head.selected = true
      }
    }

    slider.value.onChange { (_, _, _) => resetStream() }

    val generateButton = new Button("Сгенерировать") {
      focusTraversable = false
      onAction = _ => {
        if (currentStream.isEmpty) {
          currentStream = PasswordGenerator.generate(
            slider.value.toInt,
            checkboxes(0).selected.value,
            checkboxes(1).selected.value,
            checkboxes(2).selected.value,
            checkboxes(3).selected.value
          )
        }

        currentStream match {
          case head #:: tail =>
            passwordField.text = head
            currentStream = tail
          case _ =>
            passwordField.text = "Ошибка генерации"
        }
      }
    }

    val useButton = new Button("Использовать") {
      focusTraversable = false
      onAction = _ =>
        Option(passwordField.text.value)
          .filter(_.nonEmpty)
          .foreach(onUse)
    }

    new VBox(10) {
      padding = Insets(15)
      style = "-fx-border-color: lightgray; -fx-border-width: 1;"
      children = Seq(
        new Label("Генератор паролей") {
          style = "-fx-font-weight: bold;"
        },
        new HBox(10,
          new Label("Длина:"),
          new Label {
            text <== Bindings.createStringBinding(
              () => slider.value.toInt.toString,
              slider.value
            )
          }
        ),
        slider
      ) ++ checkboxes ++ Seq(passwordField, new HBox(10, generateButton, useButton))
    }
  }
}
