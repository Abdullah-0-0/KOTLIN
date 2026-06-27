package Controleur

import Vue.Flip7Jeu
import Vue.PopUpFlip7
import iut.info1.flip7.etats.EtatPartie
import javafx.scene.control.Alert

class ControleurMainFlip7(vue: Flip7Jeu) {
    private val vue: Flip7Jeu
    init {
        this.vue = vue
    }

    private val controleurEtat = ControleurEtatJoueurs(vue)
    private val controleurNB = ControleurNBPiocheDefausse(vue)
    private val controleurFinPartie = ControleurFinPartie(vue)
    private val controleurMajMains = ControleurMajMains(vue)

    fun verifierMainFlip7() {
        for (i in 0 until vue.flip7.main.size){
            if (vue.flip7.outilsCarte.estFlip7(vue.flip7.main[i]!!)){
                val pop = PopUpFlip7(
                    "FLIP 7 !",
                    "Bravo ! Un ${vue.LabelJoueurs[i].text} a obtenu 7 cartes différentes.\nLa manche est terminée.",
                    "#0b7f2a"
                )

                pop.initOwner(vue.scene.window)
                pop.showAndWait()

                val score = vue.flip7.scoreManche()

                println("Score manche après Flip7 : $score")
                println("État partie après scoreManche : ${vue.flip7.etatPartie}")

                controleurFinPartie.verifierFinPartie()
                if (vue.flip7.etatPartie != EtatPartie.PARTIE_TERMINEE) {

                    println("Nouvelle manche après Flip7")

                    vue.flip7.nouvelleManche()

                    controleurEtat.majEtatJoueurs()
                    controleurNB.majCartes()
                    controleurMajMains.majToutesLesMains()
                } else {
                    println("Partie terminée après Flip7")
                }
            }
        }
    }
}