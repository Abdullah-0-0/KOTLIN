package flip7

import flip7.controleur.ControleurMenuPrincipal
import flip7.vue.VueMenuPrincipal
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class ApplicationFlip7 : Application() {

    override fun start(fenetrePrincipale: Stage) {
        val racine = StackPane()
        val routeur = Routeur(racine)

        val vueMenu = VueMenuPrincipal()
        ControleurMenuPrincipal(routeur, vueMenu)

        routeur.afficherEcran(vueMenu.racine)

        fenetrePrincipale.title = "Flip 7"
        fenetrePrincipale.scene = Scene(racine, 800.0, 600.0)
        fenetrePrincipale.show()
    }
}

fun main() {
    Application.launch(ApplicationFlip7::class.java)
}