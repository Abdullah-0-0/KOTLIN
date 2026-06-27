import iut.info1.flip7.exceptions.MainInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.OutilsCarte

class TestVerifieMainCorrecte {
    private val outils = OutilsCarte()

    @Test
    fun `main vide ne leve pas d exception`() {
        assertDoesNotThrow {
            outils.verifieMainCorrecte(emptyList())
        }
    }

    @Test
    fun `main avec une seule CarteNum est valide`() {
        val main = listOf(CarteNum(5))
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `main avec sept CarteNum differentes et un bonus est valide`() {
        val main = listOf(
            CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3),
            CarteNum(4), CarteNum(5), CarteNum(6),
            CarteBonusPlus(4)
        )
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `une Carte2ndeChance dans la main est valide`() {
        val main = listOf(CarteNum(3), Carte2ndeChance())
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `doublon de CarteNum leve MainInvalideException`() {
        val main = listOf(CarteNum(3), CarteNum(3))
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `deux CarteBonusMultiplie levent MainInvalideException`() {
        val main = listOf(CarteNum(5), CarteBonusMultiplie(), CarteBonusMultiplie())
        assertThrows(MainInvalideException::class.java) {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `CarteStop dans la main leve MainInvalideException`() {
        val main = listOf(CarteNum(2), CarteStop())
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }
}