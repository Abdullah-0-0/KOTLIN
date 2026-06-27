package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueFlip7
import javafx.event.ActionEvent
import javafx.event.EventHandler

class Controleur3ALaSuiteBtn(vue: VueFlip7, modele: ModeleFlip7): EventHandler<ActionEvent>  {

    val vue: VueFlip7
    val modele: ModeleFlip7

    init {
        this.vue = vue
        this.modele = modele
    }

    override fun handle(event: ActionEvent?) {
        val dernierJoueur = modele.flip7.joueurs[modele.flip7.joueurCourant].donneNom()
        val joueurCible = modele.flip7.joueurs[modele.mainJoueur].donneNom()
        if (modele.carteAction != null) {
            val cartes = modele.flip7.joueurCourantCible3aLaSuite(modele.carteAction!!, modele.mainJoueur)
            vue.joueurCourantLabel.text = "Au tour de " + modele.flip7.joueurs[modele.flip7.joueurCourant].donneNom()
            vue.etatPartieLabel.text = modele.flip7.etatPartie.name
            vue.historiqueCible(dernierJoueur,joueurCible,modele.carteAction!!)
            modele.carteAction = null
            for (i in cartes){
                vue.historiquePioche(dernierJoueur, i)
            }
            vue.piocheTextLabel.text = "Tirer une carte (${modele.flip7.taillePioche})"
        }
    }
}