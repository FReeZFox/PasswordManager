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

@main def run(): Unit = PasswordManager.main(Array.empty)

