package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueDebutPartie
import IHM.vue.VueFlip7
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.Label

class ControleurLancerFlip7(vue: VueDebutPartie, gameVue: VueFlip7): EventHandler<ActionEvent> {
    val vue: VueDebutPartie
    val gameVue: VueFlip7

    init {
        this.vue = vue
        this.gameVue = gameVue
    }

    override fun handle(event: ActionEvent) {
        var isGood = true
        var nbJoueur = 2

        if (vue.champJoueur1.text.isEmpty()){isGood = false}
        if (vue.champJoueur2.text.isEmpty()){isGood = false}
        if (!vue.champJoueur3.isDisable) {if (vue.champJoueur3.text.isEmpty()){isGood = false}else nbJoueur = 3}
        if (!vue.champJoueur4.isDisable) {if (vue.champJoueur4.text.isEmpty()){isGood = false}else nbJoueur = 4}
        if(!isGood){showWarningAlert("Tout les champs des joueurs ne sont pas remplie");return}
        try {
            val score = vue.champScoreFin.text.toInt()
            if (score < 50 || score > 200){showWarningAlert("le score doit etre compris entre 50 et 200");return}
        }catch(e:NumberFormatException){showWarningAlert("le champs du score doit etre remplie");return}

        if(!isGood){}
        if (isGood){
            val modele= ModeleFlip7(nbJoueur,vue.champJoueur1.text,vue.champJoueur2.text,vue.champJoueur3.text,vue.champJoueur4.text,vue.champScoreFin.text.toInt())
            vue.btnJouer.addEventHandler(ActionEvent.ACTION, ControleurInitGame(vue,gameVue,modele))


        }
    }

    fun showWarningAlert(msg: String) {
        val dialog = Alert(Alert.AlertType.ERROR)

        dialog.title = "Erreur lancement Flip7"
        dialog.headerText = "Impossible de lancer la partie"
        dialog.contentText = msg

        val pane = dialog.dialogPane

        pane.style = "-fx-background-color: #001436;"+
        "-fx-border-color: #F4C95D;"+
        "-fx-border-width: 3;"


        dialog.setOnShown {
            pane.applyCss()

            pane.lookup(".header-panel").let { header ->
                header.style = "-fx-background-color: #123A6D;"

                header.lookup(".label").style ="-fx-text-fill: #F4C95D;"+
                    "-fx-font-size: 20px;"+
                    "-fx-font-weight: bold;"+
                    "-fx-font-family: 'Idiqlat';"
                }


            pane.lookup(".content.label").style = "-fx-text-fill: #F4C95D;"+
                "-fx-font-size: 16px;"+
                "-fx-font-family: 'Idiqlat';"


            pane.lookup(".button").style = "-fx-background-color: #123A6D;"+
                    "-fx-text-fill: #F4C95D;"+
                    "-fx-border-color: #F4C95D;"+
                    "-fx-border-width: 2;"+
                    "-fx-font-family: 'Idiqlat';"+
                    "-fx-cursor: hand;"


        }

        dialog.showAndWait()
    }
}