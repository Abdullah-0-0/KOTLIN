package Controleur

import Vue.Accueil
import Vue.Flip7Jeu
import iut.info1.flip7.cartes.CarteNum
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage

class ControleurBoutonJouer(vueAccueil: Accueil) : EventHandler<ActionEvent> {
    private val accueil: Accueil
    init {
        this.accueil = vueAccueil
    }
    override fun handle(event: ActionEvent) {
        val bouton = event.source as Button
        val stage = bouton.scene.window as Stage

        val listeJoueur = mutableListOf<String>()

        for (i in accueil.champsPseudos) {
            listeJoueur.add(i.text)
        }

        val scoreFinal = accueil.choixScoreFinal.value
        val vueJeu = Flip7Jeu(listeJoueur, scoreFinal)
        vueJeu.majMain(vueJeu.main1, CarteNum(12))
        vueJeu.resetmains()
        val sceneJeu = Scene(vueJeu, 1500.0, 800.0)

        stage.scene = sceneJeu
        stage.show()
    }
}