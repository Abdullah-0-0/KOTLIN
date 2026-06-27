package flip7.controleur

import flip7.Routeur
import flip7.vue.VueConfiguration
import flip7.vue.VueMenuPrincipal
import flip7.vue.VueRegles
import flip7.vue.modales.ModaleConfirmation
import javafx.application.Platform

class ControleurMenuPrincipal(
    private val routeur: Routeur,
    private val vue: VueMenuPrincipal
) {
    init {
        vue.boutonPlay.setOnAction {
            val vueConfig = VueConfiguration()
            ControleurConfiguration(routeur, vueConfig)
            routeur.ouvrirModale(vueConfig.racine)
        }
        vue.boutonRegles.setOnAction {
            val vueRegles = VueRegles()
            ControleurRegles(routeur, vueRegles)
            routeur.afficherEcran(vueRegles.racine)
        }
        vue.boutonQuitter.setOnAction {
            val modale = ModaleConfirmation("QUITTER", "ÊTES-VOUS SÛR DE VOULOIR QUITTER ?")

            modale.boutonOui.setOnAction { Platform.exit() }
            modale.boutonNon.setOnAction { routeur.fermerModale(modale.racine) }

            routeur.ouvrirModale(modale.racine)
        }
    }
}