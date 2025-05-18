error id: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/Main.scala:`<none>`.
file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: `<none>`.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 113
uri: file:///C:/Users/danch/Documents/ФиЛП%20-%20КР/passmanager/src/main/scala/Main.scala
text:
```scala
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import UI.MainView

object PasswordManager exte@@nds JFXApp3 {
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

@main def run(): Unit = PasswordManager.main(Array.empty)
```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.