package Vue

import Controleur.ControleurBoutonJouer
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import java.io.FileInputStream

class Accueil : BorderPane() {
    val choixNombreJoueurs = ChoiceBox<Int>()
    val choixScoreFinal = ChoiceBox<Int>()
    val boutonJouer = Button("Jouer")
    val champsPseudos = mutableListOf<TextField>()
    val regle = Label(
        """
        Règles du jeu :
        
        - Chaque joueur pioche une carte à son tour.
        - Le but est d’atteindre le score final choisi.
        - Si un joueur pioche deux cartes avec le même numéro, il perd la manche.
        - Le joueur qui à obtenu 7 cartes numérotée distinct a gagné la manche.
        - Le joueur qui atteint le score final gagne la partie.
        """
    )

    init {
        this.style = """
            -fx-background-color: linear-gradient(to bottom, #0b7f2a, #13d6d0);
        """


        val logo = ImageView(
            Image(FileInputStream("src/main/ressources/Assert/logo.png"))
        )
        logo.fitWidth = 420.0
        logo.isPreserveRatio = true


        val zoneLogo = VBox(logo)
        zoneLogo.alignment = Pos.CENTER
        zoneLogo.padding = Insets(30.0, 0.0, 20.0, 0.0)




        val blocJoueur = creeBlock("Combien de joueurs ?",choixNombreJoueurs)
        choixNombreJoueurs.items.addAll(2,3,4)
        choixNombreJoueurs.value = 2

        val zonePseudos = VBox(8.0)
        zonePseudos.alignment = Pos.CENTER
        zonePseudos.padding = Insets(15.0, 0.0, 0.0, 0.0)

        blocJoueur.children.add(zonePseudos)

        mettreAJourPseudos(zonePseudos, choixNombreJoueurs.value)
        choixNombreJoueurs.selectionModel.selectedItemProperty().addListener {_,_, nouveauNombre ->
            mettreAJourPseudos(zonePseudos, nouveauNombre)
        }


        val zoneGauche = VBox(blocJoueur)
        zoneGauche.alignment = Pos.CENTER_LEFT
        zoneGauche.padding = Insets(40.0, 0.0, 0.0, 60.0)


        val  blocScoreFinal = creeBlock("Score Finale ?", choixScoreFinal)
        choixScoreFinal.items.addAll(50,100,150,200)
        choixScoreFinal.value = 200


        val zoneDroite = VBox(blocScoreFinal)
        zoneDroite.alignment = Pos.CENTER_RIGHT
        zoneDroite.padding = Insets(40.0, 60.0, 0.0, 0.0)


        boutonJouer.prefWidth = 380.0
        boutonJouer.prefHeight = 115.0
        boutonJouer.font = Font.font("Arial", FontWeight.BOLD, 28.0)

        val zoneBouton = VBox(boutonJouer)
        zoneBouton.alignment = Pos.CENTER
        zoneBouton.padding = Insets(60.0, 0.0, 40.0, 0.0)

        val styleBoutonJouerNormal = """
            -fx-background-color: #cde447;
            -fx-background-radius: 40;
            -fx-text-fill: black;
            -fx-cursor: hand;
        """
        val styleBoutonJouerSurvol = """
            -fx-background-color: #b6c53a;
            -fx-background-radius: 40;
            -fx-text-fill: black;
            -fx-cursor: hand;
        """
        boutonJouer.style = styleBoutonJouerNormal
        boutonJouer.setOnMouseEntered { boutonJouer.style = styleBoutonJouerSurvol }
        boutonJouer.setOnMouseExited { boutonJouer.style = styleBoutonJouerNormal }

        regle.isWrapText = true
        regle.isWrapText = true
        regle.prefWidth = 540.0
        regle.prefHeight = 420.0
        regle.font = Font.font("Arial", 20.0)

        regle.style = """
            -fx-control-inner-background: #cde447;
            -fx-background-color: #cde447;
            -fx-background-radius: 12;  
            -fx-border-radius: 12;
            -fx-border-color: transparent;
            -fx-text-fill: black;
        """

        val zoneRegles = VBox(regle)
        zoneRegles.alignment = Pos.CENTER
        zoneRegles.padding = Insets(10.0)

        val espace1 = Region()
        val espace2 = Region()

        HBox.setHgrow(espace1, Priority.ALWAYS)
        HBox.setHgrow(espace2, Priority.ALWAYS)

        val zoneMilieu = HBox(30.0)
        zoneMilieu.alignment = Pos.CENTER
        zoneMilieu.padding = Insets(20.0, 40.0, 0.0, 40.0)
        zoneMilieu.children.addAll(blocJoueur, espace1, zoneRegles, espace2, blocScoreFinal)

        // controleur
        boutonJouer.onAction = ControleurBoutonJouer(this)

        this.center = zoneMilieu
        this.bottom = zoneBouton
        this.top =  zoneLogo
    }




    fun creeBlock(texte : String , choixbox : ChoiceBox<Int>) : VBox {
        val label = Label(texte)

        label.font = Font.font("Verdana", FontWeight.BOLD,28.0)
        label.textFill = Color.BLACK
        label.prefWidth = 330.0
        label.isWrapText = true
        label.prefHeight = 115.0
        label.alignment = Pos.CENTER
        label.padding = Insets(0.0, 20.0, 0.0, 20.0)
        label.style = """
            -fx-background-color: #cde447;
            -fx-background-radius: 12;
        """



        choixbox.prefWidth = 290.0
        choixbox.prefHeight = 28.0

        choixbox.style = """
            -fx-background-color: #dddddd;
            -fx-background-radius: 12;
        """

        val vbox = VBox()
        vbox.alignment = Pos.CENTER
        vbox.children.addAll(label,choixbox)
        return  vbox
    }

    fun mettreAJourPseudos(zonePseudos: VBox, nombreJoueurs: Int) {
        zonePseudos.children.clear()
        champsPseudos.clear()

        for (i in 1..nombreJoueurs) {
            val labelPseudo = Label("Pseudo joueur $i")
            labelPseudo.font = Font.font("Arial", FontWeight.BOLD, 14.0)
            labelPseudo.textFill = Color.BLACK

            val champPseudo = TextField()
            champPseudo.promptText = "Joueur $i"
            champPseudo.prefWidth = 290.0
            champPseudo.prefHeight = 30.0

            champPseudo.style = """
            -fx-background-color: #eeeeee;
            -fx-background-radius: 10;
        """

            val lignePseudo = VBox()
            lignePseudo.alignment = Pos.CENTER_LEFT
            lignePseudo.children.addAll(labelPseudo, champPseudo)

            zonePseudos.children.add(lignePseudo)
            champsPseudos.add(champPseudo)
        }
    }


}