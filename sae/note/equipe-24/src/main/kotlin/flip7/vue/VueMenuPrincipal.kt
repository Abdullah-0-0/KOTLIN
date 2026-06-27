package flip7.vue

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import javafx.scene.text.Text

class VueMenuPrincipal {
    val boutonPlay = Button("PLAY")
    val boutonRegles = Button("RÈGLES")
    val boutonQuitter = Button("QUITTER")
    val racine: StackPane

    init {
        val fond = StackPane()
        fond.style = "-fx-background-color: #00C0E8;"

        // titre
        val policeFlip = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 100.0)
        val police7 = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 150.0)
        // fonction pour créer l'effet de double stroke
        fun creerTexte(contenu: String, police: Font, couleur: Color, contour: Color, epaisseur: Double): StackPane {
            val base = Text(contenu)
            base.font = police
            base.fill = couleur

            val trait = Text(contenu)
            trait.font = police
            trait.stroke = contour
            trait.strokeWidth = epaisseur
            trait.fill = contour

            val blanc = Text(contenu)
            blanc.font = police
            blanc.stroke = Color.WHITE
            blanc.strokeWidth = epaisseur + 8.0
            blanc.fill = Color.WHITE

            return StackPane(blanc, trait, base)
        }

        val flip = creerTexte("FLIP", policeFlip, Color.web("#FFD900"), Color.web("#FF00FB"), 14.0)
        val sept = creerTexte("7", police7, Color.web("#00C0E8"), Color.web("#FF00FB"), 16.0)

        val conteneurTitre = HBox(10.0, flip, sept)
        conteneurTitre.alignment = Pos.BOTTOM_CENTER
        // le style des boutons
        val styleBouton = """
            -fx-background-color: #FF00FB;
            -fx-text-fill: #FFD900;
            -fx-font-size: 20px;
            -fx-font-weight: bold;
            -fx-background-radius: 25;
            -fx-border-color: white;
            -fx-border-width: 5;
            -fx-border-radius: 25;
            -fx-padding: 15 30 15 30;
        """
        // on applique le style aux boutons
        listOf(boutonPlay, boutonRegles, boutonQuitter).forEach {
            it.style = styleBouton
            it.prefWidth = 300.0
        }
        // mise en place verticale des différents composants
        val boiteBoutons = VBox(20.0, boutonPlay, boutonRegles, boutonQuitter)
        boiteBoutons.alignment = Pos.CENTER

        val dispositionCentrale = VBox(40.0, conteneurTitre, boiteBoutons)
        dispositionCentrale.alignment = Pos.CENTER
        // assemblage dans le stackPane principal
        racine = StackPane(fond, dispositionCentrale)
    }
}