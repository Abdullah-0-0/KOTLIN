package flip7.controleur

import flip7.Routeur
import flip7.modele.PartieFlip7
import flip7.vue.VueJeu
import flip7.vue.VueMancheTerminee

class ControleurMancheTerminee(
    private val routeur: Routeur,
    private val vue: VueMancheTerminee,
    private val partie: PartieFlip7,
    private val vueJeu: VueJeu
) {

    init {
        vue.boutonProchaineManche.setOnAction { onProchaineManche() }
    }

    private fun onProchaineManche() {
        routeur.fermerModale(vue.racine)
        partie.nouvelleManche()
        vueJeu.rafraichir()
    }
}