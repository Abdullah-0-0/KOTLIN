package flip7

import javafx.scene.Parent
import javafx.scene.layout.StackPane

class Routeur(val racine: StackPane) {
    fun afficherEcran(vue: Parent) {
        racine.children.setAll(vue)
    }

    fun ouvrirModale(modale: Parent) {
        racine.children.add(modale)
    }

    fun fermerModale(modale: Parent) {
        racine.children.remove(modale)
    }
}