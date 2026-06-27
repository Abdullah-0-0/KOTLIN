package flip7.vue

import flip7.style.Style
import javafx.geometry.Pos
import javafx.scene.effect.DropShadow
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment

class CarteVue(valeur: String, grande: Boolean = true) : StackPane() {

    init {
        val largeur = if (grande) 84.0 else 48.0
        val hauteur = if (grande) 112.0 else 66.0
        dimensionner(this, largeur, hauteur)
        style = styleCarte()

        val estCourt = valeur.length <= 2
        val taille = when {
            grande && estCourt -> 44.0
            grande -> 20.0
            estCourt -> 26.0
            else -> 12.0
        }
        val texte = Text(valeur)
        texte.fill = Color.web(Style.CYAN)
        texte.font = Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, taille)
        texte.textAlignment = TextAlignment.CENTER
        val ombre = DropShadow()
        ombre.color = Color.web(Style.MAGENTA)
        ombre.radius = 0.0
        ombre.offsetX = if (grande) 2.5 else 1.5
        ombre.offsetY = if (grande) 2.5 else 1.5
        texte.effect = ombre

        if (grande) {
            val pied = Text("FLIP7")
            pied.fill = Color.web(Style.MAGENTA)
            pied.font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 11.0)
            val boite = VBox(8.0, texte, pied)
            boite.alignment = Pos.CENTER
            children.add(boite)
        } else {
            children.add(texte)
        }
    }

    private fun styleCarte(): String = """
        -fx-background-color: linear-gradient(to bottom, ${Style.JAUNE}, #FFFBE6);
        -fx-background-radius: 12;
        -fx-border-color: ${Style.MAGENTA};
        -fx-border-width: 4;
        -fx-border-radius: 12;
    """

    companion object {
        fun dos(grande: Boolean = true): StackPane {
            val largeur = if (grande) 84.0 else 48.0
            val hauteur = if (grande) 112.0 else 66.0
            val carte = StackPane()
            dimensionner(carte, largeur, hauteur)
            carte.style = """
                -fx-background-color: linear-gradient(to bottom, ${Style.JAUNE}, #FFFBE6);
                -fx-background-radius: 12;
                -fx-border-color: ${Style.MAGENTA};
                -fx-border-width: 4;
                -fx-border-radius: 12;
            """
            val logo = Style.texteContour("FLIP7", Style.JAUNE, Style.MAGENTA, if (grande) 24.0 else 16.0)
            logo.rotate = -20.0
            carte.children.add(logo)
            return carte
        }

        private fun dimensionner(region: Region, largeur: Double, hauteur: Double) {
            region.minWidth = largeur; region.prefWidth = largeur; region.maxWidth = largeur
            region.minHeight = hauteur; region.prefHeight = hauteur; region.maxHeight = hauteur
        }
    }
}