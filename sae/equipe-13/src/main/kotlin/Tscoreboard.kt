import Vue.Scorebord
import iut.info1.flip7.IJoueur
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class MainApp : Application() {

    override fun start(stage: Stage) {

        val pierre =Joueur("pierre")
        val jean =Joueur("jean")
        val marie =Joueur("marie")
        val paul =Joueur("paul")

        val joueurs = arrayListOf<IJoueur>(
            pierre,
            jean,
            marie,
            paul
        )

        val scores = mapOf(
            0 to 10,
            1 to 20,
            2 to 30,
            3 to 15
        )

        val scoreboard = Scorebord(joueurs, scores)

        stage.scene = Scene(scoreboard, 1000.0, 800.0)
        stage.title = "Scoreboard"
        stage.show()
    }
}

fun main() {
    Application.launch(MainApp::class.java)
}