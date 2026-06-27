package flip7.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import javafx.scene.text.Text

class VueRegles {

    val boutonRetour = Button("RETOUR")
    val racine = StackPane()

    init {
        val fond = StackPane()
        fond.style = "-fx-background-color: #00C0E8;"

        // titre
        val policeTitre = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 60.0)
        fun creerTitre(texte: String): StackPane {
            val base = Text(texte); base.font = policeTitre; base.fill = Color.web("#FFD900")
            val trait = Text(texte); trait.font = policeTitre; trait.stroke = Color.web("#FF00FB"); trait.strokeWidth = 6.0; trait.fill = Color.web("#FF00FB")
            val blanc = Text(texte); blanc.font = policeTitre; blanc.stroke = Color.WHITE; blanc.strokeWidth = 10.0; blanc.fill = Color.WHITE
            return StackPane(blanc, trait, base)
        }
        val titreEmpile = creerTitre("Les Règles")

        // boite des regles
        val contenu = VBox(10.0)
        contenu.padding = Insets(20.0)
        contenu.style = "-fx-background-color: white; -fx-background-radius: 40; -fx-border-color: #FF00FB; -fx-border-width: 6; -fx-border-radius: 40;"

        // texte des règles
        contenu.children.addAll(
            ligneTexte("BUT : ATTEINDRE LE SCORE CIBLE", true),
            ligneTexte("Piocher pour accumuler des points ou s'arrêter pour sécuriser."),
            ligneTexte("DECK :", true),
            ligneTexte("• Cartes 0-12 : Rapportent leur valeur."),
            ligneTexte("• Seconde Chance : Annule le premier doublon."),
            ligneTexte("• Flip 3 : Force à piocher 3 cartes."),
            ligneTexte("• Bonus (+2/+10) : S'ajoutent au score."),
            ligneTexte("• Multiplicateurs (x2) : Multiplient le total."),
            ligneTexte("FIN DE TOUR :", true),
            ligneTexte("• Arrêt : Valide tes points."),
            ligneTexte("• Doublon : Annule le tour (0 point)."),
            ligneTexte("• Flip 7 : Aligner 7 cartes donne +15 points.")
        )

        // bouton retour
        boutonRetour.style = "-fx-background-color: #FF00FB; -fx-text-fill: #FFD900; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 20; -fx-border-color: white; -fx-border-width: 4; -fx-border-radius: 20; -fx-padding: 8 20 8 20;"

        val layout = VBox(15.0, titreEmpile, contenu, boutonRetour)
        layout.alignment = Pos.CENTER
        layout.padding = Insets(15.0)
        racine.children.addAll(fond, layout)
    }

    private fun ligneTexte(txt: String, estTitre: Boolean = false): Text {
        val t = Text(txt)
        t.font = Font.font("Arial", FontWeight.BOLD, 18.0)
        t.fill = if (estTitre) Color.web("#FF00FB") else Color.web("#00C0E8")
        t.stroke = Color.BLACK
        t.strokeWidth = 0.3
        return t
    }
}