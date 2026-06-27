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

class TestDefausse {
    private fun creeJoueurs(nb: Int): List<IJoueur> =
        (1..nb).map { i ->
            val joueurMock = mockk<IJoueur>()
            every { joueurMock.donneNom() } returns "Joueur$i"
            joueurMock
        }

    private fun piocheValide(): List<Carte> {
        val cartes = mutableListOf<Carte>()
        cartes.add(CarteNum(0))
        for (v in 1..12) {
            repeat(v) { cartes.add(CarteNum(v)) }
        }
        cartes.add(CarteBonusPlus(2))
        cartes.add(CarteBonusPlus(4))
        cartes.add(CarteBonusPlus(6))
        cartes.add(CarteBonusPlus(8))
        cartes.add(CarteBonusPlus(10))
        cartes.add(CarteBonusMultiplie())
        repeat(3) { cartes.add(CarteStop()) }
        repeat(3) { cartes.add(Carte2ndeChance()) }
        repeat(3) { cartes.add(Carte3aLaSuite()) }
        return cartes
    }

    @Test
    fun `vide au debut de la partie a 0 carte`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertEquals(0, flip7.defausse.size)
    }

    @Test
    fun `taille jamais 94 cartes`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertTrue(flip7.defausse.size <= 94)
    }

    @Test
    fun `taille jamais negative`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertTrue(flip7.defausse.size >= 0)
    }

    @Test
    fun `nb CarteBonus ne depasse jamais 6`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteBonus = flip7.defausse.count { it.estCarteBonusPlus() || it.estCarteBonusMultiplie() }
        assertTrue(nbCarteBonus <= 6)
    }

    @Test
    fun `nb CarteBonus est jamais negatif`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteBonus = flip7.defausse.count { it.estCarteBonusPlus() || it.estCarteBonusMultiplie() }
        assertTrue(nbCarteBonus >= 0)
    }

    @Test
    fun `nb CarteSpe ne depasse jamais 9`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteSpe = flip7.defausse.count {
            it.estCarteStop() || it.estCarte2ndeChance() || it.estCarte3aLaSuite()
        }
        assertTrue(nbCarteSpe <= 9)
    }

    @Test
    fun `nb CarteSpe jamais negatif`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteSpe = flip7.defausse.count {
            it.estCarteStop() || it.estCarte2ndeChance() || it.estCarte3aLaSuite()
        }
        assertTrue(nbCarteSpe >= 0)
    }

    @Test
    fun `nb CarteNum ne depasse jamais 79`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteNum = flip7.defausse.count { it.estCarteNum() }
        assertTrue(nbCarteNum <= 79)
    }

    @Test
    fun `nb CarteNum jamais negatif`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteNum = flip7.defausse.count { it.estCarteNum() }
        assertTrue(nbCarteNum >= 0)
    }

    @Test
    fun `nb CarteStop ne depasse jamais 3`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteStop = flip7.defausse.count { it.estCarteStop() }
        assertTrue(nbCarteStop <= 3)
    }

    @Test
    fun `nb CarteStop dans la defausse n est jamais negatif`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarteStop = flip7.defausse.count { it.estCarteStop() }
        assertTrue(nbCarteStop >= 0)
    }

    @Test
    fun `nb Carte2ndeChance ne depasse jamais 3`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarte2ndeChance = flip7.defausse.count { it.estCarte2ndeChance() }
        assertTrue(nbCarte2ndeChance <= 3)
    }

    @Test
    fun `nb Carte2ndeChance jamais negatif`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarte2ndeChance = flip7.defausse.count { it.estCarte2ndeChance() }
        assertTrue(nbCarte2ndeChance >= 0)
    }

    @Test
    fun `nb Carte3aLaSuite ne depasse jamais 3`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarte3aLaSuite = flip7.defausse.count { it.estCarte3aLaSuite() }
        assertTrue(nbCarte3aLaSuite <= 3)
    }

    @Test
    fun `nb Carte3aLaSuite jamais negatif`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        val nbCarte3aLaSuite = flip7.defausse.count { it.estCarte3aLaSuite() }
        assertTrue(nbCarte3aLaSuite >= 0)
    }

    @Test
    fun `apres un doublon defausse toutes les bornes restent respectees`() {
        val pioche = mutableListOf<Carte>(CarteNum(5), CarteNum(8), CarteNum(5))
        pioche.addAll(piocheValide())
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantPiocheUneCarte()

        assertTrue(flip7.defausse.size <= 94)
        assertTrue(flip7.defausse.count { it.estCarteNum() } <= 79)
        assertTrue(flip7.defausse.count { it.estCarteBonusPlus() || it.estCarteBonusMultiplie() } <= 6)
        assertTrue(flip7.defausse.count { it.estCarteStop() } <= 3)
        assertTrue(flip7.defausse.count { it.estCarte2ndeChance() } <= 3)
        assertTrue(flip7.defausse.count { it.estCarte3aLaSuite() } <= 3)
    }
}