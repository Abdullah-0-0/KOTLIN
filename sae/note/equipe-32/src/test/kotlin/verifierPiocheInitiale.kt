import io.mockk.every
import io.mockk.mockk
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
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class xverifierPiocheInitiale {
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
        for (n in 1..3) { cartes.add(CarteStop()) }
        for (n in 1..3) { cartes.add(Carte2ndeChance()) }
        for (n in 1..3) { cartes.add(Carte3aLaSuite()) }
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
        val pioche = mutableListOf<Carte>()
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `doublon de CarteNum(5) dans la pioche leve PiocheInvalideException`() {
        val pioche = piocheValide()
        val indexUnCarteNum4 = pioche.indexOfFirst { it.estCarteNum() && it.valeur == 4 }
        pioche.removeAt(indexUnCarteNum4)
        pioche.add(CarteNum(5))
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `4 fois la meme CarteStop leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(CarteStop())
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `4 fois la meme Carte2ndeChance leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(Carte2ndeChance())
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `4 fois la meme Carte3aLaSuite leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(Carte3aLaSuite())
        assertThrows(PiocheInvalideException::class.java) {
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
    fun `nb CarteStop inferieur a 3 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        val indexUneCarteStop = pioche.indexOfFirst { it.estCarteStop() }
        pioche.removeAt(indexUneCarteStop)
        assertThrows(PiocheInvalideException::class.java) {
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
    fun `nb Carte2ndeChance inferieur a 3 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        val indexUneCarte2ndeChance = pioche.indexOfFirst { it.estCarte2ndeChance() }
        pioche.removeAt(indexUneCarte2ndeChance)
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    // ─── 3 Carte3aLaSuite ───────────────────────────────────────

    @Test
    fun `nb Carte3aLaSuite superieur a 3 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        pioche.add(Carte3aLaSuite())
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb Carte3aLaSuite egal a 3 dans la pioche valide ne leve pas d exception`() {
        val pioche = piocheValide()
        val nbCarte3aLaSuite = pioche.count { it.estCarte3aLaSuite() }
        assertEquals(3, nbCarte3aLaSuite)
        assertDoesNotThrow {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun `nb Carte3aLaSuite inferieur a 3 leve PiocheInvalideException`() {
        val pioche = piocheValide()
        val indexUneCarte3aLaSuite = pioche.indexOfFirst { it.estCarte3aLaSuite() }
        pioche.removeAt(indexUneCarte3aLaSuite)
        assertThrows(PiocheInvalideException::class.java) {
            outils.verifiePiocheInitiale(pioche)
        }
    }

    // ─── mode debug=true, pioche incomplete ──────────────────────

    @Test
    fun `mode debug=true accepte une pioche incomplete sans lever d exception`() {
        val piocheIncomplete: List<Carte> = listOf(CarteNum(5), CarteNum(7))
        assertDoesNotThrow {
            Flip7(2, creeJoueurs(2), piocheIncomplete, debug = true)
        }
    }

    @Test
    fun `mode debug=true avec pioche incomplete expose bien la taillePioche reelle, pas 94`() {
        val piocheIncomplete: List<Carte> = listOf(CarteNum(5), CarteNum(7))
        val flip7 = Flip7(2, creeJoueurs(2), piocheIncomplete, debug = true)
        assertEquals(2, flip7.taillePioche)
    }
}