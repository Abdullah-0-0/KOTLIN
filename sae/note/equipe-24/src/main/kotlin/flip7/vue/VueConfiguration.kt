package flip7.vue

import flip7.style.Style
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

class VueConfiguration {

    val bouton2: Button = Style.boutonCarre("2")
    val bouton3: Button = Style.boutonCarre("3")
    val bouton4: Button = Style.boutonCarre("4")

    val champJoueur1: TextField = Style.champTexte("Joueur 1")
    val champJoueur2: TextField = Style.champTexte("Joueur 2")
    val champJoueur3: TextField = Style.champTexte("Joueur 3")
    val champJoueur4: TextField = Style.champTexte("Joueur 4")

    val curseurScore: Slider = Slider(50.0, 200.0, 100.0)

    val boutonLancer: Button = Style.boutonPrincipal("LANCER")

    val fondSombre: Region = Region()

    val racine: StackPane

    init {
        fondSombre.style = "-fx-background-color: rgba(0, 0, 0, 0.5);"

        val titre = Style.texteContour("NOUVELLE PARTIE", Style.JAUNE, Style.MAGENTA, 30.0)

        val libelleNombre = Style.texteContour("NOMBRE DE JOUEURS", Style.CYAN, Style.MAGENTA, 18.0)
        val selecteur = HBox(16.0, bouton2, bouton3, bouton4)
        selecteur.alignment = Pos.CENTER

        val pseudos = VBox(
            10.0,
            ligneSaisie("NOM J1", champJoueur1),
            ligneSaisie("NOM J2", champJoueur2),
            ligneSaisie("NOM J3", champJoueur3),
            ligneSaisie("NOM J4", champJoueur4)
        )

        val valeurScore = Label(curseurScore.value.toInt().toString())
        valeurScore.style = "-fx-text-fill: ${Style.NOIR}; -fx-font-size: 18px; -fx-font-weight: bold;"
        curseurScore.valueProperty().addListener { _, _, nouvelle ->
            valeurScore.text = nouvelle.toInt().toString()
        }
        curseurScore.prefWidth = 260.0

        val borne50 = Label("50")
        val borne200 = Label("200")
        val styleBorne = "-fx-text-fill: ${Style.NOIR}; -fx-font-size: 13px; -fx-font-weight: bold;"
        borne50.style = styleBorne
        borne200.style = styleBorne
        val espace = Region()
        HBox.setHgrow(espace, Priority.ALWAYS)
        val bornes = HBox(borne50, espace, borne200)
        bornes.prefWidth = 260.0

        val blocScore = VBox(2.0, valeurScore, curseurScore, bornes)
        blocScore.alignment = Pos.CENTER

        val carte = VBox(
            16.0,
            titre,
            libelleNombre,
            selecteur,
            pseudos,
            blocScore,
            boutonLancer
        )
        carte.alignment = Pos.CENTER
        carte.padding = Insets(28.0)
        carte.maxWidth = 430.0
        carte.maxHeight = Region.USE_PREF_SIZE
        carte.style = """
            -fx-background-color: ${Style.BLANC};
            -fx-background-radius: 24;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 18, 0.2, 0, 6);
        """
        StackPane.setAlignment(carte, Pos.CENTER)

        racine = StackPane(fondSombre, carte)
    }


    private fun ligneSaisie(libelle: String, champ: TextField): HBox {
        val etiquette = Style.texteContour(libelle, Style.MAGENTA, Style.JAUNE, 16.0)
        val boiteEtiquette = HBox(etiquette)
        boiteEtiquette.alignment = Pos.CENTER_LEFT
        boiteEtiquette.minWidth = 90.0
        val ligne = HBox(12.0, boiteEtiquette, champ)
        ligne.alignment = Pos.CENTER_LEFT
        HBox.setHgrow(champ, Priority.ALWAYS)
        return ligne
    }
}