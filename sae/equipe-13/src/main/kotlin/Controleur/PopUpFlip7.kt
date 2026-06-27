package Vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle

class PopUpFlip7(
    titre: String,
    message: String,
    couleurPrincipale: String = "#d62828"
) : Stage() {

    init {
        this.title = titre
        this.initModality(Modality.APPLICATION_MODAL)
        this.initStyle(StageStyle.TRANSPARENT)

        val labelTitre = Label(titre)
        labelTitre.font = Font.font("Arial", FontWeight.BOLD, 30.0)
        labelTitre.textFill = Color.WHITE

        val labelMessage = Label(message)
        labelMessage.font = Font.font("Arial", FontWeight.BOLD, 18.0)
        labelMessage.textFill = Color.WHITE
        labelMessage.isWrapText = true
        labelMessage.maxWidth = 400.0
        labelMessage.alignment = Pos.CENTER

        val boutonOk = Button("OK")
        boutonOk.prefWidth = 160.0
        boutonOk.prefHeight = 50.0
        boutonOk.font = Font.font("Arial", FontWeight.BOLD, 20.0)
        boutonOk.style = """
            -fx-background-color: #cde447;
            -fx-background-radius: 25;
            -fx-border-color: white;
            -fx-border-width: 3;
            -fx-border-radius: 22;
            -fx-text-fill: black;
            -fx-cursor: hand;
        """

        boutonOk.setOnAction {
            this.close()
        }

        val root = VBox(25.0)
        root.alignment = Pos.CENTER
        root.padding = Insets(35.0)
        root.style = """
            -fx-background-color: linear-gradient(to bottom right, $couleurPrincipale, #13d6d0);
            -fx-background-radius: 35;
            -fx-border-color: white;
            -fx-border-width: 4;
            -fx-border-radius: 32;
        """

        root.children.addAll(labelTitre, labelMessage, boutonOk)

        val scene = Scene(root, 500.0, 300.0)
        scene.fill = Color.TRANSPARENT

        this.scene = scene
    }
}