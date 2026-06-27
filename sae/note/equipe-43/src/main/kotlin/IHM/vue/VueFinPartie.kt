package IHM.vue

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class VueFinPartie(joueursMap : Map<String, Int>) : VBox() {
    val joueursMap: Map<String, Int>
    val boutonRejouer: Button
    init {
        this.joueursMap = joueursMap
        spacing = 10.0
        alignment = Pos.CENTER
        style = "-fx-background-color: #02132B;"


        val logo = ImageView(Image("file:src/main/resources/img//Logo.png"))
        logo.isPreserveRatio = true
        logo.fitWidth = 180.0


        children.add(logo)

        val conteneurClassement = StackPane()
        conteneurClassement.alignment = Pos.CENTER

        val fondClassement = ImageView(Image("file:src/main/resources/img/FondClassemet.png"))
        fondClassement.isPreserveRatio = true

        widthProperty().addListener { _, _, nouvelleLargeur ->
            val largeur = nouvelleLargeur.toDouble()

            if (largeur <= 700) {
                fondClassement.fitWidth = 500.0
            }
            else if (largeur <= 1000) {
                fondClassement.fitWidth = 750.0
            }
            else {
                fondClassement.fitWidth = 900.0
            }
        }

        val contenuClassement = VBox(15.0)
        contenuClassement.alignment = Pos.CENTER

        val titre = Label("CLASSEMENT DE LA PARTIE :")
        titre.textFill = Color.web("#02132B")
        titre.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-Regular.ttf",24.0)

        widthProperty().addListener { _, _, nouvelleLargeur ->
            val largeur = nouvelleLargeur.toDouble()

            if (largeur <= 700) {
                titre.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-Regular.ttf", 18.0)
            }
            else if (largeur <= 1000) {
                titre.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-Regular.ttf", 24.0)
            }
            else {
                titre.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-Regular.ttf", 30.0)
            }
        }

        contenuClassement.children.add(titre)

        val joueurs = mutableListOf<Joueur>()
        for (i in joueursMap) {
            joueurs.add(Joueur(i.key, i.value))
        }
        joueurs.sortByDescending { it.score }

        if (joueurs.size == 2) {
            val ligne = HBox(20.0)
            ligne.alignment = Pos.CENTER
            ligne.children.add(carteJoueur(joueurs[0], "file:src/main/resources/img/CouronneOr.png"))
            ligne.children.add(carteJoueur(joueurs[1], "file:src/main/resources/img/CouronneArgent.png"))
            contenuClassement.children.add(ligne)
        }
        else if (joueurs.size == 3) {
            val ligne = HBox(15.0)
            ligne.alignment = Pos.CENTER
            ligne.children.add(carteJoueur(joueurs[0], "file:src/main/resources/img/CouronneOr.png"))
            ligne.children.add(carteJoueur(joueurs[1], "file:src/main/resources/img/CouronneArgent.png"))
            ligne.children.add(carteJoueur(joueurs[2], "file:src/main/resources/img/CouronneBronze.png"))
            contenuClassement.children.add(ligne)
        }
        else if (joueurs.size == 4) {
            val ligne1 = HBox(20.0)
            val ligne2 = HBox(20.0)
            ligne1.alignment = Pos.CENTER
            ligne2.alignment = Pos.CENTER

            ligne1.children.add(carteJoueur(joueurs[0], "file:src/main/resources/img/CouronneOr.png"))
            ligne1.children.add(carteJoueur(joueurs[1], "file:src/main/resources/img/CouronneArgent.png"))

            ligne2.children.add(carteJoueur(joueurs[2], "file:src/main/resources/img/CouronneBronze.png"))
            ligne2.children.add(carteJoueur(joueurs[3], "file:src/main/resources/img/CouronneChocolat.png"))

            contenuClassement.children.add(ligne1)
            contenuClassement.children.add(ligne2)
        }

        conteneurClassement.children.addAll(fondClassement, contenuClassement)
        children.add(conteneurClassement)

        val imageRejouer = ImageView(Image("file:src/main/resources/img/Rejouer.png"))
        imageRejouer.isPreserveRatio = true
        imageRejouer.fitWidth = 240.0


        boutonRejouer = Button()
        boutonRejouer.prefWidth = 240.0
        boutonRejouer.prefHeight = imageRejouer.fitWidth * 0.4
        boutonRejouer.style = "-fx-background-color: transparent;"


        val rejouerStack = StackPane()
        rejouerStack.children.addAll(imageRejouer, boutonRejouer)


        children.add(rejouerStack)
    }

    private fun carteJoueur(joueur: Joueur, cheminCouronne: String): StackPane {
        val bloc = StackPane()
        bloc.alignment = Pos.CENTER

        val fondJoueur = ImageView(Image("file:src/main/resources/img/FondJoueur.png"))
        fondJoueur.isPreserveRatio = true

        val dispositionInterne = HBox(40.0)
        dispositionInterne.alignment = Pos.CENTER

        val textes = VBox(2.0)
        textes.alignment = Pos.CENTER_LEFT

        val nomLabel = Label(joueur.nom)
        nomLabel.textFill = Color.web("#F4C95D")
        nomLabel.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-Light.ttf", 16.0)

        val scoreLabel = Label("Score : ${joueur.score}")
        scoreLabel.textFill = Color.web("#F4C95D")
        scoreLabel.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-ExtraLight.ttf", 12.0)

        textes.children.addAll(nomLabel, scoreLabel)

        val couronne = ImageView(Image(cheminCouronne))
        couronne.isPreserveRatio = true

        dispositionInterne.children.addAll(textes, couronne)

        bloc.children.addAll(fondJoueur, dispositionInterne)

        widthProperty().addListener { _, _, nouvelleLargeur ->
            val largeur = nouvelleLargeur.toDouble()

            if (largeur <= 700) {
                fondJoueur.fitWidth = 180.0
                nomLabel.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-Light.ttf", 12.0)
                scoreLabel.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-ExtraLight.ttf", 10.0)
                couronne.fitHeight = 20.0
                dispositionInterne.spacing = 10.0
            }
            else if (largeur <= 1000) {
                fondJoueur.fitWidth = 250.0
                nomLabel.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-Light.ttf", 16.0)
                scoreLabel.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-ExtraLight.ttf", 12.0)
                couronne.fitHeight = 30.0
                dispositionInterne.spacing = 20.0
            }
            else {
                fondJoueur.fitWidth = 320.0
                nomLabel.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-Light.ttf", 20.0)
                scoreLabel.font = Font.loadFont("file:src/main/resources/fonts/Idiqlat-ExtraLight.ttf", 14.0)
                couronne.fitHeight = 40.0
                dispositionInterne.spacing = 40.0
            }
        }

        return bloc
    }
}

data class Joueur(
    val nom: String,
    val score: Int
)