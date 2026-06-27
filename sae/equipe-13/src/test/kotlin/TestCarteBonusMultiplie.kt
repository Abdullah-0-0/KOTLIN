import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TestCarteBonusMultiplie {
    @Test
    fun testestCarteBonusMultiplie01() {
        val carte = CarteBonusMultiplie()
        assertTrue(carte.estCarteBonusMultiplie())
    }
    @Test
    fun testestCarteBonusMultiplie01_1() {
        val carte = CarteNum(2)
        assertFalse(carte.estCarteBonusMultiplie())
    }
    @Test
    fun testestCarteBonusMultiplie01_2(){
        val carte = CarteStop()
        assertFalse(carte.estCarteBonusMultiplie())
    }
    @Test
    fun testestCarteBonusMultiplie01_3(){
        val carte = CarteBonusPlus(2)
        assertFalse(carte.estCarteBonusMultiplie())
    }
    @Test
    fun testestCarteBonusMultiplie02() {
        val carte = CarteBonusMultiplie()
        assertEquals("[Carte Bonus x2]", carte.toString())
    }

    @Test
    fun testestCarteBonusMultiplie04() {
        val carte = CarteBonusMultiplie()
        val carte2 = CarteStop()
        assertFalse(carte.equals(carte2))
    }
    @Test
    fun testestCarteBonusMultiplie05(){
        val carte1 = CarteBonusMultiplie()
        val carte2 = CarteBonusMultiplie()
        assertTrue(carte1.equals(carte2))
    }
    @ParameterizedTest
    @MethodSource("parametreCarteMultiply")
    fun testCarteMultiply(cartemMult : CarteBonusMultiplie, carteAutre : Any, res : Any){
        if (res == true){
            val carte1 = cartemMult
            val carte2 = carteAutre
            assertTrue(carte1.equals(carte2))
        }
        if (res == false){
            val carte1 = cartemMult
            val carte2 = carteAutre
            assertFalse(carte1.equals(carte2))
        }
        if (res is String){
            val carte1 = cartemMult
            assertEquals(res,carte1.toString())
        }
    }

    companion object {
        @JvmStatic
        fun parametreCarteMultiply(): Stream<Arguments> {
            return Stream.of(
                of(CarteBonusMultiplie(), CarteBonusMultiplie(), true),
                of(CarteBonusMultiplie(), CarteStop(), false),
                of(CarteBonusMultiplie(), CarteBonusPlus(2), false),
                of(CarteBonusMultiplie(), Carte3aLaSuite(), false),
                of(CarteBonusMultiplie(), Carte2ndeChance(), false),
                of(CarteBonusMultiplie(), 0, "[Carte Bonus x2]")
            )
        }
    }
}