import Vue.Accueil
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class Flip7App : Application() {

    override fun start(appli: Stage) {
        val accueil = Accueil()

        val scene = Scene(accueil, 1500.0, 800.0)

        appli.title = "Flip7"
        appli.scene = scene
        appli.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Flip7App::class.java)
}