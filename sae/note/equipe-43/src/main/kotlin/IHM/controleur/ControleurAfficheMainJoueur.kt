package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueFlip7
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.etats.EtatPartie
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane

class ControleurAfficheMainJoueur(vue: VueFlip7, modele: ModeleFlip7, idJoueur:Int = -1, joueurCourant: Boolean = false, joueurSuivant: Boolean = false): EventHandler<ActionEvent> {
    val vue: VueFlip7
    val modele: ModeleFlip7
    var idJoueur : Int
    val joueurCourant : Boolean
    val joueurSuivant : Boolean

    init {
        this.vue = vue
        this.modele = modele
        this.idJoueur = idJoueur
        this.joueurCourant = joueurCourant
        this.joueurSuivant = joueurSuivant
    }
    override fun handle(event: ActionEvent?) {
        vue.cartesBox.children.clear()
        vue.actionBottonBox.children.clear()

        if (idJoueur != -1) {
            modele.mainJoueur = idJoueur
        }else if (joueurCourant){
            modele.mainJoueur = modele.flip7.joueurCourant
        }else if(joueurSuivant){
            var occ = 0
            while (modele.flip7.etatJoueur[modele.mainJoueur]!!.name != "JOUE_ENCORE" && occ < 10) {
                if (modele.mainJoueur == modele.nbJoueur - 1) {
                    modele.mainJoueur = 0
                } else {
                    modele.mainJoueur += 1
                }
                occ++
            }
        }

        val nbCartes = if (modele.flip7.main[modele.mainJoueur] == null) 0 else modele.flip7.main[modele.mainJoueur]!!.size


        val baseWidth = 120.0
        val step = 4.0

        val widthCartes = (baseWidth - nbCartes * step) /* peut etre faire du binding pour fair qlq chose de responsive*/
        // ratio en 2:3 (les images sont en 400x600)
        val heightCartes = widthCartes * 1.5

        if (nbCartes != 0) {
            for (i in 0..nbCartes-1){
                val carteStack = StackPane() // Empile les element (donc l'image et le bouton)

                val image = Image(modele.urlCarte(modele.flip7.main[modele.mainJoueur]!![i])) /* image de la carte a gere dans un controleurs*/
                val CarteIv = ImageView(image)

                CarteIv.fitWidth = widthCartes
                CarteIv.fitHeight = heightCartes
                CarteIv.isPreserveRatio = true

                val carteButton = Button()
                carteButton.prefWidth = widthCartes
                carteButton.prefHeight = heightCartes
                carteButton.style = "-fx-background-color: transparent;"

                carteStack.children.addAll(CarteIv, carteButton)
                vue.cartesBox.children.add(carteStack)
            }
        }

        for (i in 0..vue.joueursBox.children.size-1){
            if (i == modele.mainJoueur){
                vue.joueursBox.children[i].opacity = 1.0
                vue.joueursBox.children[i].style = "-fx-background-color: #123A6D;" +
                        "-fx-border-color: #FFFFFF;" +
                        "-fx-border-width: 2;"
            }else if(i == modele.flip7.joueurCourant){
                vue.joueursBox.children[i].opacity = 0.7
                vue.joueursBox.children[i].style = "-fx-background-color: #123A6D;" +
                        "-fx-border-color: #F4C95D;" +
                        "-fx-border-width: 2;"
            }else if(modele.flip7.etatPartie == EtatPartie.ATTENTE_CIBLE_STOP && modele.flip7.etatJoueur[i] == EtatJoueur.JOUE_ENCORE){
                vue.joueursBox.children[i].opacity = 0.9
                vue.joueursBox.children[i].style += "-fx-border-color: #8AFFFF;" +
                        "-fx-border-width: 2;"
            }else if(modele.flip7.etatPartie == EtatPartie.ATTENTE_CIBLE_3SUITE && modele.flip7.etatJoueur[i] == EtatJoueur.JOUE_ENCORE){
                vue.joueursBox.children[i].opacity = 0.9
                vue.joueursBox.children[i].style += "-fx-border-color: #A5FF8A;" +
                        "-fx-border-width: 2;"
            }else{
                vue.joueursBox.children[i].opacity = 0.5
                vue.joueursBox.children[i].style = "-fx-background-color: #123A6D;" +
                        "-fx-border-color: #F4C95D;" +
                        "-fx-border-width: 2;"
            }
        }

        if (modele.mainJoueur == modele.flip7.joueurCourant){
            vue.actionBottonBox.children.add(vue.stopButton)
        }else{
            vue.actionBottonBox.children.addAll(vue.freezeButton,vue.troisSuiteButton)
        }

        for (i in 0..modele.nbJoueur-1){
            vue.setEtatJoueur(i,
                modele.flip7.etatJoueur[i]!!,
                modele.flip7.joueurCourant)
        }

    }
}