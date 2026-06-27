import io.mockk.every
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.exceptions.CarteInvalideException
import iut.info1.flip7.exceptions.EtatPartieInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import io.mockk.mockk
import io.mockk.every
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.exceptions.IndiceJoueurInvalideException

/**
 * Section "joueurCourantCibleStop() — cas additionnels" de l'analyse (lignes 378-384).
 */
class TestJoueurCourantCibleStopCasAdditionnels {

    fun `CreeJoueur`(nb : Int) : List<IJoueur> {
        val joueurs = mutableListOf<IJoueur>()
        for (i in 1 .. nb) {
            val joueurMock = mockk<IJoueur>()
            every { joueurMock.donneNom() } returns "Joueur$i"
            joueurs.add(joueurMock)
        }
        return joueurs
    }

    private fun piocheValide(): List<Carte> {
        val cartes = mutableListOf<Carte>()
        cartes.add(CarteNum(0))
        for (v in 1..12) {
            for (n in 1..v) {
                cartes.add(CarteNum(v))
            }
        }
        cartes.add(CarteBonusPlus(2))
        cartes.add(CarteBonusPlus(4))
        cartes.add(CarteBonusPlus(6))
        cartes.add(CarteBonusPlus(8))
        cartes.add(CarteBonusPlus(10))
        cartes.add(CarteBonusMultiplie())
        for (n in 1..3) { cartes.add(CarteStop()) }
        for (n in 1..3) { cartes.add(Carte2ndeChance()) }
        for (n in 1..3) { cartes.add(Carte3aLaSuite()) }
        return cartes
    }


    @Test
    fun `appel en etat ATTENTE_CIBLE_3SUITE leve EtatPartieInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(Carte3aLaSuite()))
        partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_3SUITE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantCibleStop(CarteStop(), 1)
        }
    }

    @Test
    fun `appel en etat MANCHE_TERMINEE leve EtatPartieInvalideException`() {
        // CORRECTION : retrait de "debug = true"
        val partie = partieAvecPiocheValide(nbJoueurs = 2)
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        assertEquals(EtatPartie.MANCHE_TERMINEE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantCibleStop(CarteStop(), 1)
        }
    }

    @Test
    fun `appel en etat NOUVELLE_MANCHE leve EtatPartieInvalideException`() {
        // CORRECTION : retrait de "debug = true"
        val partie = partieAvecPiocheValide(nbJoueurs = 2)
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.scoreManche()
        assertEquals(EtatPartie.NOUVELLE_MANCHE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantCibleStop(CarteStop(), 1)
        }
    }

    @Test
    fun `appel en etat PARTIE_TERMINEE leve EtatPartieInvalideException`() {
        val pioche: List<Carte> = listOf(
            CarteNum(12), CarteNum(1),
            CarteNum(10), CarteNum(2),
            CarteBonusMultiplie(), CarteNum(3),
            CarteNum(9),  CarteNum(4),
            CarteNum(11), CarteNum(5)
        )

        val partie = Flip7(
            nbJoueurs = 2,
            joueurs = CreeJoueur(2),
            cartes = pioche,
            debug = true,
            scoreFinPartie = 50
        )

        repeat(5) {
            partie.joueurCourantPiocheUneCarte() // J1 pioche
            partie.joueurCourantPiocheUneCarte() // J2 pioche
        }

        partie.joueurCourantDitStop()
        partie.joueurCourantDitStop()
        partie.scoreManche()
        assertEquals(EtatPartie.PARTIE_TERMINEE, partie.etatPartie)

        // Toute action de jeu en PARTIE_TERMINEE doit lever l'exception
        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantCibleStop(CarteStop(), 1)
        }
    }

    @Test
    fun `carte passee differente de la derniere carte tiree leve CarteInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(CarteStop()))
        partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_STOP, partie.etatPartie)

            val autreCarteStop = CarteStop()
        assertThrows(CarteInvalideException::class.java) {
            partie.joueurCourantCibleStop(autreCarteStop, 1)
        }
    }

    @Test
    fun `numero de cible negatif leve IndiceJoueurInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(CarteStop()))
        val carteStop = partie.joueurCourantPiocheUneCarte()
        assertThrows(IndiceJoueurInvalideException::class.java) {
            partie.joueurCourantCibleStop(carteStop, -1)
        }
    }

    @Test
    fun `numero de cible egal a nbJoueurs leve IndiceJoueurInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(CarteStop()))
        val carteStop = partie.joueurCourantPiocheUneCarte()
        assertThrows(IndiceJoueurInvalideException::class.java) {
            partie.joueurCourantCibleStop(carteStop, 2)
        }
    }

    // CORRECTION : Array<CarteNum> remplacé par Array<out Carte>
    // Ajout éventuel de `scoreFinPartie` si vous l'utilisez dans le test `appel en etat PARTIE_TERMINEE...`
    private fun partieDebug(nbJoueurs: Int, cartesEnTete: Array<out Carte> = emptyArray(), scoreFinPartie: Int = 100): Flip7 {
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
}