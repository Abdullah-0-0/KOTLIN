package iut.info1.flip7.prototype.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class VueFinDePartie : BorderPane() {
    fun construire(
        classement: List<Pair<String, Int>>,
        surRejouer: () -> Unit,
        surQuitter: () -> Unit
    ) {
        this.style = "-fx-background-color: #2b2b2b;"
        this.padding = Insets(40.0)

        val conteneurCentral = VBox(20.0)
        conteneurCentral.alignment = Pos.CENTER

        val nomGagnant = classement.firstOrNull()?.first ?: "?"
        val titreLigne = HBox(12.0)
        titreLigne.alignment = Pos.CENTER


        val labelGagnant = Label("Gagnant ")
        labelGagnant.font = Font.font("System", FontWeight.BOLD, 72.0)
        labelGagnant.textFill = Color.WHITE

        val labelNomGagnant = Label(nomGagnant)
        labelNomGagnant.font = Font.font("System", FontWeight.BOLD, 72.0)
        labelNomGagnant.textFill = Color.web("#f5d76e")

        titreLigne.children.addAll(labelGagnant, labelNomGagnant)

        val conteneurClassement = VBox(14.0)
        conteneurClassement.alignment = Pos.CENTER
        conteneurClassement.maxWidth = 700.0

        classement.forEachIndexed { index, (pseudo, score) ->
            conteneurClassement.children.add(
                construireBarre(index + 1, pseudo, score)
            )
        }

        conteneurCentral.children.addAll(titreLigne, conteneurClassement)
        this.center = conteneurCentral

        val boutonRejouer = Button("Rejouer")
        boutonRejouer.style = """
            -fx-background-color: #ffffff;
            -fx-text-fill: #1e1e1e;
            -fx-font-weight: bold;
            -fx-font-size: 14px;
            -fx-padding: 14 40 14 40;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """.trimIndent()
        boutonRejouer.setOnAction { surRejouer() }

        val boutonQuitter = Button("Quitter")
        boutonQuitter.style = """
            -fx-background-color: #8b0000;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-font-size: 14px;
            -fx-padding: 14 40 14 40;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """.trimIndent()
        boutonQuitter.setOnAction { surQuitter() }

        val ligneBoutons = HBox(40.0, boutonRejouer, boutonQuitter)
        ligneBoutons.alignment = Pos.CENTER
        ligneBoutons.padding = Insets(20.0, 0.0, 0.0, 0.0)

        this.bottom = ligneBoutons
    }

    private fun construireBarre(rang: Int, pseudo: String, score: Int): StackPane {
        val couleur = when (rang) {
            1    -> "#f5d76e"
            2    -> "#e8e8e8"
            3    -> "#cd7f32"
            else -> "#555555"
        }
        val couleurTexte = when (rang) {
            2    -> "#1a1a1a"
            else -> "#1a1a1a"
        }

        val rect = Rectangle(1000.0, 100.0)
        rect.fill = Color.web(couleur)
        rect.arcWidth = 12.0
        rect.arcHeight = 12.0

        val labelRang = Label("$rang.")
        labelRang.font = Font.font("System", FontWeight.BOLD, 36.0)
        labelRang.textFill = Color.web(couleurTexte)
        labelRang.minWidth = 36.0

        val labelNom = Label(pseudo)
        labelNom.font = Font.font("System", FontWeight.BOLD, 44.0)
        labelNom.textFill = Color.web(couleurTexte)

        val labelScore = Label("$score pts")
        labelScore.font = Font.font("System", FontWeight.BOLD, 36.0)
        labelScore.textFill = Color.web(couleurTexte)

        val contenu = HBox(16.0, labelRang, labelNom)
        contenu.alignment = Pos.CENTER_LEFT
        contenu.padding = Insets(0.0, 20.0, 0.0, 24.0)

        val espaceFlexible = javafx.scene.layout.Region()
        HBox.setHgrow(espaceFlexible, javafx.scene.layout.Priority.ALWAYS)
        contenu.children.addAll(espaceFlexible, labelScore)

        return StackPane(rect, contenu)
    }
}