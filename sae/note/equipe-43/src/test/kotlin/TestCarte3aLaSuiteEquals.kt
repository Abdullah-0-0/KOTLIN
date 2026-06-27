import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TestCarte3aLaSuiteEquals {
    @ParameterizedTest
    @MethodSource("donneCartes")
    fun testCarte2ndeChanceEquals(otherCarte: Carte, expected: Boolean){
        val carte3aLaSuite = Carte3aLaSuite()

        assertEquals(carte3aLaSuite.equals(otherCarte),expected)
    }

    companion object {
        @JvmStatic
        fun donneCartes() = listOf(
            Arguments.of(CarteNum(12), false),
            Arguments.of(CarteBonusPlus(10), false),
            Arguments.of(CarteBonusMultiplie(), false),
            Arguments.of(Carte3aLaSuite(), true))
    }
}

