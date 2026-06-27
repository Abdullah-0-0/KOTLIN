package Controleur

import Vue.Flip7Jeu
import Vue.Scorebord
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage

class ControleurVueScore(vueJeu: Flip7Jeu) : EventHandler<ActionEvent> {
    private val vueJeu: Flip7Jeu
    init {
        this.vueJeu = vueJeu
    }
    override fun handle(event: ActionEvent) {
        val vueScore = Scorebord(vueJeu.listJoueur, vueJeu.flip7.score)
        val fenetreScore = Stage()
        fenetreScore.title = "Score des joueurs"
        val sceneScore = Scene(vueScore, 1500.0, 800.0)
        fenetreScore.scene = sceneScore

        vueScore.boutonFermer.onAction = ControleurFermerScore(fenetreScore)

        fenetreScore.show()
    }
}