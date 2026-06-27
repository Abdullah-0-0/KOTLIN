import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.exceptions.EtatPartieInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Section "nouvelleManche() — cas additionnels" de l'analyse (lignes 411-412).
 */
class TestNouvelleMancheCasAdditionnels {


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
    fun `chainage complet MANCHE_TERMINEE vers scoreManche vers NOUVELLE_MANCHE vers nouvelleManche vers ATTENTE_CHOIX_JOUEUR`() {
        val partie = partieAvecPiocheValide(nbJoueurs = 2)
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        assertEquals(EtatPartie.MANCHE_TERMINEE, partie.etatPartie)

        partie.scoreManche()
        assertEquals(EtatPartie.NOUVELLE_MANCHE, partie.etatPartie)

        partie.nouvelleManche()
        assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, partie.etatPartie)
    }

    @Test
    fun `appeler nouvelleManche deux fois de suite leve EtatPartieInvalideException au 2e appel`() {
        val partie = partieAvecPiocheValide(nbJoueurs = 2)
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.scoreManche()
        assertEquals(EtatPartie.NOUVELLE_MANCHE, partie.etatPartie)

        partie.nouvelleManche() // 1er appel, valide
        assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.nouvelleManche() // 2e appel, l'état n'est plus NOUVELLE_MANCHE
        }
    }
}
