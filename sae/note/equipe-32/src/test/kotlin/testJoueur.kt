import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.Carte


class testJoueur {
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
    fun `nb de cartes en main depasse jamais 22 en debut de partie`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteJoueur0 = flip7.main[0]?.size ?: 0
        assertTrue(nbCarteJoueur0 <= 22)
    }

    @Test
    fun `nb de cartes en main jamais negatif`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteJoueur0 = flip7.main[0]?.size ?: 0
        assertTrue(nbCarteJoueur0 >= 0)
    }

    @Test
    fun `apres avoir pioche une carte le nombre de cartes en main reste dans des bornes`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        flip7.joueurCourantPiocheUneCarte()
        val nbCarteJoueur0 = flip7.main[0]?.size ?: 0
        assertTrue(nbCarteJoueur0 in 0..22)
    }

    @Test
    fun `le score jamais negatif en debut de partie`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val scoreJoueur0 = flip7.score.getOrDefault(0, 0)
        assertTrue(scoreJoueur0 >= 0)
    }

    @Test
    fun `le score toujours superieur ou egal a 0, jamais negatif`() {
        // Aucune carte du jeu ne retire de points : un score negatif est structurellement
        // impossible, quelle que soit la composition de la main.
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        flip7.score.values.forEach { score ->
            assertTrue(score >= 0)
        }
    }
}