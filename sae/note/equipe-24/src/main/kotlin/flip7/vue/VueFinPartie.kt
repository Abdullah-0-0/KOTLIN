package flip7.vue

import flip7.modele.ScoreLigne
import flip7.style.Style
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment

class VueFinPartie(gagnant: String, scores: List<ScoreLigne>) {

    val boutonMenu: Button = Style.boutonPrincipal("MENU")
    val boutonRejouer: Button = Style.boutonPrincipal("REJOUER")
    val fondSombre: Region = Region()
    val racine: StackPane

    init {
        fondSombre.style = "-fx-background-color: rgba(0, 0, 0, 0.5);"

        val titre = Style.texteContour("LA PARTIE EST\nTERMINÉE", Style.JAUNE, Style.MAGENTA, 24.0)
        titre.textAlignment = TextAlignment.CENTER

        val sousTitre = Style.texteContour("$gagnant a gagné !", Style.MAGENTA, Style.JAUNE, 18.0)

        val lignes = VBox(12.0)
        lignes.alignment = Pos.CENTER
        scores.forEach { lignes.children.add(ligneScore(it)) }

        val actions = HBox(28.0, boutonMenu, boutonRejouer)
        actions.alignment = Pos.CENTER

        val carte = VBox(18.0, titre, sousTitre, lignes, actions)
        carte.alignment = Pos.CENTER
        carte.padding = Insets(30.0)
        carte.maxWidth = 540.0
        carte.maxHeight = Region.USE_PREF_SIZE
        carte.style = """
            -fx-background-color: ${Style.BLANC};
            -fx-background-radius: 24;
            -fx-border-color: ${Style.JAUNE};
            -fx-border-width: 4;
            -fx-border-radius: 24;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 18, 0.2, 0, 6);
        """
        StackPane.setAlignment(carte, Pos.CENTER)

        racine = StackPane(fondSombre, carte)
    }
}