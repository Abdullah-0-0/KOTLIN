import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TestCarteBonusMultipliePlusCompareTo {
    @ParameterizedTest
    @MethodSource("donneCartes")
    fun testCarte2ndeChanceCompareTo(otherCarte: Carte, expected: Int){
        val carteBonusMultiplie = CarteBonusMultiplie()

        assertEquals(carteBonusMultiplie.compareTo(otherCarte),expected)
    }

    companion object {
        @JvmStatic
        fun donneCartes() = listOf(
            Arguments.of(CarteNum(12), 1),
            Arguments.of(CarteBonusPlus(10), -1),
            Arguments.of(CarteNum(2), 1),
            Arguments.of(CarteBonusMultiplie(), 0),
            Arguments.of(Carte3aLaSuite(), -1),
            Arguments.of(CarteNum(6), 1))
    }
}

