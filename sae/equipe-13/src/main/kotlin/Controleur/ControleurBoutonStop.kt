package Controleur

import Vue.Flip7Jeu
import iut.info1.flip7.etats.EtatPartie
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControleurBoutonStop(private val vue: Flip7Jeu) : EventHandler<ActionEvent> {

    private val controleurEtat = ControleurEtatJoueurs(vue)
    private val controleurFinManche = ControleurFinManche(vue)

    override fun handle(event: ActionEvent) {

        if (vue.flip7.etatPartie == EtatPartie.ATTENTE_CHOIX_JOUEUR) {
            vue.flip7.joueurCourantDitStop()
            controleurEtat.majEtatJoueurs()
            controleurFinManche.verifierFinManche()
            println(vue.flip7.etatJoueur)
        }
    }
}