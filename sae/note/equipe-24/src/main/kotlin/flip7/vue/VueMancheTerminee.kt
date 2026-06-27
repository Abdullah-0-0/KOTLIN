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

class VueMancheTerminee(numeroManche: Int, scores: List<ScoreLigne>) {

    val boutonProchaineManche: Button = Style.boutonPrincipal("PROCHAINE MANCHE")
    val fondSombre: Region = Region()
    val racine: StackPane

    init {
        fondSombre.style = "-fx-background-color: rgba(0, 0, 0, 0.5);"

        val titre = Style.texteContour("LA MANCHE $numeroManche EST\nTERMINÉE", Style.JAUNE, Style.MAGENTA, 24.0)
        titre.textAlignment = TextAlignment.CENTER

        val lignes = VBox(12.0)
        lignes.alignment = Pos.CENTER
        scores.forEach { lignes.children.add(ligneScore(it)) }

        val carte = VBox(20.0, titre, lignes, boutonProchaineManche)
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

internal fun ligneScore(score: ScoreLigne): HBox {
    val nom = Style.texteContour(score.nom, Style.MAGENTA, Style.JAUNE, 15.0)
    val pastille = HBox(nom)
    pastille.alignment = Pos.CENTER
    pastille.padding = Insets(8.0, 16.0, 8.0, 16.0)
    pastille.minWidth = 150.0
    pastille.style = """
        -fx-border-color: ${Style.JAUNE};
        -fx-border-width: 3;
        -fx-border-radius: 14;
        -fx-background-radius: 14;
    """

    val detail = Style.texteContour(
        "SCORE + ${score.scoreManche}  TOTAL = ${score.total}",
        Style.JAUNE, Style.MAGENTA, 15.0
    )

    val ligne = HBox(18.0, pastille, detail)
    ligne.alignment = Pos.CENTER_LEFT
    return ligne
}