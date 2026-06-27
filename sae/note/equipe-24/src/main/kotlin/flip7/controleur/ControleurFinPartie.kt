package flip7.controleur

import flip7.Routeur
import flip7.modele.PartieFlip7
import flip7.vue.VueFinPartie
import flip7.vue.VueJeu
import flip7.vue.VueMenuPrincipal

class ControleurFinPartie(
    private val routeur: Routeur,
    private val vue: VueFinPartie,
    private val partie: PartieFlip7
) {

    init {
        vue.boutonMenu.setOnAction { retourMenu() }
        vue.boutonRejouer.setOnAction { rejouer() }
    }

    private fun retourMenu() {
        val vueMenu = VueMenuPrincipal()
        ControleurMenuPrincipal(routeur, vueMenu)
        routeur.afficherEcran(vueMenu.racine)
    }

    private fun rejouer() {
        val nouvellePartie = PartieFlip7(partie.noms(), partie.scoreCible)
        val vueJeu = VueJeu(nouvellePartie)
        ControleurJeu(routeur, vueJeu, nouvellePartie)
        routeur.afficherEcran(vueJeu.racine)
    }
}