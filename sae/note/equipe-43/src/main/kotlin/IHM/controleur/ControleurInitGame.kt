package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueDebutPartie
import IHM.vue.VueFlip7
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

class ControleurInitGame(startVue: VueDebutPartie, vue: VueFlip7, modele: ModeleFlip7): EventHandler<ActionEvent> {
    val startVue: VueDebutPartie
    val vue : VueFlip7
    val modele  : ModeleFlip7

    init {
        this.startVue = startVue
        this.vue= vue
        this.modele =modele
    }
    override fun handle(event: ActionEvent?) {
        startVue.scene.root = vue

        for (i in 0..modele.nbJoueur-1){
            val nameLabel = Label(modele.flip7.joueurs[i].donneNom())
            val scoreLabel = Label("Score : 0")

            val textBox = VBox(10.0)
            textBox.children.addAll(nameLabel, scoreLabel)
            val imageView: ImageView
            if (i == 0){
                imageView = ImageView(vue.imgPlay)
            }else{
                imageView = ImageView(vue.imgWait)
            }
            imageView.fitWidth = 40.0
            imageView.fitHeight = 40.0
            imageView.translateY = 10.0
            imageView.isPreserveRatio = true
            vue.joueurImages.add(imageView)

            val spacer = Region()
            HBox.setHgrow(spacer, Priority.ALWAYS)

            val content = HBox(10.0)
            content.children.addAll(textBox, spacer, imageView)

            val button = Button()
            button.graphic = content
            button.maxWidth = Double.MAX_VALUE
            button.maxHeight = Double.MAX_VALUE
            HBox.setHgrow(button, Priority.ALWAYS)

            vue.joueursBox.children.add(button)

            if(i!=0){
                button.opacity = 0.5
            }
            nameLabel.style = "-fx-font-size: 16px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #F4C95D;"+
                    "-fx-font-family: 'Idiqlat';"
            scoreLabel.style = "-fx-font-size: 12px;" +
                    "-fx-text-fill: #F4C95D;"+
                    "-fx-font-family: 'Idiqlat Light';"
            button.style = "-fx-background-color: #123A6D;" +
                    "-fx-border-color: #F4C95D;" +
                    "-fx-border-width: 2;"
        }
        vue.fixeControlleurJoueurBox(modele)
        vue.actionBottonBox.children.add(vue.stopButton)
        vue.joueurCourantLabel.text += modele.flip7.joueurs[modele.flip7.joueurCourant].donneNom()
        vue.etatPartieLabel.text =  modele.flip7.etatPartie.name
        vue.cartesBox.children.clear()
    }
}