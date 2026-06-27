package Controleur

import Vue.Flip7Jeu
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.etats.EtatPartie

class ControleurFinManche( vue: Flip7Jeu) {

    private val vue: Flip7Jeu
    init {
        this.vue = vue
    }
    private val controleurEtat = ControleurEtatJoueurs(vue)
    private val controleurNB = ControleurNBPiocheDefausse(vue)
    private val controleurFinPartie = ControleurFinPartie(vue)
    private val controleurMajMains = ControleurMajMains(vue)

    fun verifierFinManche() {
        var nbJoueursActifs = 0

        for (i in 0 until vue.LabelJoueurs.size) {
            val etat = vue.flip7.etatJoueur[i]

            if (etat == EtatJoueur.JOUE_ENCORE) {
                nbJoueursActifs++
            }
        }

        if (nbJoueursActifs == 0) {
            val score = vue.flip7.scoreManche()
            vue.resetmains()
            println(score)

            controleurFinPartie.verifierFinPartie()

            if (vue.flip7.etatPartie != EtatPartie.PARTIE_TERMINEE) {
                println("La partie continue : nouvelle manche")

                vue.flip7.nouvelleManche()

                println(vue.flip7.score)

                controleurMajMains.majToutesLesMains()
                controleurEtat.majEtatJoueurs()
                controleurNB.majCartes()

            } else {
                println("Partie terminée")
            }
        }
    }
}