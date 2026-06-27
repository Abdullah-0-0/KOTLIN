package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueDebutPartie
import IHM.vue.VueFlip7
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.StackPane

class ControleurAjoutJoueur (vue: VueDebutPartie): EventHandler<ActionEvent>  {

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
        print(nbJoueur)

        if (nbJoueur == 2) {
            vue.champJoueur3.isDisable = false
            vue.labelJ3.style = vue.styleLabelJoueurAjoute
            vue.zoneSaisieJ3.style = "-fx-background-color: #123A6D; " +
                    "-fx-border-color: #F4C95D; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 0px; " +
                    "-fx-background-radius: 0px;"
            val textField = vue.champJoueur3
            textField.style = vue.styleTextField
            vue.zoneSaisieJ3.children.add(textField)
            vue.btnRetirer.opacity = 1.0
        }

        if (nbJoueur == 3) {
            vue.champJoueur4.isDisable = false
            vue.labelJ4.style = vue.styleLabelJoueurAjoute
            vue.zoneSaisieJ4.style = "-fx-background-color: #123A6D; " +
                    "-fx-border-color: #F4C95D; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 0px; " +
                    "-fx-background-radius: 0px;"
            val textField = vue.champJoueur4
            textField.style = vue.styleTextField
            vue.zoneSaisieJ4.children.add(textField)
            vue.btnAjouter.opacity = 0.5
        }
    }
}