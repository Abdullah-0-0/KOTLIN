package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueDebutPartie
import IHM.vue.VueFlip7
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControleurRetireJoueur (vue: VueDebutPartie): EventHandler<ActionEvent>  {

    val vue: VueDebutPartie

    init {
        this.vue = vue
    }

    override fun handle(event: ActionEvent?) {
        var nbJoueur = 0
        if (vue.champJoueur1.isDisable == false) {nbJoueur++}
        if (vue.champJoueur2.isDisable == false) {nbJoueur++}
        if (vue.champJoueur3.isDisable == false) {nbJoueur++}
        if (vue.champJoueur4.isDisable == false) {nbJoueur++}

        if (nbJoueur == 3) {
            vue.champJoueur3.isDisable = true
            vue.labelJ3.style = vue.styleLabelJoueurNonAjoute
            vue.zoneSaisieJ3.style= "-fx-background-color: #06142e; " +
                    "-fx-border-color: #5b4a26; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 0px; " +
                    "-fx-background-radius: 0px;"
            vue.zoneSaisieJ3.children.clear()
            vue.btnRetirer.opacity = 0.5
        }

        if (nbJoueur == 4) {
            vue.champJoueur4.isDisable = true
            vue.labelJ4.style = vue.styleLabelJoueurNonAjoute
            vue.zoneSaisieJ4.style= "-fx-background-color: #06142e; " +
                    "-fx-border-color: #5b4a26; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 0px; " +
                    "-fx-background-radius: 0px;"
            vue.zoneSaisieJ4.children.clear()
            vue.btnAjouter.opacity = 1.0
        }
    }
}