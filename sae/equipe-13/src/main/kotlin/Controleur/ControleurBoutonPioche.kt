package Controleur

import Vue.Flip7Jeu
import Vue.PopUpFlip7
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatPartie
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert

class ControleurBoutonPioche( vue: Flip7Jeu) : EventHandler<ActionEvent> {
    private val vue : Flip7Jeu
    private val controleurNB = ControleurNBPiocheDefausse(vue)
    private val controleurEtat = ControleurEtatJoueurs(vue)
    private val controleurMajMains = ControleurMajMains(vue)
    private val controleurFinManche = ControleurFinManche(vue)
    private val controleurMainFlip7 = ControleurMainFlip7(vue)
    init {
        this.vue = vue
    }
    override fun handle(event: ActionEvent) {
        if (vue.flip7.etatPartie == EtatPartie.ATTENTE_CHOIX_JOUEUR){
            val joueurQuiPioche = vue.flip7.joueurCourant

            val carteTire: Carte = vue.flip7.joueurCourantPiocheUneCarte()

            controleurNB.majCartes()
            controleurMajMains.majToutesLesMains()
            controleurMainFlip7.verifierMainFlip7()
            //ne doit pas être pris en compte si le joueur a perdu (doit être codé)
            if (carteTire is CarteStop || carteTire is Carte3aLaSuite) {
                if (carteTire is CarteStop) {
                    val pop = PopUpFlip7(
                        "Carte Stop",
                        "Vous avez obtenu une carte Stop.\nChoisissez un joueur à cibler.",
                        "#1d4ed8"
                    )

                    pop.initOwner(vue.scene.window)
                    pop.showAndWait()
                }
                else{
                    val pop = PopUpFlip7(
                        "Carte 3 à la suite",
                        "Vous avez obtenu une carte 3 à la suite.\nChoisissez un joueur à cibler.",
                        "#1d4ed8"
                    )

                    pop.initOwner(vue.scene.window)
                    pop.showAndWait()
                }
                vue.carteCibleEnAttente = carteTire
                return
            }
            controleurEtat.majEtatJoueurs()
            controleurFinManche.verifierFinManche()
            println(carteTire)
            println(vue.flip7.main)
        }

    }
}