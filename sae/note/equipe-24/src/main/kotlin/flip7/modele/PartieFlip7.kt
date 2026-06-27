package flip7.modele

import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.OutilsCarte
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.etats.EtatPartie

data class ScoreLigne(val nom: String, val scoreManche: Int, val total: Int)

class PartieFlip7(private val nomsJoueurs: List<String>, val scoreCible: Int) {

    private val outils = OutilsCarte()
    private val partie: Flip7


    private var derniereCarte: Carte? = null


    var numeroManche: Int = 1
        private set

    init {
        val joueurs: List<IJoueur> = nomsJoueurs.map { Joueur(it) }
        partie = Flip7(
            nomsJoueurs.size,   // nombre de joueurs (2 a 4)
            joueurs,
            piocheInitiale(),
            false,
            scoreCible,
            outils
        )
    }


    // Le joueur courant pioche une carte
    fun piocher() {
        derniereCarte = partie.joueurCourantPiocheUneCarte()
    }

    // Le joueur courant stop sa manche
    fun ditStop() {
        partie.joueurCourantDitStop()
    }


    fun ciblerStop(indiceCible: Int) {
        partie.joueurCourantCibleStop(derniereCarte!!, indiceCible)
    }


    fun cibler3aLaSuite(indiceCible: Int) {
        partie.joueurCourantCible3aLaSuite(derniereCarte!!, indiceCible)
    }


    fun cloturerManche(): List<ScoreLigne> {
        // la partie devient soit une nouvelle manche soit la partie se termine
        val pointsManche = partie.scoreManche()
        val lignes = mutableListOf<ScoreLigne>()
        for (i in 0 until partie.nbJoueurs) {
            val gagnes = pointsManche[i] ?: 0
            val total = partie.score[i] ?: 0
            lignes.add(ScoreLigne(nomJoueur(i), gagnes, total))
        }
        return lignes
    }


    fun nouvelleManche() {
        partie.nouvelleManche()
        numeroManche++
    }



    fun attendCibleStop(): Boolean = partie.etatPartie == EtatPartie.ATTENTE_CIBLE_STOP
    fun attendCible3aLaSuite(): Boolean = partie.etatPartie == EtatPartie.ATTENTE_CIBLE_3SUITE
    fun mancheTerminee(): Boolean = partie.etatPartie == EtatPartie.MANCHE_TERMINEE
    fun partieTerminee(): Boolean = partie.etatPartie == EtatPartie.PARTIE_TERMINEE



    val nbJoueurs: Int get() = partie.nbJoueurs
    val joueurCourant: Int get() = partie.joueurCourant
    val taillePioche: Int get() = partie.taillePioche


    fun noms(): List<String> = nomsJoueurs


    fun nomJoueur(indice: Int): String = nomsJoueurs[indice]


    fun mainTexte(indice: Int): List<String> {
        val cartes = partie.main[indice] ?: emptyList()
        val libelles = mutableListOf<String>()
        for (carte in cartes) {
            libelles.add(afficherCarte(carte))
        }
        return libelles
    }


    fun scoreMancheCourante(indice: Int): Int {
        val main = partie.main[indice] ?: return 0
        return outils.calculScore(main)
    }


    fun scoreTotal(indice: Int): Int = partie.score[indice] ?: 0


    fun etatJoueurTexte(indice: Int): String = when (partie.etatJoueur[indice]) {
        EtatJoueur.STOP -> "STOP"
        EtatJoueur.PERDU -> "PERDU"
        else -> "EN JEU"
    }


    fun defausseTexte(): String? {
        val defausse = partie.defausse
        if (defausse.isEmpty()) {
            return null
        }
        return afficherCarte(defausse.last())
    }

    fun ciblesPossibles(): List<Pair<Int, String>> {
        val actifs = mutableListOf<Int>()
        for (i in 0 until partie.nbJoueurs) {
            if (partie.etatJoueur[i] == EtatJoueur.JOUE_ENCORE) {
                actifs.add(i)
            }
        }
        val adversaires = mutableListOf<Int>()
        for (i in actifs) {
            if (i != partie.joueurCourant) {
                adversaires.add(i)
            }
        }
        val choix = if (adversaires.isNotEmpty()) adversaires else actifs
        val resultat = mutableListOf<Pair<Int, String>>()
        for (i in choix) {
            resultat.add(i to nomJoueur(i))
        }
        return resultat
    }


    fun gagnant(): String {
        var meilleur = 0
        var meilleurScore = scoreTotal(0)
        for (i in 1 until partie.nbJoueurs) {
            val score = scoreTotal(i)
            if (score > meilleurScore) {
                meilleurScore = score
                meilleur = i
            }
        }
        return nomJoueur(meilleur)
    }



    private fun afficherCarte(carte: Carte): String = when {
        carte.estCarteBonusPlus() -> "+${carte.valeur}"
        carte.estCarteBonusMultiplie() -> "x2"
        carte.estCarteStop() -> "STOP"
        carte.estCarte2ndeChance() -> "2nde\nchance"
        carte.estCarte3aLaSuite() -> "3 à la\nsuite"
        else -> carte.valeur.toString()
    }


    private fun piocheInitiale(): List<Carte> {
        val cartes = mutableListOf<Carte>()


        cartes.add(CarteNum(0))
        for (valeur in 1..12) {
            for (exemplaire in 0 until valeur) {
                cartes.add(CarteNum(valeur))
            }
        }

        // bonus multiplicateur
        cartes.add(CarteBonusMultiplie())

        // bonus additifs
        for (valeur in listOf(2, 4, 6, 8, 10)) {
            cartes.add(CarteBonusPlus(valeur))
        }

        // cartes speciales (3 exemplaires chacune)
        for (exemplaire in 0 until 3) {
            cartes.add(Carte2ndeChance())
            cartes.add(Carte3aLaSuite())
            cartes.add(CarteStop())
        }

        return cartes
    }
}