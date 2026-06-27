package Controleur

import Vue.Flip7Jeu
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.Carte3aLaSuite
import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent

class ControleurChoixCible(private val vue: Flip7Jeu) : EventHandler<MouseEvent> {
    private val controleurEtat = ControleurEtatJoueurs(vue)
    private val controleurFinManche = ControleurFinManche(vue)
    private val controleurMajMains = ControleurMajMains(vue)
    override fun handle(event: MouseEvent) {
        val carte = vue.carteCibleEnAttente

        println("Label cliqué")

        if (carte != null) {
            val labelClique = event.source as Label
            //pour récupérer l'indice du joueur selection
            val indexCible = labelClique.userData as Int

            //Il faudra supprimer les cartes seconde chance quand elles seront utilisées + faire en sorte que la carte en double ne soit pas inclue
            if (carte is CarteStop) {
                vue.flip7.joueurCourantCibleStop(carte, indexCible)
                controleurEtat.majEtatJoueurs()
                controleurMajMains.majToutesLesMains()
                controleurFinManche.verifierFinManche()
                println("Carte Stop utilisée")
                println(vue.flip7.main)
            }

            //faire en sorte que les cartes piochées soient affichées aussi
            if (carte is Carte3aLaSuite) {
                vue.flip7.joueurCourantCible3aLaSuite(carte, indexCible)
                /*
                vue.majMain(vue.selecMain(indexCible), Carte3aLaSuite())
                vue.majMain(vue.selecMain(indexCible), vue.flip7.main.get(indexCible)!!.get(vue.flip7.main.get(indexCible)!!.size-3))
                vue.majMain(vue.selecMain(indexCible), vue.flip7.main.get(indexCible)!!.get(vue.flip7.main.get(indexCible)!!.size-2))
                vue.majMain(vue.selecMain(indexCible), vue.flip7.main.get(indexCible)!!.get(vue.flip7.main.get(indexCible)!!.size-1))
                */
                controleurEtat.majEtatJoueurs()
                controleurFinManche.verifierFinManche()
                controleurMajMains.majToutesLesMains()
                println("Carte 3 à la suite utilisée")
                println(vue.flip7.main)
            }

            vue.carteCibleEnAttente = null
        } else {
            println("Aucune carte cible en attente")
        }
    }
}