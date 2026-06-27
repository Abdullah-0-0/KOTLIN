package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueFinPartie
import IHM.vue.VueFlip7
import iut.info1.flip7.etats.EtatPartie
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControleurCheckButton(vue: VueFlip7, modele: ModeleFlip7): EventHandler<ActionEvent>  {

    val vue: VueFlip7
    val modele: ModeleFlip7


    init {
        this.vue = vue
        this.modele = modele
    }

    override fun handle(event: ActionEvent?) {
        if(modele.flip7.etatPartie == EtatPartie.PARTIE_TERMINEE){
            val joueursMap = mutableMapOf<String, Int>()
            for (i in 0..modele.flip7.nbJoueurs-1){
                joueursMap[modele.flip7.joueurs[i].donneNom()] = modele.flip7.score[i]!!
            }
            val vueFin = VueFinPartie(joueursMap)
            vueFin.boutonRejouer.addEventHandler(ActionEvent.ACTION, ControleurRejouer(vueFin))
            vue.scene.root = vueFin
        }else  if(modele.flip7.etatPartie.name  == "MANCHE_TERMINEE"){
            vue.piocheButton.isDisable = true
            vue.stopButton.isDisable = true
            vue.troisSuiteButton.isDisable = true
            vue.freezeButton.isDisable = true
        }else if (modele.flip7.etatPartie.name  ==  "ATTENTE_CHOIX_JOUEUR"){
            vue.piocheButton.isDisable = false
            vue.stopButton.isDisable = false
            vue.troisSuiteButton.isDisable = true
            vue.freezeButton.isDisable = true
        }else if(modele.flip7.etatJoueur[modele.mainJoueur]!!.name == "PERDU" || modele.flip7.etatJoueur[modele.mainJoueur]!!.name  == "STOP"){
            vue.troisSuiteButton.isDisable = true
            vue.freezeButton.isDisable = true
        }else  if(modele.flip7.etatPartie.name  == "ATTENTE_CIBLE_STOP"){
            vue.piocheButton.isDisable = true
            vue.stopButton.isDisable = true
            vue.troisSuiteButton.isDisable = true
            vue.freezeButton.isDisable = false
        }else  if(modele.flip7.etatPartie.name  == "ATTENTE_CIBLE_3SUITE"){
            vue.piocheButton.isDisable = true
            vue.stopButton.isDisable = true
            vue.troisSuiteButton.isDisable = false
            vue.freezeButton.isDisable = true
        }

        if (vue.piocheButton.isDisable){
            vue.piocheStack.opacity = 0.5
            vue.piocheLabelBox.opacity = 0.5
        }else{
            vue.piocheStack.opacity = 1.0
            vue.piocheLabelBox.opacity = 1.0
        }

        if (vue.stopButton.isDisable){
            vue.stopButton.opacity = 0.5
        }else{
            vue.stopButton.opacity = 1.0
        }

        if (vue.troisSuiteButton.isDisable){
            vue.troisSuiteButton.opacity = 0.5
        }else{
            vue.troisSuiteButton.opacity = 1.0
        }

        if (vue.freezeButton.isDisable){
            vue.freezeButton.opacity = 0.5
        }else{
            vue.freezeButton.opacity = 1.0
        }
    }
}