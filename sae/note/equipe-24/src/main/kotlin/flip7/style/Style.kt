package flip7.style

import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.Text

object Style {

    // code couleurs
    const val MAGENTA = "#FF00FB"
    const val JAUNE = "#FFD900"
    const val CYAN = "#00C0E8"
    const val BLANC = "#FFFFFF"
    const val NOIR = "#222222"
    const val GRIS_BORDURE = "#CCCCCC"

    // fonction pour contour des textes
    fun texteContour(contenu: String, remplissage: String, contour: String, taille: Double): Text {
        val texte = Text(contenu)
        texte.fill = Color.web(remplissage)
        texte.stroke = Color.web(contour)
        texte.strokeWidth = 1.5
        texte.font = Font.font("Arial", FontWeight.BOLD, taille)
        return texte
    }

    // style des boutons boutons
    fun boutonPrincipal(texte: String): Button {
        val bouton = Button(texte)
        bouton.style = """
            -fx-background-color: $MAGENTA;
            -fx-text-fill: $JAUNE;
            -fx-font-size: 20px;
            -fx-font-weight: bold;
            -fx-background-radius: 20;
            -fx-border-color: $JAUNE;
            -fx-border-width: 8;
            -fx-border-radius: 16;
            -fx-padding: 15 30 15 30;
            -fx-cursor: hand;
        """.trimIndent()
        return bouton
    }

    // bouton carre arrondi (selecteur 2 / 3 / 4) : fond cyan, chiffre magenta
    fun boutonCarre(texte: String): Button {
        val bouton = Button(texte)
        bouton.style = """
            -fx-background-color: $CYAN;
            -fx-text-fill: $MAGENTA;
            -fx-font-size: 24px;
            -fx-font-weight: bold;
            -fx-min-width: 64; -fx-min-height: 64;
            -fx-max-width: 64; -fx-max-height: 64;
            -fx-background-radius: 18;
            -fx-cursor: hand;
        """.trimIndent()
        return bouton
    }

    // champ de saisie : fond blanc, fine bordure grise, coins arrondis
    fun champTexte(invite: String): TextField {
        val champ = TextField()
        champ.promptText = invite
        champ.style = """
            -fx-background-color: $BLANC;
            -fx-border-color: $GRIS_BORDURE;
            -fx-border-width: 1;
            -fx-border-radius: 6;
            -fx-background-radius: 6;
            -fx-padding: 8;
            -fx-font-size: 14px;
        """.trimIndent()
        return champ
    }
}