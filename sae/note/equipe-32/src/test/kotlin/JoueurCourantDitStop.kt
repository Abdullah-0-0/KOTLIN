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
import iut.info1.flip7.exceptions.JoueurNonActifException


class TestJoueurCourantDitStopCasAdditionnels {

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
    fun `appel en ATTENTE_CHOIX_JOUEUR fait passer le joueur courant a STOP`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, flip7.etatPartie)
        flip7.joueurCourantDitStop()
        assertEquals(EtatJoueur.STOP, flip7.etatJoueur[0])
    }

    @Test
    fun `appel en ATTENTE_CIBLE_STOP leve EtatPartieInvalideException`() {
        val pioche = mutableListOf<Carte>(CarteStop())
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        flip7.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_STOP, flip7.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            flip7.joueurCourantDitStop()
        }
    }


    @Test
    fun `joueur restant superieur a 0 et joueur suivant existe alors etat revient a ATTENTE_CHOIX_JOUEUR`() {
        val flip7 = Flip7(3, creeJoueurs(3), piocheValide())
        assertEquals(0, flip7.joueurCourant)
        flip7.joueurCourantDitStop()
        assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, flip7.etatPartie)
        assertNotEquals(0, flip7.joueurCourant, "Le tour doit passer au joueur suivant")
    }

    @Test
    fun `joueurCourant pointe bien sur le joueur suivant apres un stop, pas n importe lequel`() {
        val flip7 = Flip7(3, creeJoueurs(3), piocheValide())
        flip7.joueurCourantDitStop()
        assertEquals(1, flip7.joueurCourant)
    }

    @Test
    fun `dernier joueur actif dit stop termine la manche`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        flip7.joueurCourantDitStop()
        assertEquals(1, flip7.joueurCourant)
        flip7.joueurCourantDitStop()
        assertEquals(EtatPartie.MANCHE_TERMINEE, flip7.etatPartie)
    }

    @Test
    fun `joueur deja STOP qui redit stop leve JoueurNonActifException`() {
        val flip7 = Flip7(3, creeJoueurs(3), piocheValide())
        flip7.joueurCourantDitStop()
        assertEquals(EtatJoueur.STOP, flip7.etatJoueur[0])
        assertEquals(1, flip7.joueurCourant)
        assertNotEquals(EtatJoueur.STOP, flip7.etatJoueur[1])
    }
    
    @Test
    fun `joueur PERDU qui redit stop leve JoueurNonActifException`() {
        val pioche = mutableListOf<Carte>(CarteNum(5), CarteNum(8), CarteNum(5))
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantPiocheUneCarte()
        assertEquals(EtatJoueur.PERDU, flip7.etatJoueur[0])
        assertEquals(1, flip7.joueurCourant)
    }
}