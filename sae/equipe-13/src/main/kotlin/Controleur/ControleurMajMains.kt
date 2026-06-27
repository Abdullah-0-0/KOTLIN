package Controleur

import Vue.Flip7Jeu

class ControleurMajMains(private val vue: Flip7Jeu) {

    fun majToutesLesMains() {
        // 1. On vide l'affichage actuel
        vue.main1.children.clear()
        vue.main2.children.clear()
        vue.main3.children.clear()
        vue.main4.children.clear()

        // 2. On parcourt les vraies mains du modèle
        for (i in 0 until vue.LabelJoueurs.size) {
            val mainDuJoueur = vue.flip7.main[i]

            if (mainDuJoueur != null) {
                for (carte in mainDuJoueur) {
                    vue.majMain(vue.selecMain(i), carte)
                }
            }
        }
    }

    fun viderToutesLesMains() {
        vue.main1.children.clear()
        vue.main2.children.clear()
        vue.main3.children.clear()
        vue.main4.children.clear()
    }

    fun majMainJoueur(joueur: Int) {
        val mainGraphique = vue.selecMain(joueur)

        mainGraphique.children.clear()

        val mainDuJoueur = vue.flip7.main[joueur]

        if (mainDuJoueur != null) {
            for (carte in mainDuJoueur) {
                vue.majMain(mainGraphique, carte)
            }
        }
    }
}