import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
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

class TestCarteStop {
    @Test
    fun testestCarteStop01() {
        val carte = CarteStop()
        assertTrue(carte.estCarteStop())
    }

    @ParameterizedTest
    @MethodSource("parametreCarteStop")
    fun testCarteStop(carteStop : CarteStop , carteAutre : Any, res : Any){
        if (res == true){
            val carte1 = carteStop
            val carte2 = carteAutre
            assertTrue(carte1.equals(carte2))
        }
        if (res == false){
            val carte1 = carteStop
            val carte2 = carteAutre
            assertFalse(carte1.equals(carte2))

        }
        if (res is String){
            val carte1 = carteStop
            assertEquals(res,carte1.toString())
        }
        if (res==true){
            assertTrue(carteStop.estCarteStop())
        }
        if (res==false){
            assertFalse(carteStop.estCarteNum())
        }
        if (res==false){
            assertFalse(carteStop.estCarte2ndeChance())
        }
        if (res==false){
            assertFalse(carteStop.estCarte3aLaSuite())
        }
        if (res==false){
            assertFalse(carteStop.estCarteBonusMultiplie())
        }
        if (res==false){
            assertFalse(carteStop.estCarteBonusPlus())
        }
    }

    companion object {
        @JvmStatic
        fun parametreCarteStop(): Stream<Arguments> {
            return Stream.of(
                of(CarteStop(), CarteBonusMultiplie(), false),
                of(CarteStop(), CarteStop(), true),
                of(CarteStop(), CarteBonusPlus(2), false),
                of(CarteStop(), Carte3aLaSuite(), false),
                of(CarteStop(), Carte2ndeChance(), false),
                of(CarteStop(), 0, "[Carte Stop]"),
            )
        }
    }

}