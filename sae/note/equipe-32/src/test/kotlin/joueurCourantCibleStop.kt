import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.exceptions.EtatPartieInvalideException
import iut.info1.flip7.exceptions.CarteInvalideException
import iut.info1.flip7.exceptions.IndiceJoueurInvalideException
import iut.info1.flip7.exceptions.JoueurNonActifException


class TestJoueurCourantCibleStop {

    private fun creeJoueurs(nb: Int): List<IJoueur> {
        val joueurs = mutableListOf<IJoueur>()
        for (i in 1..nb) {
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
    fun `appel en ATTENTE_CIBLE_STOP avec cible valide fait passer la cible a STOP`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_STOP, flip7.etatPartie)
        flip7.joueurCourantCibleStop(carteStop, 1)
        assertEquals(EtatJoueur.STOP, flip7.etatJoueur[1])
    }

    @Test
    fun `appel en ATTENTE_CHOIX_JOUEUR leve EtatPartieInvalideException`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, flip7.etatPartie)
        assertThrows(EtatPartieInvalideException::class.java) {
            flip7.joueurCourantCibleStop(CarteStop(), 1)
        }
    }

    @Test
    fun `carte passee differente d une CarteStop leve CarteInvalideException`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        flip7.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_STOP, flip7.etatPartie)
        assertThrows(CarteInvalideException::class.java) {
            flip7.joueurCourantCibleStop(CarteNum(5), 1)
        }
    }

    @Test
    fun `carte passee differente de la derniere carte tiree leve CarteInvalideException`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        flip7.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_STOP, flip7.etatPartie)
        val autreCarteStop = CarteStop()
        assertThrows(CarteInvalideException::class.java) {
            flip7.joueurCourantCibleStop(autreCarteStop, 1)
        }
    }

    @Test
    fun `cible deja STOP leve JoueurNonActifException`() {
        val flip7 = Flip7(3, creeJoueurs(3), piocheValide())
        flip7.joueurCourantDitStop()
        flip7.joueurCourantDitStop()
        assertEquals(EtatJoueur.STOP, flip7.etatJoueur[1])
        val piocheRestante = mutableListOf<Carte>(CarteStop())
        piocheRestante.addAll(piocheValide())
        val flip7Bis = Flip7(3, creeJoueurs(3), piocheRestante, debug = true)
        flip7Bis.joueurCourantDitStop()
        val carteStop = flip7Bis.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_STOP, flip7Bis.etatPartie)
        assertThrows(JoueurNonActifException::class.java) {
            flip7Bis.joueurCourantCibleStop(carteStop, 0)
        }
    }

    @Test
    fun `cible PERDU leve JoueurNonActifException`() {
        val pioche = mutableListOf<Carte>(
            CarteNum(5), CarteNum(8), CarteNum(5),
            CarteStop()
        )
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantPiocheUneCarte()
        assertEquals(EtatJoueur.PERDU, flip7.etatJoueur[0])
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_STOP, flip7.etatPartie)
        assertThrows(JoueurNonActifException::class.java) {
            flip7.joueurCourantCibleStop(carteStop, 0)
        }
    }


    @Test
    fun `cibler le joueur courant lui meme`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        val joueurCourantAvant = flip7.joueurCourant
        flip7.joueurCourantCibleStop(carteStop, joueurCourantAvant)
        assertEquals(EtatJoueur.STOP, flip7.etatJoueur[joueurCourantAvant])
    }

    @Test
    fun `numero cible negatif leve IndiceJoueurInvalideException`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        assertThrows(IndiceJoueurInvalideException::class.java) {
            flip7.joueurCourantCibleStop(carteStop, -1)
        }
    }

    @Test
    fun `numero cible egal a nbJoueurs leve IndiceJoueurInvalideException`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        assertThrows(IndiceJoueurInvalideException::class.java) {
            flip7.joueurCourantCibleStop(carteStop, 2)
        }
    }

    @Test
    fun `apres cibleStop avec joueur suivant actif l etat revient a ATTENTE_CHOIX_JOUEUR`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(3, creeJoueurs(3), pioche, debug = true)
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantCibleStop(carteStop, 1)
        assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, flip7.etatPartie)
    }


    @Test
    fun `apres cibleStop sans plus aucun joueur actif la manche se termine`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(3, creeJoueurs(3), pioche, debug = true)
        flip7.joueurCourantDitStop()
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantCibleStop(carteStop, 2)
        assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, flip7.etatPartie)
        assertEquals(1, flip7.joueurCourant)
        flip7.joueurCourantDitStop()
        assertEquals(EtatPartie.MANCHE_TERMINEE, flip7.etatPartie)
    }

    @Test
    fun `la CarteStop ciblee est ajoutee a la main de la cible, pas a la defausse`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantCibleStop(carteStop, 1)
        val mainCible = flip7.main[1].orEmpty()
        assertTrue(mainCible.any { it.estCarteStop() }, "La CarteStop doit se retrouver dans la main de la cible")
    }
}