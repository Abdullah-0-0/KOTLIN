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
 * Section "JoueurCourantPiocheUneCarte -> 3alasuite" du diagramme d'etats.
 *
 * D'apres la documentation Dokka de joueurCourantCible3aLaSuite() : "À l'issue de la
 * pioche, le tour passe au joueur suivant (joueurSuivant)." Deux cas sont donc a couvrir :
 * - si le joueur suivant existe et est actif -> ATTENTE_CHOIX_JOUEUR
 * - si plus aucun joueur n'est actif apres le tirage force -> MANCHE_TERMINEE
 */
class TestCible3aLaSuiteTransitions {

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
    fun `apres la fin du 3 a la suite joueur suivant actif transite vers ATTENTE_CHOIX_JOUEUR`() {
        // 3 joueurs : le joueur 0 pioche Carte3aLaSuite et cible le joueur 1, qui pioche
        // 3 cartes "normales" sans incident. Le joueur 2 est toujours JOUE_ENCORE : l'etat
        // doit revenir a ATTENTE_CHOIX_JOUEUR apres le tirage force.
        val pioche = mutableListOf<Carte>(Carte3aLaSuite(), CarteNum(1), CarteNum(2), CarteNum(3))
        pioche.addAll(piocheValide())
        val flip7 = Flip7(3, creeJoueurs(3), pioche, debug = true)
        val carte3aLaSuite = flip7.joueurCourantPiocheUneCarte() // joueur 0 pioche Carte3aLaSuite
        assertEquals(EtatPartie.ATTENTE_CIBLE_3SUITE, flip7.etatPartie)

        flip7.joueurCourantCible3aLaSuite(carte3aLaSuite, 1) // joueur 0 cible le joueur 1

        assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, flip7.etatPartie)
    }

    @Test
    fun `apres la fin du 3 a la suite sans plus aucun joueur actif la manche se termine`() {
        // 3 joueurs : les joueurs 0 et 1 disent stop d'abord, il ne reste que le joueur 2
        // actif. Celui-ci pioche une Carte3aLaSuite et n'a alors plus que lui-meme comme
        // cible possible (puisque les deux autres sont deja STOP) : apres son tirage force
        // de 3 cartes, plus aucun joueur n'est actif, la manche doit se terminer.
        val pioche = mutableListOf<Carte>(Carte3aLaSuite(), CarteNum(1), CarteNum(2), CarteNum(3))
        pioche.addAll(piocheValide())
        val flip7 = Flip7(3, creeJoueurs(3), pioche, debug = true)
        flip7.joueurCourantDitStop() // joueur 0 -> STOP, joueurCourant passe a 1
        flip7.joueurCourantDitStop() // joueur 1 -> STOP, joueurCourant passe a 2
        assertEquals(2, flip7.joueurCourant)

        val carte3aLaSuite = flip7.joueurCourantPiocheUneCarte() // joueur 2 pioche Carte3aLaSuite
        assertEquals(EtatPartie.ATTENTE_CIBLE_3SUITE, flip7.etatPartie)

        flip7.joueurCourantCible3aLaSuite(carte3aLaSuite, 2) // joueur 2 se cible lui-meme, seule option restante

        assertEquals(EtatPartie.MANCHE_TERMINEE, flip7.etatPartie)
    }
}
