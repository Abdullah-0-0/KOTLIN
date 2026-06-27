package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueFlip7
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import javafx.scene.layout.VBox

class ControleurCheckEndManche(vue: VueFlip7, modele: ModeleFlip7): EventHandler<ActionEvent>  {

    val vue: VueFlip7
    val modele: ModeleFlip7

    init {
        this.vue = vue
        this.modele = modele
    }

    override fun handle(event: ActionEvent?) {
        if (modele.flip7.etatPartie.name == "MANCHE_TERMINEE"){
            modele.flip7.scoreManche()
            vue.joueursBox.children.clear()
            vue.joueurImages.clear()
            for (i in 0..modele.nbJoueur-1){
                val nameLabel = Label(modele.flip7.joueurs[i].donneNom())
                val scoreLabel = Label("Score : " + modele.flip7.score[i])

                val textBox = VBox(10.0)
                textBox.children.addAll(nameLabel, scoreLabel)

                val imageView = ImageView(vue.imgWait)
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
                button.addEventHandler(ActionEvent.ACTION, ControleurAfficheMainJoueur(vue, modele, i))
                button.addEventHandler(ActionEvent.ACTION, ControleurCheckButton(vue, modele))
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

            vue.etatPartieLabel.text =  modele.flip7.etatPartie.name

            if (modele.flip7.etatPartie.name == "NOUVELLE_MANCHE"){
                modele.flip7.nouvelleManche()
                modele.mainJoueur = modele.flip7.joueurCourant
                modele.manche +=1
                vue.mancheLabel.text = "MANCHE "+ modele.manche
                vue.joueurCourantLabel.text = "Au tour de " + modele.flip7.joueurs[modele.flip7.joueurCourant].donneNom()
                vue.etatPartieLabel.text =  modele.flip7.etatPartie.name
                vue.historiqueNouvelleManche(modele.manche)
            }

            val bouton = vue.joueursBox.children[modele.mainJoueur] as Button
            bouton.fire()
        }
    }
}