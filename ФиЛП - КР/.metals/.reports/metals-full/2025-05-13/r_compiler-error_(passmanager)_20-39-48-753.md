file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordListView.scala
### java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      = new ImageView(
  new Image(getClass.getResourceAsStream("/eye-closed.png")) {
    fitWidth = 16
    fitHeight = 16
  } style _root_.scala.Predef.???
) # -1,
parent span = <4327..4561>,
child       = new Image(getClass.getResourceAsStream("/eye-closed.png")) {
  fitWidth = 16
  fitHeight = 16
} style _root_.scala.Predef.??? # -1,
child span  = [4341..4501..4576]

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 4988
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/UI/PasswordListView.scala
text:
```scala
// package UI

// import scalafx.scene.control._
// import scalafx.scene.layout._
// import models.PasswordEntry
// import core.PasswordList

// object PasswordListView {
//   def createView(): TableView[PasswordEntry] = {
//     new TableView[PasswordEntry] {
//       items = PasswordList.entries
//       prefWidth = 430
//       minWidth = 430
//       maxWidth = 430
//       prefHeight = 580
//       minHeight = 580
//       maxHeight = 580
      
//       columns ++= List(
//         new TableColumn[PasswordEntry, String]("Сервис") {
//           cellValueFactory = _.value.service
//           prefWidth = 130
//           resizable = false
          
//           // Явно указываем тип для cellFactory
//           cellFactory = { (col: TableColumn[PasswordEntry, String]) =>
//             new TableCell[PasswordEntry, String] {
//               item.onChange { (_, _, newValue) =>
//                 text = if (newValue != null) newValue else ""
//                 tooltip = if (newValue != null) new Tooltip(newValue) else null
//               }
//             }
//           }
//         },
//         new TableColumn[PasswordEntry, String]("Логин") {
//           cellValueFactory = _.value.username
//           prefWidth = 150
//           resizable = false
          
//           cellFactory = { (col: TableColumn[PasswordEntry, String]) =>
//             new TableCell[PasswordEntry, String] {
//               item.onChange { (_, _, newValue) =>
//                 text = if (newValue != null) newValue else ""
//                 tooltip = if (newValue != null) new Tooltip(newValue) else null
//               }
//             }
//           }
//         },
//         new TableColumn[PasswordEntry, String]("Пароль") {
//           cellValueFactory = _.value.password
//           prefWidth = 150
//           resizable = false
          
//           cellFactory = { (col: TableColumn[PasswordEntry, String]) =>
//             new TableCell[PasswordEntry, String] {
//               item.onChange { (_, _, newValue) =>
//                 text = if (newValue != null) newValue else ""
//                 tooltip = if (newValue != null) new Tooltip(newValue) else null
//               }
//             }
//           }
//         }
//       )
//       columnResizePolicy = TableView.ConstrainedResizePolicy
//     }
//   }
// }

package UI

import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.scene.image.{Image, ImageView}
import models.PasswordEntry
import core.PasswordList

object PasswordListView {
  def createView(): TableView[PasswordEntry] = {
    new TableView[PasswordEntry] {
      items = PasswordList.entries
      prefWidth = 430
      minWidth = 430
      maxWidth = 430
      prefHeight = 580
      minHeight = 580
      maxHeight = 580
      
      columns ++= List(
        new TableColumn[PasswordEntry, String]("Сервис") {
          cellValueFactory = _.value.service
          prefWidth = 130
          resizable = false
          
          cellFactory = { (col: TableColumn[PasswordEntry, String]) =>
            new TableCell[PasswordEntry, String] {
              item.onChange { (_, _, newValue) =>
                text = if (newValue != null) newValue else ""
                tooltip = if (newValue != null) new Tooltip(newValue) else null
              }
            }
          }
        },
        new TableColumn[PasswordEntry, String]("Логин") {
          cellValueFactory = _.value.username
          prefWidth = 150
          resizable = false
          
          cellFactory = { (col: TableColumn[PasswordEntry, String]) =>
            new TableCell[PasswordEntry, String] {
              item.onChange { (_, _, newValue) =>
                text = if (newValue != null) newValue else ""
                tooltip = if (newValue != null) new Tooltip(newValue) else null
              }
            }
          }
        },
        new TableColumn[PasswordEntry, String]("Пароль") {
          cellValueFactory = _.value.password
          prefWidth = 150
          resizable = false
          
          cellFactory = { (col: TableColumn[PasswordEntry, String]) =>
            new TableCell[PasswordEntry, String] {
              // Создаем кнопку с иконкой глаза
              private val toggleButton = new Button {
                graphic = new ImageView(new Image(getClass.getResourceAsStream("/eye-closed.png")) {
                  fitWidth = 16
                  fitHeight = 16
                }
                style = "-fx-background-color: transparent; -fx-padding: 0;"
              }
              
              // Состояние видимости пароля
              private var passwordVisible = false
              
              // Обработчик нажатия на кнопку
              toggleButton.onAction = _ => {
                passwordVisible = !passwordVisible
                updatePasswordDisplay()
                updateButtonIcon()
              }
              
              // Обновляем отображение@@ пароля
              private def updatePasswordDisplay(): Unit = {
                if (item.value != null) {
                  text = if (passwordVisible) item.value else "•" * item.value.length
                  tooltip = if (passwordVisible) null else new Tooltip(item.value)
                } else {
                  text = ""
                  tooltip = null
                }
              }
              
              // Обновляем иконку кнопки
              private def updateButtonIcon(): Unit = {
                val iconPath = if (passwordVisible) "/eye-open.png" else "/eye-closed.png"
                toggleButton.graphic = new ImageView(new Image(getClass.getResourceAsStream(iconPath))) {
                  fitWidth = 16
                  fitHeight = 16
                }
              }
              
              // Инициализация ячейки
              override def startEdit(): Unit = {
                super.startEdit()
                graphic = null
              }
              
              override def cancelEdit(): Unit = {
                super.cancelEdit()
                updateGraphic()
              }
              
              private def updateGraphic(): Unit = {
                if (empty || item.value == null) {
                  graphic = null
                } else {
                  graphic = toggleButton
                }
              }
              
              item.onChange { (_, _, newValue) =>
                if (newValue != null) {
                  updatePasswordDisplay()
                  updateButtonIcon()
                  updateGraphic()
                } else {
                  text = ""
                  graphic = null
                  tooltip = null
                }
              }
              
              // Первоначальная настройка
              updatePasswordDisplay()
              updateButtonIcon()
              updateGraphic()
            }
          }
        }
      )
      columnResizePolicy = TableView.ConstrainedResizePolicy
    }
  }
}
```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:177)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:207)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:207)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:207)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:207)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:207)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:207)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:207)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:207)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:207)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:207)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:207)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:207)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:207)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:207)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:202)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:207)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:334)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:207)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:228)
	dotty.tools.dotc.parsing.Parser.parse$$anonfun$1(ParserPhase.scala:39)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:503)
	dotty.tools.dotc.parsing.Parser.parse(ParserPhase.scala:40)
	dotty.tools.dotc.parsing.Parser.$anonfun$2(ParserPhase.scala:52)
	scala.collection.Iterator$$anon$6.hasNext(Iterator.scala:479)
	scala.collection.Iterator$$anon$9.hasNext(Iterator.scala:583)
	scala.collection.immutable.List.prependedAll(List.scala:152)
	scala.collection.immutable.List$.from(List.scala:685)
	scala.collection.immutable.List$.from(List.scala:682)
	scala.collection.IterableOps$WithFilter.map(Iterable.scala:900)
	dotty.tools.dotc.parsing.Parser.runOn(ParserPhase.scala:51)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:343)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1323)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:336)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:384)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:396)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:69)
	dotty.tools.dotc.Run.compileUnits(Run.scala:396)
	dotty.tools.dotc.Run.compileSources(Run.scala:282)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:161)
	dotty.tools.pc.MetalsDriver.run(MetalsDriver.scala:47)
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:40)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:363)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      = new ImageView(
  new Image(getClass.getResourceAsStream("/eye-closed.png")) {
    fitWidth = 16
    fitHeight = 16
  } style _root_.scala.Predef.???
) # -1,
parent span = <4327..4561>,
child       = new Image(getClass.getResourceAsStream("/eye-closed.png")) {
  fitWidth = 16
  fitHeight = 16
} style _root_.scala.Predef.??? # -1,
child span  = [4341..4501..4576]