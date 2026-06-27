import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TestCarteBonusPlus {
    @Test
    fun testestCarteBonusPlus01() {
        //ce test ne fonctionne pas
        //c'est normal que ça ne marche pas le prof a mis certaines valeurs comme des exceptions du coup c'est des tests de levées d'exceptions que tu dois utiliser
//        val carte = CarteBonusPlus(1)
        //assertTrue(carte.estCarteBonusPlus())
        assertThrows(IllegalArgumentException::class.java) {
            val carte = CarteBonusPlus(1)
        }
    }
    @Test
    fun verifExeptionCarteBonus(){
        assertThrows(IllegalArgumentException::class.java) { CarteBonusPlus(1) }
    }
    @Test

    fun testestCarteBonusPlus02() {
        val carte = CarteBonusPlus(2)
        assertTrue(carte.estCarteBonusPlus())

    }

    @ParameterizedTest
    @MethodSource("parametreCarteBonusPlus")
    fun testCarteBonusPlus(n : Int, res : Any, message : String ="") {
        if (res == true && message == "") {
            val carte = CarteBonusPlus(n)
            assertTrue(carte.estCarteBonusPlus(), message)
        }
        if (res == false && message == "") {
            val carte = CarteNum(n)
            assertFalse(carte.estCarteBonusPlus(), message)
        }
        if (res == false && message == "") {
            val carte = Carte2ndeChance()
            assertFalse(carte.estCarteBonusPlus(), message)
        }
        if (res == false && message == "") {
            val carte = CarteStop()
            assertFalse(carte.estCarteBonusPlus(), message)
        }
        if (res == false && message == "") {
            val carte = Carte3aLaSuite()
            assertFalse(carte.estCarteBonusPlus(), message)
        }
        if (res == false && message == "") {
            val carte = CarteBonusMultiplie()
            assertFalse(carte.estCarteBonusPlus(), message)
        }
        if (res == IllegalArgumentException::class.java) {
            assertThrows(IllegalArgumentException::class.java) {
                val carte = CarteBonusPlus(n)
            }
        }
        if (res is String) {
            val carte = CarteBonusPlus(n)
            assertEquals(res, carte.toString(), message)
        }
        if (message == "equals") {
            val carteTest = CarteNum(2)
            assertTrue(carteTest.equals(CarteNum(n)))
        }
        if (message == "notequals") {
            val carteTest = CarteNum(2)
            assertFalse(carteTest.equals(CarteNum(n)))
        }
    }

    companion object {

        @JvmStatic
        fun parametreCarteBonusPlus(): Stream<Arguments> {
            return Stream.of(
                of(-1, IllegalArgumentException::class.java, ""),
                of(0, IllegalArgumentException::class.java, ""),
                of(1, IllegalArgumentException::class.java, ""),
                of(2, true, ""),
                of(2, "[Carte Bonus +2]", ""),
                of(2, false, "cette carte n'est pas une carte bonus"),
                of(3, IllegalArgumentException::class.java, ""),
                of(4, true, ""),
                of(4, "[Carte Bonus +4]", ""),
                of(4, false, "cette carte n'est pas une carte bonus"),
                of(5, IllegalArgumentException::class.java, ""),
                of(6, true, ""),
                of(6, "[Carte Bonus +6]", ""),
                of(6, false, "cette carte n'est pas une carte bonus"),
                of(7, IllegalArgumentException::class.java, ""),
                of(8, true, ""),
                of(8, "[Carte Bonus +8]", ""),
                of(8, false, "cette carte n'est pas une carte bonus"),
                of(9, IllegalArgumentException::class.java, ""),
                of(10, true, ""),
                of(10, "[Carte Bonus +10]", ""),
                of(10, false, "cette carte n'est pas une carte bonus"),
                of(11, IllegalArgumentException::class.java, ""),
                of(2, true, "equals"),
                of(3, true, "notequals")
            )
        }
    }
}