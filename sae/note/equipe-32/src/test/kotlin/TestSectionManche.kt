import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.exceptions.NombreJoueursInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Section "Manche" de l'analyse (lignes 300 à 311).
 */
class TestSectionManche {

    // =========================================================================
    // TESTS
    // =========================================================================

    @Test
    fun `une manche ne peut etre jouee qu avec 2 a 4 joueurs (rappel des bornes du constructeur)`() {
        assertDoesNotThrow { partieAvecPiocheValide(nbJoueurs = 2) }
        assertDoesNotThrow { partieAvecPiocheValide(nbJoueurs = 3) }
        assertDoesNotThrow { partieAvecPiocheValide(nbJoueurs = 4) }
        assertThrows(NombreJoueursInvalideException::class.java) {
            partieAvecPiocheValide(nbJoueurs = 1)
        }
        assertThrows(NombreJoueursInvalideException::class.java) {
            partieAvecPiocheValide(nbJoueurs = 5)
        }
    }

    @Test
    fun `debut de manche nb carte vaut 0 pour chaque joueur`() {
        val partie = partieAvecPiocheValide(nbJoueurs = 3)
        for (i in 0 until 3) {
            // main est Map<Int, List<Carte>>, indexée par Int 0-based
            assertTrue(
                partie.main[i]?.isEmpty() == true,
                "La main du joueur $i doit être vide en début de manche"
            )
        }
    }

    @Test
    fun `Faire une action passe au joueur suivant`() {
        val partie = partieDebug(nbJoueurs = 3, cartesEnTete = arrayOf(
            CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(5), CarteNum(6)
        ))
        val joueurAvant = partie.joueurCourant
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        assertNotEquals(
            joueurAvant,
            partie.joueurCourant,
            "Une seule action (ditStop) doit faire avancer le joueur courant"
        )
    }

    @Test
    fun `joueurCourantDitStop fait passer la main au joueur suivant après une action`() {
        val partie = partieAvecPiocheValide(nbJoueurs = 3)
        val joueur0 = partie.joueurCourant
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        val joueur1 = partie.joueurCourant
        assertNotEquals(joueur0, joueur1, "Le joueur courant doit changer apres un seul appel a ditStop()")
    }



    // Les fonctions




    private fun partieAvecPiocheValide(nbJoueurs: Int): Flip7 {
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
            debug = true
        )
    }

    private fun partieDebug(nbJoueurs: Int, cartesEnTete: Array<CarteNum>): Flip7 {
        val listeJoueurs: List<IJoueur> = List(nbJoueurs) { index ->
            object : IJoueur {
                override fun donneNom(): String = "Joueur ${index + 1}"
            }
        }
        val listeCartes: List<Carte> = cartesEnTete.toList()
        return Flip7(
            nbJoueurs = nbJoueurs,
            joueurs = listeJoueurs,
            cartes = listeCartes,
            debug = true
        )
    }
}