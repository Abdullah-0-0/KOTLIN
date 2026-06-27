import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TestCarte3aLaSuiteCompareTo {
    @ParameterizedTest
    @MethodSource("donneCartes")
    fun testCarte2ndeChanceCompareTo(otherCarte: Carte, expected: Int){
        val carte3aLasuite = Carte3aLaSuite()

        assertEquals(carte3aLasuite.compareTo(otherCarte),expected)
    }

    companion object {
        @JvmStatic
        fun donneCartes() = listOf(
            Arguments.of(CarteNum(12), 1),
            Arguments.of(CarteBonusPlus(10), 1),
            Arguments.of(CarteBonusMultiplie(), 1),
            Arguments.of(Carte3aLaSuite(), 0),
            Arguments.of(CarteStop(), -1))
    }
}

