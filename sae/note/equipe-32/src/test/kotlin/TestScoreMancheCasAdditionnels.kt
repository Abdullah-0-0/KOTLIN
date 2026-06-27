import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.exceptions.EtatPartieInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Section "scoreManche() — cas additionnels" de l'analyse (lignes 403-409).
 */
class TestScoreMancheCasAdditionnels {

    private fun partieDebug(nbJoueurs: Int, scoreFinPartie: Int = 200, cartesEnTete: Array<Carte>): Flip7 {
        val listeJoueurs: List<IJoueur> = List(nbJoueurs) { index ->
            object : IJoueur {
                override fun donneNom(): String = "Joueur ${index + 1}"
            }
        }
        return Flip7(
            nbJoueurs = nbJoueurs,
            joueurs = listeJoueurs,
            cartes = cartesEnTete.toList(),
            debug = true,
            scoreFinPartie = scoreFinPartie
        )
    }

    private fun partieAvecPiocheValide(nbJoueurs: Int, scoreFinPartie: Int = 200): Flip7 {
        val listeJoueurs: List<IJoueur> = List(nbJoueurs) { index ->
            object : IJoueur {
                override fun donneNom(): String = "Joueur ${index + 1}"
            }
        }
        val listeCartes: List<Carte> = listOf(
            CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(5), CarteNum(6)
        )
        return Flip7(
            nbJoueurs = nbJoueurs,
            joueurs = listeJoueurs,
            cartes = listeCartes,
            debug = true,
            scoreFinPartie = scoreFinPartie
        )
    }

    @Test
    fun `appel en etat ATTENTE_CIBLE_STOP leve EtatPartieInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(CarteStop()))
        partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_STOP, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.scoreManche()
        }
    }

    @Test
    fun `appel en etat ATTENTE_CIBLE_3SUITE leve EtatPartieInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(Carte3aLaSuite()))
        partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_3SUITE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.scoreManche()
        }
    }

    @Test
    fun `appel en etat NOUVELLE_MANCHE leve EtatPartieInvalideException`() {
        val partie = partieAvecPiocheValide(nbJoueurs = 2)
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.scoreManche()
        assertEquals(EtatPartie.NOUVELLE_MANCHE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.scoreManche()
        }
    }

    @Test
    fun `appel en etat PARTIE_TERMINEE leve EtatPartieInvalideException`() {
        val partie = partieDebug(
            nbJoueurs = 2,
            scoreFinPartie = 50,
            cartesEnTete = arrayOf(CarteNum(12), CarteNum(11), CarteNum(10), CarteNum(9), CarteNum(8))
        )
        repeat(5) { partie.joueurCourantPiocheUneCarte() }
        partie.joueurCourantDitStop()
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.scoreManche()
        assertEquals(EtatPartie.PARTIE_TERMINEE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.scoreManche()
        }
    }

    @Test
    fun `un seul joueur depasse scoreFinPartie, les autres tres en dessous, PARTIE_TERMINEE sans ambiguite`() {
        val partie = partieDebug(
            nbJoueurs = 3,
            scoreFinPartie = 50,
            cartesEnTete = arrayOf(
                CarteNum(12), CarteNum(0),CarteNum(0),
                CarteNum(11), CarteNum(1),CarteNum(1),
                CarteNum(10), CarteNum(2),CarteNum(2),
                CarteNum(9) ,CarteNum(3), CarteNum(3),
            )
        )
        repeat(5) { partie.joueurCourantPiocheUneCarte() } // joueur 0 accumule 50
        partie.joueurCourantDitStop() // passe au joueur 1
        partie.joueurCourantPiocheUneCarte() // joueur 1 pioche une carte faible
        partie.joueurCourantDitStop() // passe au joueur 2
        partie.joueurCourantPiocheUneCarte() // joueur 2 pioche une carte faible
        partie.joueurCourantDitStop()
        assertEquals(EtatPartie.MANCHE_TERMINEE, partie.etatPartie)
    }

    @Test
    fun `le Map retourne par scoreManche contient une entree pour chaque joueur y compris un joueur PERDU`() {
        val partie = partieDebug(
            nbJoueurs = 2,
            cartesEnTete = arrayOf(CarteNum(5), CarteNum(10), CarteNum(5),CarteNum(3))
        )
        partie.joueurCourantPiocheUneCarte() // CarteNum(5)
        partie.joueurCourantPiocheUneCarte() // doublon -> PERDU
        partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatJoueur.PERDU, partie.etatJoueur[0])

        partie.joueurCourantPiocheUneCarte() // joueur 1 pioche
        partie.joueurCourantDitStop()
        assertEquals(EtatPartie.MANCHE_TERMINEE, partie.etatPartie)

        val scores = partie.scoreManche()
        assertTrue(scores.containsKey(0), "Le Map retourné doit contenir une entrée pour le joueur 0, même PERDU")
        assertTrue(scores.containsKey(1), "Le Map retourné doit contenir une entrée pour le joueur 1")
    }
}
