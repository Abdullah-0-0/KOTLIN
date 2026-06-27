package iut.info1.flip7.prototype.controleur

import iut.info1.flip7.prototype.vue.VueAccueil
import javafx.scene.Scene
import javafx.stage.Stage

class AccueilController(private val stage: Stage) {
    private val vueAccueil = VueAccueil()
    init {
        vueAccueil.sAbonnerBoutonDemarrer { executerOuverturePlateau() }
    }

    fun construireScene(): Scene {
        return Scene(vueAccueil, 1280.0, 820.0)
    }


    private fun executerOuverturePlateau() {
        val nomsValides = vueAccueil.verifierEtDonnerNoms()

        if (nomsValides != null) {
            val scoreObjectif = vueAccueil.donneScoreObjectif()
            val plateauController = PlateauController(nomsValides, scoreObjectif)
            val scenePlateau = plateauController.construireScene()

            val feuilleStyle = javaClass.getResource("/style.css")
            if (feuilleStyle != null) {
                scenePlateau.stylesheets.add(feuilleStyle.toExternalForm())
            }

            stage.scene = scenePlateau
            stage.isFullScreen = true
        }
    }
}