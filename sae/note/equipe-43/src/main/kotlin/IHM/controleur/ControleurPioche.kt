package IHM.controleur

import IHM.modele.ModeleFlip7
import IHM.vue.VueFlip7
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteSpeciale
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.etats.EtatPartie
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControleurPioche(vue: VueFlip7, modele: ModeleFlip7): EventHandler<ActionEvent>  {

    val vue: VueFlip7
    val modele: ModeleFlip7

    init {
        this.vue = vue
        this.modele = modele
    }
    override fun handle(event: ActionEvent?) {
        if (modele.flip7.etatPartie == EtatPartie.ATTENTE_CHOIX_JOUEUR) {
            val dernierJoueur = modele.flip7.joueurCourant
            var newcarte = modele.flip7.joueurCourantPiocheUneCarte()
            vue.joueurCourantLabel.text ="Au tour de ${modele.flip7.joueurs[modele.flip7.joueurCourant].donneNom()}"
            vue.etatPartieLabel.text = modele.flip7.etatPartie.name
            vue.piocheTextLabel.text = "Tirer une carte (${modele.flip7.taillePioche})"
            vue.historiquePioche(modele.flip7.joueurs[dernierJoueur].donneNom(), newcarte)
            if(modele.flip7.etatJoueur[dernierJoueur]== EtatJoueur.PERDU){
                vue.historiqueJoueurNonActif(modele.flip7.joueurs[dernierJoueur].donneNom())
            }
            if (newcarte is CarteSpeciale) {

                val nbActifs = modele.flip7.etatJoueur.count { entry ->
                    entry.value == EtatJoueur.JOUE_ENCORE
                }

                if (nbActifs >= 2) {
                    modele.carteAction = newcarte
                    return
                }

                when (newcarte) {
                    is Carte3aLaSuite ->{
                        modele.flip7.joueurCourantCible3aLaSuite(newcarte, modele.flip7.joueurCourant)
                        vue.historiqueCible(modele.flip7.joueurs[dernierJoueur].donneNom(),modele.flip7.joueurs[dernierJoueur].donneNom(),newcarte,true)
                    }


                    is CarteStop ->{
                        modele.flip7.joueurCourantCibleStop(newcarte, modele.flip7.joueurCourant)
                        vue.historiqueCible(modele.flip7.joueurs[dernierJoueur].donneNom(),modele.flip7.joueurs[dernierJoueur].donneNom(),newcarte,true)
                    }
                }
            }
        }
    }
}