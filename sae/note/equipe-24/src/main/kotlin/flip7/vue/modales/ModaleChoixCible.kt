package flip7.vue.modales

import flip7.style.Style
import flip7.vue.CarteVue
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment

class ModaleChoixCible(titre: String, cibles: List<String>, carteJouee: String? = null) {

    val boutonsCibles: Map<String, Button>
    val fondSombre: Region = Region()
    val racine: StackPane

    init {
        fondSombre.style = "-fx-background-color: rgba(0, 0, 0, 0.5);"

        val titreTexte = Style.texteContour(titre, Style.JAUNE, Style.MAGENTA, 19.0)
        titreTexte.textAlignment = TextAlignment.CENTER

        val carte = VBox(18.0)
        carte.alignment = Pos.CENTER
        carte.children.add(titreTexte)


        if (carteJouee != null) {
            carte.children.add(CarteVue(carteJouee, grande = true))
        }

        val boutons = LinkedHashMap<String, Button>()
        val rangee = HBox(20.0)
        rangee.alignment = Pos.CENTER
        cibles.forEach { nom ->
            val bouton = boutonCible(nom)
            boutons[nom] = bouton
            rangee.children.add(bouton)
        }
        boutonsCibles = boutons
        carte.children.add(rangee)

        carte.padding = Insets(30.0)
        carte.maxWidth = 500.0
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

    private fun boutonCible(nom: String): Button {
        val bouton = Button(nom)
        val normal = styleCible(Style.BLANC)
        val survol = styleCible("#FFE9F6")
        bouton.style = normal
        bouton.setOnMouseEntered { bouton.style = survol }
        bouton.setOnMouseExited { bouton.style = normal }
        return bouton
    }

    private fun styleCible(fond: String): String = """
        -fx-background-color: $fond;
        -fx-text-fill: ${Style.MAGENTA};
        -fx-font-size: 16px;
        -fx-font-weight: bold;
        -fx-background-radius: 14;
        -fx-border-color: ${Style.MAGENTA};
        -fx-border-width: 2;
        -fx-border-radius: 14;
        -fx-padding: 10 22 10 22;
        -fx-cursor: hand;
    """
}