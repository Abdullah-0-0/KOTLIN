package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueFlip7
import iut.info1.flip7.etats.EtatPartie
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControleurStopBtn(vue: VueFlip7, modele: ModeleFlip7): EventHandler<ActionEvent>  {

    val vue: VueFlip7
    val modele: ModeleFlip7

    init {
        this.vue = vue
        this.modele = modele
    }

    override fun handle(event: ActionEvent?) {
        val dernierJoueur = modele.flip7.joueurs[modele.flip7.joueurCourant].donneNom()

        if (modele.flip7.etatPartie == EtatPartie.ATTENTE_CHOIX_JOUEUR) {
            modele.flip7.joueurCourantDitStop()
            vue.joueurCourantLabel.text = "Au tour de " + modele.flip7.joueurs[modele.flip7.joueurCourant].donneNom()
            vue.etatPartieLabel.text = modele.flip7.etatPartie.name
            vue.historiqueJoueurNonActif(dernierJoueur, true)
        }
    }
}