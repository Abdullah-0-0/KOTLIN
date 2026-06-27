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
 * Section "Quand MANCHE_TERMINEE si..." du diagramme d'etats, qui decrit le comportement
 * de scoreManche() : "Aucun joueur n'atteint scoreFinPartie -> NOUVELLE_MANCHE" et
 * "au moins un joueur atteint scoreFinPartie -> PARTIE_TERMINEE". Confirme par la
 * documentation Dokka de scoreManche().
 */
class TestScoreMancheTransitions {

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
    fun `aucun joueur n atteint scoreFinPartie transite vers NOUVELLE_MANCHE`() {
        // scoreFinPartie est fixe a 200, les deux joueurs piochent une seule petite carte
        // chacun puis disent stop : aucun ne peut atteindre 200 sur une seule manche.
        val pioche = mutableListOf<Carte>(CarteNum(3), CarteNum(2))
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true, scoreFinPartie = 200)

        flip7.joueurCourantPiocheUneCarte() // joueur 0
        flip7.joueurCourantPiocheUneCarte() // joueur 1
        flip7.joueurCourantDitStop()
        flip7.joueurCourantDitStop()
        assertEquals(EtatPartie.MANCHE_TERMINEE, flip7.etatPartie)

        flip7.scoreManche()

        assertEquals(EtatPartie.NOUVELLE_MANCHE, flip7.etatPartie)
    }

    @Test
    fun `au moins un joueur atteint scoreFinPartie transite vers PARTIE_TERMINEE`() {
        // scoreFinPartie au minimum autorise (50) : le joueur 0 pioche assez de cartes
        // pour atteindre exactement cette limite en une seule manche. Le joueur 1 doit
        // recevoir des valeurs distinctes entre elles, sinon il fait un doublon et perd
        // avant que le joueur 0 n'ait fini de piocher ses 5 cartes.
        val pioche = mutableListOf<Carte>(
            CarteNum(12), CarteNum(1),
            CarteNum(11), CarteNum(2),
            CarteNum(10), CarteNum(3),
            CarteNum(9), CarteNum(4),
            CarteNum(8) // total joueur 0 : 12+11+10+9+8 = 50
        )
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true, scoreFinPartie = 50)

        for (i in 1..9) {
            flip7.joueurCourantPiocheUneCarte()
            assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, flip7.etatPartie, "echec a la pioche numero $i")
        }
        flip7.joueurCourantDitStop()
        flip7.joueurCourantDitStop()
        assertEquals(EtatPartie.MANCHE_TERMINEE, flip7.etatPartie)

        flip7.scoreManche()

        assertEquals(EtatPartie.PARTIE_TERMINEE, flip7.etatPartie)
    }
}
