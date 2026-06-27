package flip7.controleur

import flip7.Routeur
import flip7.vue.VueMenuPrincipal
import flip7.vue.VueRegles


class ControleurRegles(
    private val routeur: Routeur,
    private val vue: VueRegles
) {

    init {
        vue.boutonRetour.setOnAction { retourMenu() }
    }

    private fun retourMenu() {
        val vueMenu = VueMenuPrincipal()
        ControleurMenuPrincipal(routeur, vueMenu)
        routeur.afficherEcran(vueMenu.racine)
    }
}