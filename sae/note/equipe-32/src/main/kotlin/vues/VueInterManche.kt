package iut.info1.flip7.prototype.vue

import iut.info1.flip7.prototype.modele.JoueurUI
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

class VueInterManche : BorderPane() {

    fun construire(
        numeroManche: Int,
        scoresManche: Map<String, Int>,
        scoresTotaux: List<JoueurUI>,
        surContinuer: () -> Unit
    ) {
        this.style = "-fx-background-color: #2b2b2b;"
        this.padding = Insets(40.0)

        val conteneurCentral = VBox(28.0)
        conteneurCentral.alignment = Pos.CENTER

        val titreManche = Label("Fin de la manche $numeroManche")
        titreManche.font = Font.font("System", FontWeight.BOLD, 32.0)
        titreManche.textFill = Color.web("#f5d76e")

        val sousTitre = Label("Scores de cette manche")
        sousTitre.font = Font.font("System", 16.0)
        sousTitre.textFill = Color.web("#aaaaaa")

        val conteneurBarres = VBox(12.0)
        conteneurBarres.alignment = Pos.CENTER
        conteneurBarres.maxWidth = 700.0

        val classement = scoresTotaux.sortedByDescending { it.score }
        classement.forEachIndexed { index, joueur ->
            val scoreManche = scoresManche[joueur.pseudo] ?: 0
            conteneurBarres.children.add(
                construireLigneJoueur(index + 1, joueur.pseudo, scoreManche, joueur.score)
            )
        }

        conteneurCentral.children.addAll(titreManche, sousTitre, conteneurBarres)
        this.center = conteneurCentral

        val boutonContinuer = Button("Manche suivante →")
        boutonContinuer.style = """
            -fx-background-color: #ffffff;
            -fx-text-fill: #1e1e1e;
            -fx-font-weight: bold;
            -fx-font-size: 15px;
            -fx-padding: 14 40 14 40;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """.trimIndent()
        boutonContinuer.setOnAction { surContinuer() }

        val ligneBouton = HBox(boutonContinuer)
        ligneBouton.alignment = Pos.CENTER
        ligneBouton.padding = Insets(20.0, 0.0, 0.0, 0.0)
        this.bottom = ligneBouton
    }

    private fun construireLigneJoueur(
        rang: Int,
        pseudo: String,
        scoreManche: Int,
        scoreTotal: Int
    ): StackPane {
        val couleur = when (rang) {
            1    -> "#f5d76e"
            2    -> "#e8e8e8"
            3    -> "#cd7f32"
            else -> "#555555"
        }
        val couleurTexte = if (rang == 4) "#ffffff" else "#1a1a1a"
        val rect = Rectangle(700.0, 62.0)
        rect.fill = Color.web(couleur)
        rect.arcWidth = 12.0
        rect.arcHeight = 12.0
        val labelRang = Label("$rang.")
        labelRang.font = Font.font("System", FontWeight.BOLD, 18.0)
        labelRang.textFill = Color.web(couleurTexte)
        labelRang.minWidth = 36.0
        val labelNom = Label(pseudo)
        labelNom.font = Font.font("System", FontWeight.BOLD, 20.0)
        labelNom.textFill = Color.web(couleurTexte)
        val signe = if (scoreManche >= 0) "+" else "" // afficher le + pour les scores positifs
        val labelManche = Label("${signe}${scoreManche} pts")
        labelManche.font = Font.font("System", FontWeight.BOLD, 16.0)
        labelManche.textFill = Color.web(couleurTexte)
        val labelTotal = Label("Total : $scoreTotal pts")
        labelTotal.font = Font.font("System", FontWeight.BOLD, 16.0)
        labelTotal.textFill = Color.web(couleurTexte)
        val espaceGauche = javafx.scene.layout.Region()
        val espaceDroit = javafx.scene.layout.Region()
        HBox.setHgrow(espaceGauche, javafx.scene.layout.Priority.ALWAYS)
        HBox.setHgrow(espaceDroit, javafx.scene.layout.Priority.ALWAYS)
        val contenu = HBox(12.0, labelRang, labelNom, espaceGauche, labelManche, espaceDroit, labelTotal)
        contenu.alignment = Pos.CENTER_LEFT
        contenu.padding = Insets(0.0, 24.0, 0.0, 24.0)

        return StackPane(rect, contenu)
    }
}