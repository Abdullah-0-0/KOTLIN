package Controleur

import Vue.Flip7Jeu
import Vue.PopUpFlip7
import iut.info1.flip7.etats.EtatPartie
import javafx.scene.control.Alert

class ControleurFinPartie( vue: Flip7Jeu) {
    private val vue: Flip7Jeu
    init {
        this.vue = vue
    }

    fun verifierFinPartie() {
        if (vue.flip7.etatPartie == EtatPartie.PARTIE_TERMINEE) {

            val pop = PopUpFlip7(
                "PARTIE TERMINE",
                "Un joueur a obtenu le score finale",
                "#0b7f2a"
            )

            pop.initOwner(vue.scene.window)
            pop.showAndWait()

            vue.boutonPioche.isDisable = true
            vue.boutonStop.isDisable = true

            for (label in vue.LabelJoueurs) {
                label.isDisable = true
                label.opacity = 0.4
            }

            println("Partie terminée")
        }
    }
}