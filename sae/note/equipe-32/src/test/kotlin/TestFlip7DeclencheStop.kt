import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.Carte

/**
 * Section "JoueurCourantPiocheUneCarte() ! FLIP7réalisé -> Stop()" du diagramme d'etats.
 *
 * Quand la 7e CarteNum distincte est piochee, estFlip7() devient vrai et la manche se
 * termine immediatement (le "Stop()" du diagramme correspond a l'etat de la partie qui
 * passe a MANCHE_TERMINEE, pas a un appel a joueurCourantDitStop()).
 */
class TestFlip7DeclencheStop {

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
    fun `piocher la 7e CarteNum distincte termine immediatement la manche`() {
        // Le joueur 0 doit piocher 7 valeurs distinctes de suite : comme joueurCourant
        // alterne a chaque pioche, il faut intercaler une carte pour le joueur 1 entre
        // chaque pioche du joueur 0. Le joueur 1 doit lui aussi recevoir des valeurs
        // distinctes entre elles (et differentes de celles du joueur 0 dans la mesure du
        // necessaire), sinon il tombe lui-meme sur un doublon et la manche se termine
        // prematurement avant que le joueur 0 n'ait pioche sa 7e carte.
        val pioche = mutableListOf<Carte>(
            CarteNum(0), CarteNum(7),  // joueur 0 puis joueur 1
            CarteNum(1), CarteNum(8),
            CarteNum(2), CarteNum(9),
            CarteNum(3), CarteNum(10),
            CarteNum(4), CarteNum(11),
            CarteNum(5), CarteNum(12),
            CarteNum(6)
        )
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)

        for (i in 1..12) {
            flip7.joueurCourantPiocheUneCarte() // 6 pioches du joueur 0, 6 du joueur 1
            assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, flip7.etatPartie, "echec a la pioche numero $i")
        }

        flip7.joueurCourantPiocheUneCarte() // joueur 0 pioche sa 7e CarteNum distincte : Flip7

        assertEquals(EtatPartie.MANCHE_TERMINEE, flip7.etatPartie)
    }
}
