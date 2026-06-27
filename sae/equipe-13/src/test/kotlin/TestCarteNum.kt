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
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TestCarteNum {
    @Test
    fun testestCarteNum01() {
        val carte = CarteNum(1)
        assertTrue(carte.estCarteNum())
    }
    @ParameterizedTest
    @MethodSource("parametreCarteNum")
    fun CarteNumero(n : Int,res : Any,message : String =""){
        if (res == true && message==""){
            val carte = CarteNum(n)
            assertDoesNotThrow { val carte = CarteNum(n) }
            assertTrue(carte.estCarteNum(),message)
        }
        if (res == false && message==""){
            val carte = CarteBonusPlus(n)
            assertFalse(carte.estCarteNum(),message)
        }
        if (res == false && message==""){
            val carte = CarteStop()
            assertFalse(carte.estCarteNum(),message)
        }
        if(res == false && message==""){
            val carte = Carte3aLaSuite()
            assertFalse(carte.estCarteNum(),message)
        }
        if(res == false && message==""){
            val carte = Carte2ndeChance()
            assertFalse(carte.estCarteNum(),message)
        }
        if(res == false && message==""){
            val carte = CarteBonusMultiplie()
            assertFalse(carte.estCarteNum(),message)
        }
        if (res == IllegalArgumentException::class.java){
            assertThrows(IllegalArgumentException::class.java) {
                val carte = CarteNum(n)
            }
        }
        if (res is String){
            val carte = CarteNum(n)
            assertEquals(res,carte.toString(),message)
        }
        if (message=="equals"){
            val carteTest= CarteNum(2)
            assertTrue(carteTest.equals(CarteNum(n)))
        }
        if (message=="notequals"){
            val carteTest= CarteNum(2)
            assertFalse(carteTest.equals(CarteNum(n)))

        }
    }

    companion object {
        @JvmStatic
        fun parametreCarteNum(): Stream<Arguments> {
            return Stream.of(
                of(-1, IllegalArgumentException::class.java, ""),
                of(0, true, ""),
                of(0, "[Carte n°0]", ""),
                of(1, true, ""),
                of(1, "[Carte n°1]", ""),
                of(2, true, ""),
                of(2, false, "cette carte n'est pas une carte numérotée"),
                of(2, "[Carte n°2]", ""),
                of(3, true, ""),
                of(3, "[Carte n°3]", ""),
                of(4, true, ""),
                of(4, false, "cette carte n'est pas une carte numérotée"),
                of(4, "[Carte n°4]", ""),
                of(5, true, ""),
                of(5, "[Carte n°5]", ""),
                of(6, true, ""),
                of(6, false, "cette carte n'est pas une carte numérotée"),
                of(6, "[Carte n°6]", ""),
                of(7, true, ""),
                of(7, "[Carte n°7]", ""),
                of(8, true, ""),
                of(8, false, "cette carte n'est pas une carte numérotée"),
                of(8, "[Carte n°8]", ""),
                of(9, true, ""),
                of(9, "[Carte n°9]", ""),
                of(10, true, ""),
                of(10, false, "cette carte n'est pas une carte numérotée"),
                of(10, "[Carte n°10]", ""),
                of(11, true, ""),
                of(11, "[Carte n°11]", ""),
                of(12, true, ""),
                of(12, "[Carte n°12]", ""),
                of(13, IllegalArgumentException::class.java, ""),
                of(14, IllegalArgumentException::class.java, ""),
                of(2, true, "equals"),
                of(3, false, "notequals")
                //of(2 shl 33,false,"création impossible num, trop grand 2^33"),
            )
        }
    }
}