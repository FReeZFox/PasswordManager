error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/Main.scala:scala/Unit#
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: scala/Unit#
semanticdb not found
empty definition using fallback
non-local guesses:
	 -Unit#
	 -scala/Predef.Unit#
offset: 382
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/Main.scala
text:
```scala
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import UI.MainView

object PasswordManager extends JFXApp3 {
	override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Password Manager"
      resizable = false
      scene = new Scene(800, 600) {
        root = MainView.createView()
      }
    }
  } 
}

@main def run(): Uni@@t = PasswordManager.main(Array.empty)


```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Unit#