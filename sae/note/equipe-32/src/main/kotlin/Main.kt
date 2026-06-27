package iut.info1.flip7.prototype

import iut.info1.flip7.prototype.controleur.AccueilController
import javafx.application.Application
import javafx.stage.Stage

class Main : Application() {
    override fun start(stagePrincipal: Stage) {
        val accueilController = AccueilController(stagePrincipal)

        stagePrincipal.title = "Flip7 - (equipe-32)"

        stagePrincipal.scene = accueilController.construireScene()
        stagePrincipal.isFullScreen = true
        stagePrincipal.fullScreenExitHint = ""
        stagePrincipal.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}