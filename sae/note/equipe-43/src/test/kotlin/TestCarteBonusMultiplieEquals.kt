import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TestCarteBonusMultiplieEquals {
    @ParameterizedTest
    @MethodSource("donneCartes")
    fun testCarte2ndeChanceEquals(otherCarte: Carte, expected: Boolean){
        val carteBonusMultiplie = CarteBonusMultiplie()

        assertEquals(carteBonusMultiplie.equals(otherCarte),expected)
    }

    companion object {
        @JvmStatic
        fun donneCartes() = listOf(
            Arguments.of(CarteNum(12), false),
            Arguments.of(CarteBonusPlus(10), false),
            Arguments.of(CarteNum(6), false),
            Arguments.of(CarteBonusMultiplie(), true),
            Arguments.of(Carte3aLaSuite(), false))
    }
}

