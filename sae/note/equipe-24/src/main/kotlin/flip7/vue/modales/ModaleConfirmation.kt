package flip7.vue.modales

import flip7.style.Style
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*

class ModaleConfirmation(titre: String, message: String) {

    val boutonOui: Button = Style.boutonPrincipal("OUI")
    val boutonNon: Button = Style.boutonPrincipal("NON")
    val racine: StackPane

    init {
        val fondSombre = Region()
        fondSombre.style = "-fx-background-color: rgba(0, 0, 0, 0.6);"
        // titre "QUITTER"
        val labelTitre = Label(titre)
        labelTitre.style = "-fx-text-fill: ${Style.MAGENTA}; -fx-font-size: 28px; -fx-font-weight: bold;"
        // message "ÊTES-VOUS SÛR DE VOULOIR QUITTER ?"
        val labelMessage = Label(message)
        labelMessage.style = "-fx-text-fill: #333333; -fx-font-size: 18px;"
        // on met les boutons dans une hbox
        val boiteActions = HBox(20.0, boutonNon, boutonOui)
        boiteActions.alignment = Pos.CENTER

        val conteneur = VBox(20.0, labelTitre, labelMessage, boiteActions)
        conteneur.alignment = Pos.CENTER
        conteneur.padding = Insets(30.0)

        // taille fixe
        conteneur.maxWidth = 400.0
        conteneur.maxHeight = Region.USE_PREF_SIZE

        conteneur.style = """
            -fx-background-color: ${Style.BLANC};
            -fx-background-radius: 20;
            -fx-border-color: ${Style.JAUNE};
            -fx-border-width: 5;
            -fx-border-radius: 20;
        """

        StackPane.setAlignment(conteneur, Pos.CENTER)
        racine = StackPane(fondSombre, conteneur)
    }
}