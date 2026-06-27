import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.OutilsCarte
import iut.info1.flip7.exceptions.PiocheInvalideException

class TestPaquet {
    private val outils = OutilsCarte()

    private fun creeJoueurs(nb: Int): List<IJoueur> {
        val joueurs = mutableListOf<IJoueur>()
        for (i in 1..nb) {
            val joueurMock = mockk<IJoueur>()
            every { joueurMock.donneNom() } returns "Joueur$i"
            joueurs.add(joueurMock)
        }
        return joueurs
    }

    private fun piocheValide(): MutableList<Carte> {
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
        for (n in 1..3) {cartes.add(CarteStop())}
        for (n in 1..3) {cartes.add(Carte2ndeChance())}
        for (n in 1..3) {cartes.add(Carte3aLaSuite())}
        return cartes
    }

    @Test
    fun `pioche de 94 cartes ne leve pas d exception`() {
        assertDoesNotThrow {
            outils.verifiePiocheInitiale(piocheValide())
        }
    }

    @Test
    fun `pioche de 95 cartes leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(CarteBonusPlus(2))
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `pioche de 93 cartes leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.removeAt(0)
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `pioche vide leve PiocheInvalideException`() {
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(emptyList())
        }
    }

    @Test
    fun `nb CarteBonus superieur a 6 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(CarteBonusPlus(2))
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb CarteBonus egal a 6 dans la pioche valide ne leve pas d exception`() {
        val pioche = piocheValide()
        val nbCarteBonus = pioche.count { it.estCarteBonusPlus() || it.estCarteBonusMultiplie() }
        assertEquals(6, nbCarteBonus)
        assertDoesNotThrow {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb CarteSpe superieur a 9 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(CarteStop())
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb CarteSpe egal a 9 dans la pioche valide ne leve pas d exception`() {
        val pioche = piocheValide()
        val nbCarteSpe = pioche.count { it.estCarteStop() || it.estCarte2ndeChance() || it.estCarte3aLaSuite() }
        assertEquals(9, nbCarteSpe)
        assertDoesNotThrow {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb CarteNum superieur a 79 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(CarteNum(1))
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb CarteNum egal a 79 dans la pioche valide ne leve pas d exception`() {
        val pioche = piocheValide()
        val nbCarteNum = pioche.count { it.estCarteNum() }
        assertEquals(79, nbCarteNum)
        assertDoesNotThrow {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb CarteStop superieur a 3 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(CarteStop())
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb CarteStop egal a 3 dans la pioche valide ne leve pas d exception`() {
        val pioche = piocheValide()
        val nbCarteStop = pioche.count { it.estCarteStop() }
        assertEquals(3, nbCarteStop)
        assertDoesNotThrow {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb Carte2ndeChance superieur a 3 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(Carte2ndeChance())
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb Carte2ndeChance egal a 3 dans la pioche valide ne leve pas d exception`() {
        val pioche = piocheValide()
        val nbCarte2ndeChance = pioche.count { it.estCarte2ndeChance() }
        assertEquals(3, nbCarte2ndeChance)
        assertDoesNotThrow {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb Carte3aLaSuite superieur a 3 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(Carte3aLaSuite())
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb Carte3aLaSuite = 3 dans la pioche valide ne leve pas d exception`() {
        val pioche = piocheValide()
        val nbCarte3aLaSuite = pioche.count { it.estCarte3aLaSuite() }
        assertEquals(3, nbCarte3aLaSuite)
        assertDoesNotThrow {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `CarteNum(x) est presente x fois dans la pioche valide, sauf CarteNum(0) presente 1 fois`() {
        val pioche = piocheValide()
        val nbCarteNum0 = pioche.count { it.estCarteNum() && it.valeur == 0 }
        assertEquals(1, nbCarteNum0, "CarteNum(0) doit etre presente exactement 1 fois")
        for (v in 1..12) {
            val nbCarteNumV = pioche.count { it.estCarteNum() && it.valeur == v }
            assertEquals(v, nbCarteNumV, "CarteNum($v) doit etre presente $v fois")
        }
    }

    @Test
    fun `un nombre incorrect d exemplaires de CarteNum(x) leve PiocheInvalideException`() {
        val pioche = piocheValide()
        val indexPremierCarteNum3 = pioche.indexOfFirst { it.estCarteNum() && it.valeur == 3 }
        pioche.removeAt(indexPremierCarteNum3)
        val indexUnCarteNum12 = pioche.indexOfFirst { it.estCarteNum() && it.valeur == 12 }
        pioche.removeAt(indexUnCarteNum12)
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `CarteBonusMultiplie est presente exactement 1 fois dans la pioche valide`() {
        val pioche = piocheValide()
        val nbCarteBonusMultiplie = pioche.count { it.estCarteBonusMultiplie() }
        assertEquals(1, nbCarteBonusMultiplie)
    }

    @Test
    fun `un deuxieme exemplaire de CarteBonusMultiplie leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(CarteBonusMultiplie())
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `des appels successifs font diminuer taillePioche`() {
        val pioche = mutableListOf<Carte>()
        pioche.add(CarteNum(0))
        for (v in 1..12) {
            for (n in 1..v) {
                pioche.add(CarteNum(v))
            }
        }
        val flip7 = Flip7(2, creeJoueurs(2), pioche, debug = true)
        val tailleInitiale = flip7.taillePioche
        flip7.joueurCourantPiocheUneCarte()
        assertEquals(tailleInitiale - 1, flip7.taillePioche)
        flip7.joueurCourantPiocheUneCarte()
        assertEquals(tailleInitiale - 2, flip7.taillePioche)
    }
}