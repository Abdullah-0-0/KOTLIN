package Controleur

import Vue.Flip7Jeu
import iut.info1.flip7.etats.EtatJoueur
import Vue.PopUpFlip7
import javafx.scene.control.Alert

class ControleurEtatJoueurs(private val vue: Flip7Jeu) {

    fun majEtatJoueurs() {
        for (i in 0 until vue.LabelJoueurs.size) {
            val etat = vue.flip7.etatJoueur[i]

            if (etat == EtatJoueur.PERDU) {
                afficherPopPerduSiBesoin(i)
                vue.resetmain(vue.selecMain(i))
                desactiverJoueur(i)

            } else if (etat == EtatJoueur.STOP) {
                desactiverJoueur(i)

            } else if (i == vue.flip7.joueurCourant) {
                joueurCourant(i)

            } else {
                activerJoueur(i)
            }
        }
    }

    private fun desactiverJoueur(i: Int) {
        vue.LabelJoueurs[i].isDisable = true
        vue.LabelJoueurs[i].opacity = 0.4

        vue.LabelJoueurs[i].style = """
            -fx-background-color: gray;
            -fx-background-radius: 100;
            -fx-border-color: black;
            -fx-border-width: 3;
            -fx-border-radius: 90;
            -fx-text-fill: white;
            -fx-font-size: 20;
            -fx-font-weight: bold;
        """
    }

    private fun joueurCourant(i: Int) {
        vue.LabelJoueurs[i].isDisable = false
        vue.LabelJoueurs[i].opacity = 1.0

        vue.LabelJoueurs[i].style = """
            -fx-background-color: red;
            -fx-background-radius: 100;
            -fx-border-color: white;
            -fx-border-width: 4;
            -fx-border-radius: 90;
            -fx-text-fill: white;
            -fx-font-size: 20;
            -fx-font-weight: bold;
        """
    }

    private fun activerJoueur(i: Int) {
        vue.LabelJoueurs[i].isDisable = false
        vue.LabelJoueurs[i].opacity = 1.0

        vue.LabelJoueurs[i].style = """
            -fx-background-color: white;
            -fx-background-radius: 100;
            -fx-border-color: black;
            -fx-border-width: 3;
            -fx-border-radius: 90;
            -fx-text-fill: red;
            -fx-font-size: 20;
            -fx-font-weight: bold;
        """
    }

    private fun afficherPopPerduSiBesoin(i: Int) {
        if (!vue.joueursPerdusNotifies.contains(i)) {
            vue.joueursPerdusNotifies.add(i)

            val pop = PopUpFlip7(
                "Joueur perdu",
                "${vue.LabelJoueurs[i].text} a perdu la manche.\nIl a pioché un doublon.",
                "#d62828"
            )

            pop.initOwner(vue.scene.window)
            pop.showAndWait()
        }
    }
}