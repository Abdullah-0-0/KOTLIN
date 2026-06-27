import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TestCarte2ndeChanceCompareTo {
    @ParameterizedTest
    @MethodSource("donneCartes")
    fun testCarte2ndeChanceCompareTo(otherCarte: Carte, expected: Int){
        val carte2ndeChance = Carte2ndeChance()

        assertEquals(carte2ndeChance.compareTo(otherCarte),expected)
    }

    companion object {
        @JvmStatic
        fun donneCartes() = listOf(
            Arguments.of(CarteNum(12), 1),
            Arguments.of(CarteBonusPlus(10), 1),
            Arguments.of(CarteBonusMultiplie(), 1),
            Arguments.of(Carte2ndeChance(), 0),
            Arguments.of(CarteStop(), -1))
    }
}

