package IHM.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.layout.StackPane

class VueDebutPartie : BorderPane() {

    val champScoreFin : TextField = TextField("200")
    val champJoueur1  : TextField = TextField()
    val champJoueur2  : TextField = TextField()
    val champJoueur3  : TextField = TextField()
    val champJoueur4  : TextField = TextField()

    val btnJouer   : Button = Button()
    val btnAjouter : Button = Button()
    val btnRetirer : Button = Button()

    var labelJ1   : Label
    var labelJ2   : Label
    var labelJ3   : Label
    var labelJ4   : Label

    val styleLabelJoueurAjoute = "-fx-text-fill: #F4C95D; -fx-font-weight: bold; -fx-font-size: 14px; -fx-font-family: 'Idiqlat';"
    val styleLabelJoueurNonAjoute = "-fx-text-fill: #767676; -fx-font-weight: bold; -fx-font-size: 14px; -fx-font-family: 'Idiqlat';"

     var zoneSaisieJ1 : StackPane
     var zoneSaisieJ2 : StackPane
     var zoneSaisieJ3 : StackPane
     var zoneSaisieJ4 : StackPane

    val styleTextField = "-fx-background-color: transparent; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 0 0 0 10; " +
            "-fx-font-size: 15px;"

    init {
        style = "-fx-background-color: #001436;"
        padding = Insets(10.0)

        labelJ1 = Label("Joueur 1")
        labelJ2 = Label("Joueur 2")
        labelJ3 = Label("Joueur 3")
        labelJ4 = Label("Joueur 4")

        champJoueur1.promptText = "Entrer nom Joueur"
        champJoueur2.promptText = "Entrer nom Joueur"
        champJoueur3.promptText = "Entrer nom Joueur"
        champJoueur4.promptText = "Entrer nom Joueur"


        zoneSaisieJ1 = creerCadreJoueurAjoute(champJoueur1)
        zoneSaisieJ2 = creerCadreJoueurAjoute(champJoueur2)
        zoneSaisieJ3 = creerCadreJoueurNonAjoute()
        zoneSaisieJ4 = creerCadreJoueurNonAjoute()


        top = Top()
        center = Center()
    }


    fun creerCadreScore(champ: TextField): StackPane {
        val cadre = StackPane()
        cadre.setPrefSize(150.0, 35.0)
        cadre.alignment = Pos.CENTER
        cadre.style = "-fx-background-color: #123A6D; " +
                "-fx-border-color: #F4C95D; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 0px; " +
                "-fx-background-radius: 0px;"
        cadre.children.add(champ)
        return cadre
    }

    fun creerCadreJoueurAjoute(champ: TextField): StackPane {
        val cadre = StackPane()
        cadre.setPrefSize(400.0, 50.0)
        cadre.alignment = Pos.CENTER
        cadre.style = "-fx-background-color: #123A6D; " +
                "-fx-border-color: #F4C95D; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 0px; " +
                "-fx-background-radius: 0px;"
        cadre.children.add(champ)
        return cadre
    }

    fun creerCadreJoueurNonAjoute(): StackPane {
        val cadre = StackPane()
        cadre.setPrefSize(400.0, 50.0)
        cadre.alignment = Pos.CENTER
        cadre.style = "-fx-background-color: #06142e; " +
                "-fx-border-color: #5b4a26; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 0px; " +
                "-fx-background-radius: 0px;"
        return cadre
    }


    private fun Top(): StackPane {
        val boiteTop = StackPane()
        boiteTop.padding = Insets(0.0, 10.0, 0.0, 10.0)

        val logo = Image(javaClass.getResource("/img/Logo.png")?.toExternalForm()!!)
        val imagelogo = ImageView(logo)
        imagelogo.fitWidth = 310.0
        imagelogo.isPreserveRatio = true

        val ligneLogo = HBox()
        ligneLogo.alignment = Pos.TOP_CENTER
        ligneLogo.children.add(imagelogo)

        val labelScore = Label("Score fin de partie")
        labelScore.style = "-fx-text-fill: #f0c040; -fx-font-weight: bold; -fx-font-size: 16px; -fx-font-family: 'Idiqlat';"

        champScoreFin.setPrefSize(180.0, 35.0)
        champScoreFin.style = "-fx-background-color: transparent; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 18px; "

        val zoneSaisieScore = creerCadreScore(champScoreFin)

        val boiteScore = VBox(3.0)
        boiteScore.alignment = Pos.TOP_RIGHT
        boiteScore.children.addAll(labelScore, zoneSaisieScore)

        val ligneScore = HBox()
        ligneScore.alignment = Pos.TOP_RIGHT
        ligneScore.children.add(boiteScore)


        boiteTop.children.addAll(ligneLogo, ligneScore)

        StackPane.setAlignment(ligneLogo, Pos.TOP_CENTER)
        StackPane.setAlignment(ligneScore, Pos.TOP_RIGHT)

        return boiteTop
    }

    private fun Center(): VBox {
        val boutonJouer = Image(javaClass.getResource("/img/Jouer.png")?.toExternalForm()!!)
        val imageJouer = ImageView(boutonJouer)
        imageJouer.fitWidth  = 260.0
        imageJouer.isPreserveRatio = true

        btnJouer.graphic = imageJouer
        btnJouer.style = "-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0;"

        val grilleJoueurs = GrilleJoueurs()
        val boiteBoutons = BoiteBoutons()

        val boiteCenter = VBox(15.0)
        boiteCenter.alignment = Pos.CENTER
        boiteCenter.padding   = Insets(5.0, 20.0, 10.0, 20.0)

        boiteCenter.children.addAll(btnJouer, grilleJoueurs, boiteBoutons)

        return boiteCenter
    }

    private fun GrilleJoueurs(): GridPane {


        val tLarg = 380.0
        val tHaut = 45.0

        champJoueur1.setPrefSize(tLarg, tHaut)
        champJoueur2.setPrefSize(tLarg, tHaut)
        champJoueur3.setPrefSize(tLarg, tHaut)
        champJoueur4.setPrefSize(tLarg, tHaut)

        champJoueur1.style = styleTextField
        champJoueur2.style = styleTextField
        champJoueur3.style = styleTextField
        champJoueur4.style = styleTextField

        champJoueur1.isDisable = false
        champJoueur2.isDisable = false
        champJoueur3.isDisable = true
        champJoueur4.isDisable = true

        labelJ1.style = styleLabelJoueurAjoute
        val boiteJ1 = VBox(5.0)
        boiteJ1.alignment = Pos.CENTER

        boiteJ1.children.addAll(labelJ1, zoneSaisieJ1)

        labelJ2.style = styleLabelJoueurAjoute
        val boiteJ2 = VBox(5.0)
        boiteJ2.alignment = Pos.CENTER

        boiteJ2.children.addAll(labelJ2, zoneSaisieJ2)


        labelJ3.style = styleLabelJoueurNonAjoute
        val boiteJ3 = VBox(5.0)
        boiteJ3.alignment = Pos.CENTER

        boiteJ3.children.addAll(labelJ3, zoneSaisieJ3)


        labelJ4.style = styleLabelJoueurNonAjoute
        val boiteJ4 = VBox(5.0)
        boiteJ4.alignment = Pos.CENTER
        boiteJ4.children.addAll(labelJ4, zoneSaisieJ4)

        val grille = GridPane()
        grille.hgap = 25.0
        grille.vgap = 10.0
        grille.alignment = Pos.CENTER

        grille.add(boiteJ1, 0, 0)
        grille.add(boiteJ2, 1, 0)
        grille.add(boiteJ3, 0, 1)
        grille.add(boiteJ4, 1, 1)

        return grille
    }

    private fun BoiteBoutons(): HBox {
        val boutonAjouter = Image(javaClass.getResource("/img/AjoutJoueur.png")?.toExternalForm()!!)
        val imageAjouter = ImageView(boutonAjouter)
        imageAjouter.fitWidth  = 170.0
        imageAjouter.isPreserveRatio = true

        val boutonRetirer = Image(javaClass.getResource("/img/RetireJoueur.png")?.toExternalForm()!!)
        val imageRetirer = ImageView(boutonRetirer)
        imageRetirer.fitWidth  = 170.0
        imageRetirer.isPreserveRatio = true

        btnAjouter.graphic = imageAjouter
        btnAjouter.style = "-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0;"

        btnRetirer.graphic = imageRetirer
        btnRetirer.style = "-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0;"
        btnRetirer.opacity = 0.5
        val boite = HBox(20.0)
        boite.alignment = Pos.CENTER
        boite.children.addAll(btnAjouter, btnRetirer)

        return boite
    }


}