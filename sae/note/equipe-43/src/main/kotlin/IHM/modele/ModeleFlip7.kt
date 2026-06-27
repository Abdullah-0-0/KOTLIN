package IHM.modele

import IHM.controleur.ControleurAjoutJoueur
import IHM.controleur.ControleurTextFieldScoreFin
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import kotlin.concurrent.fixedRateTimer


class ModeleFlip7(nbJoueur: Int, p1: String, p2: String, p3: String, p4: String, scoreFin: Int) {
    val nbJoueur: Int
    var mainJoueur:Int
    var carteAction:Carte?
    val flip7: Flip7
    var manche: Int

    init {
        manche = 1
        this.nbJoueur = nbJoueur
        flip7 = Flip7(nbJoueur,creeJoueurs(nbJoueur, listOf(p1,p2,p3,p4)),creePiocheValide(),false,scoreFin)
        carteAction = null
        mainJoueur = 0
    }

    fun creePiocheValide():List<Carte>{
        var cartes = mutableListOf<Carte>()

        for (i in 0 until 13) {
            cartes.add(CarteNum(i))
        }
        for (i in 2 until cartes.size) {
            for (j in 0 until cartes[i].valeur - 1) {
                cartes.add(CarteNum(i))
            }
        }
        for (i in 2 until 11 step (2)) {
            cartes.add(CarteBonusPlus(i))
        }
        for (i in 0 until 3) {
            cartes.add(Carte2ndeChance())
            cartes.add(Carte3aLaSuite())
            cartes.add(CarteStop())
        }
        cartes.add(CarteBonusMultiplie())

        return cartes
    }

    fun urlCarte(carte: Carte): String {
        return when (carte) {
            is CarteNum -> "/img/${carte.valeur}.png"
            is CarteBonusPlus -> "/img/+${carte.valeur}.png"
            is CarteBonusMultiplie -> "/img/Double.png"
            is Carte2ndeChance -> "/img/2ndC.png"
            is Carte3aLaSuite -> "/img/3C.png"
            is CarteStop -> "/img/Freeze.png"
            else -> ""
        }
    }
}

private class Joueur(private val nom: String) : IJoueur {
    override fun donneNom(): String = nom
}

private fun creeJoueurs(n: Int, noms: List<String>): List<Joueur> {
    return (0 .. n-1).map { i ->
        Joueur(noms[i])
    }
}


