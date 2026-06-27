package Controleur

import Vue.Accueil
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage

class ControleurBoutonHome : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        val bouton = event.source as Button
        val stage = bouton.scene.window as Stage

        val largeur = stage.scene.width
        val hauteur = stage.scene.height

        val vueAccueil = Accueil()
        val sceneAccueil = Scene(vueAccueil, largeur, hauteur)

        stage.scene = sceneAccueil
        stage.show()
    }
}