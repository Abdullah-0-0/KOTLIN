package Controleur

import Vue.Flip7Jeu

class ControleurNBPiocheDefausse(vue: Flip7Jeu) {
    private val vue: Flip7Jeu
    init {
        this.vue = vue
    }
    fun majCartes() {
        vue.taillePioche.text = vue.flip7.taillePioche.toString()
        vue.tailleDefausse.text = vue.flip7.defausse.size.toString()
    }
}