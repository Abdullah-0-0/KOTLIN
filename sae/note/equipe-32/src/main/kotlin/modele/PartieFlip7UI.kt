package iut.info1.flip7.prototype.modele

import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.etats.EtatPartie

class PartieFlip7UI {

    private var flip7: Flip7? = null
    private val joueursUI: MutableList<JoueurUI> = mutableListOf()
    private val nomsJoueurs: MutableList<String> = mutableListOf()

    fun donneTousLesJoueurs(): List<JoueurUI> = joueursUI

    fun donneJoueurActif(): JoueurUI? =
        joueursUI.firstOrNull { it.estJoueurActif }

    fun donneEtatPartie(): EtatPartie =
        flip7?.etatPartie ?: EtatPartie.ATTENTE_CHOIX_JOUEUR

    fun donneTaillePioche(): Int = flip7?.taillePioche ?: 0

    fun donneDefausse(): List<CarteUI> =
        flip7?.defausse?.map { it.versCarteUI() } ?: emptyList()

    // juste pour avoir la taille, le contenu n'est pas utilisé
    fun donnePioche(): List<CarteUI> = List(flip7?.taillePioche ?: 0) { CarteUI.Num(0) }

    fun donneMancheNumero(): Int = mancheNumero

    private var mancheNumero: Int = 1
    private var scoreFinPartie: Int = 200

    var enAttenteDonSecondeChance: Boolean = false
        private set

    var indexJoueurAvecDoublon: Int = -1
        private set


    fun initialiserPartie(noms: List<String>, scoreFinPartie: Int = 200) {
        nomsJoueurs.clear()
        nomsJoueurs.addAll(noms)
        mancheNumero = 1
        this.scoreFinPartie = scoreFinPartie
        creerNouvelleFlip7()
    }

    private fun creerNouvelleFlip7() {
        val joueursBiblio = nomsJoueurs.map { nom ->
            object : IJoueur {
                override fun donneNom(): String = nom
            }
        }

        flip7 = Flip7(
            nbJoueurs = nomsJoueurs.size,
            joueurs = joueursBiblio,
            cartes = construirePiocheComplete(),
            scoreFinPartie = scoreFinPartie
        )

        synchroniserJoueursUI()
    }

    private fun construirePiocheComplete(): List<Carte> {
        val cartes = mutableListOf<Carte>()
        cartes.add(CarteNum(0))
        for (v in 1..12) {
            repeat(v) { cartes.add(CarteNum(v)) }
        }
        cartes.add(CarteBonusPlus(2))
        cartes.add(CarteBonusPlus(4))
        cartes.add(CarteBonusPlus(6))
        cartes.add(CarteBonusPlus(8))
        cartes.add(CarteBonusPlus(10))
        cartes.add(CarteBonusMultiplie())
        repeat(3) { cartes.add(CarteStop()) }
        repeat(3) { cartes.add(Carte2ndeChance()) }
        repeat(3) { cartes.add(Carte3aLaSuite()) }
        return cartes
    }

    fun synchroniserJoueursUI() {
        val f = flip7 ?: return
        val joueurCourant = f.joueurCourant

        joueursUI.clear()
        for (i in nomsJoueurs.indices) {
            val mainBiblio = f.main[i] ?: emptyList()
            val etat = f.etatJoueur[i] ?: EtatJoueur.JOUE_ENCORE
            val score = f.score.getOrDefault(i, 0)

            val cartesUI = mainBiblio.map { it.versCarteUI() }.toMutableList()
            if (i != indexJoueurAvecDoublon) {
                var dejaVu = false
                cartesUI.removeIf { it is CarteUI.SecondeChance && dejaVu.also { dejaVu = true } }
            }

            joueursUI.add(
                JoueurUI(
                    pseudo = nomsJoueurs[i],
                    main = cartesUI,
                    etat = etat,
                    estJoueurActif = (i == joueurCourant &&
                            etat == EtatJoueur.JOUE_ENCORE &&
                            (f.etatPartie == EtatPartie.ATTENTE_CHOIX_JOUEUR ||
                                    f.etatPartie == EtatPartie.ATTENTE_CIBLE_STOP ||
                                    f.etatPartie == EtatPartie.ATTENTE_CIBLE_3SUITE)),
                    score = score
                )
            )
        }
    }

    fun stop() {
        val f = flip7 ?: return
        try {
            f.joueurCourantDitStop()
            synchroniserJoueursUI()
        } catch (e: Exception) {
        }
    }

    fun ciblerStop(carteBiblio: Carte, indexCible: Int) {
        val f = flip7 ?: return
        try {
            f.joueurCourantCibleStop(carteBiblio, indexCible)
            synchroniserJoueursUI()
        } catch (e: Exception) {
        }
    }

    fun ciblerTroisALaSuite(carteBiblio: Carte, indexCible: Int): List<CarteUI> {
        val f = flip7 ?: return emptyList()
        return try {
            val cartesObtenues = f.joueurCourantCible3aLaSuite(carteBiblio, indexCible)
            synchroniserJoueursUI()
            // si le joueur ciblé a maintenant 2 cartes 2nde chance
            val mainCible = f.main[indexCible] ?: emptyList()
            if (mainCible.count { it.estCarte2ndeChance() } >= 2) {
                verifierEtGererDoublonSecondeChance(indexCible)
            }
            cartesObtenues.map { it.versCarteUI() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun verifierEtGererDoublonSecondeChance(indexJoueur: Int) {
        val f = flip7 ?: return
        // un joueur qui a dit Stop ne peut pas recevoir de carte non plus
        val peutDonner = nomsJoueurs.indices.any { i ->
            i != indexJoueur &&
                    f.etatJoueur[i] == EtatJoueur.JOUE_ENCORE &&
                    (f.main[i] ?: emptyList()).none { it.estCarte2ndeChance() }
        }
        if (peutDonner) {
            enAttenteDonSecondeChance = true
            indexJoueurAvecDoublon = indexJoueur
        } else {
            // tout le monde en a déjà une, on défausse le doublon
            val joueurUI = joueursUI.getOrNull(indexJoueur)
            val indexARetirer = joueurUI?.main?.indexOfFirst { it is CarteUI.SecondeChance } ?: -1
            if (indexARetirer >= 0) {
                joueurUI?.main?.removeAt(indexARetirer)
            }
            enAttenteDonSecondeChance = false
            indexJoueurAvecDoublon = -1
        }
    }

    fun calculerScoreManche(): Map<Int, Int> {
        val f = flip7 ?: return emptyMap()
        return try {
            val scores = f.scoreManche()
            synchroniserJoueursUI()
            scores
        } catch (e: Exception) {
            emptyMap()
        }
    }

    fun lancerNouvelleManche() {
        val f = flip7 ?: return
        try {
            f.nouvelleManche()
            mancheNumero++
            // réinitialiser si une 2nde chance était en attente de don
            enAttenteDonSecondeChance = false
            indexJoueurAvecDoublon = -1
            synchroniserJoueursUI()
        } catch (e: Exception) {
        }
    }

    var dernierCartePiochee: Carte? = null
        private set

    fun piocherEtMemoriser(): CarteUI? {
        val f = flip7 ?: return null
        return try {
            val carte = f.joueurCourantPiocheUneCarte()
            dernierCartePiochee = carte
            synchroniserJoueursUI()

            if (carte.estCarte2ndeChance()) {
                val indexActif = f.joueurCourant
                val mainActif = f.main[indexActif] ?: emptyList()
                if (mainActif.count { it.estCarte2ndeChance() } >= 2) {
                    verifierEtGererDoublonSecondeChance(indexActif)
                } else {
                    enAttenteDonSecondeChance = false
                    indexJoueurAvecDoublon = -1
                }
            } else {
                enAttenteDonSecondeChance = false
                indexJoueurAvecDoublon = -1
            }

            carte.versCarteUI()
        } catch (e: Exception) {
            null
        }
    }

    fun donnerSecondeChance(indexReceveur: Int) {
        val indexDonneur = indexJoueurAvecDoublon.takeIf { it >= 0 } ?: return
        // retirer l'exemplaire en trop de la main du donneur
        val mainDonneur = joueursUI.getOrNull(indexDonneur)?.main
        val indexARetirer = mainDonneur?.indexOfFirst { it is CarteUI.SecondeChance } ?: -1
        if (indexARetirer >= 0) {
            mainDonneur?.removeAt(indexARetirer)
        }
        joueursUI.getOrNull(indexReceveur)?.main?.add(CarteUI.SecondeChance())
        enAttenteDonSecondeChance = false
        indexJoueurAvecDoublon = -1
    }

}

private fun Carte.versCarteUI(): CarteUI = when {
    this.estCarteNum()           -> CarteUI.Num((this as CarteNum).valeur)
    this.estCarteBonusPlus()     -> CarteUI.BonusPlus((this as CarteBonusPlus).valeur)
    this.estCarteBonusMultiplie() -> CarteUI.BonusMultiplie()
    this.estCarteStop()          -> CarteUI.Stop()
    this.estCarte2ndeChance()    -> CarteUI.SecondeChance()
    this.estCarte3aLaSuite()     -> CarteUI.TroisALaSuite()
    else                         -> CarteUI.Num(0)
}