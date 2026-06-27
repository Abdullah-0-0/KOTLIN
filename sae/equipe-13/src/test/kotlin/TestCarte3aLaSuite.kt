import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TestCarte3aLaSuite {
    @Test
    fun testestCarte3aLaSuite01() {
        val carte = Carte3aLaSuite()
        assertTrue(carte.estCarte3aLaSuite())
    }
    @ParameterizedTest
    @MethodSource("parametreCarte3Chance")
    fun testestCarte3aLaSuite(typeTest : String ,carte3 : Carte3aLaSuite , autreCarte : Any , res : Any){
        val carte1 = carte3
        val carte2 = autreCarte
        if (typeTest == "estCarte3aLaSuite"){
            carte2 as Carte
            val carte1 = carte3
            if (res == true){
                assertTrue(carte2.estCarte3aLaSuite())
            }
            if (res == false){
                assertFalse(carte2.estCarte3aLaSuite())
            }
        }
        if (typeTest=="toString"){
            if (autreCarte==0){
                assertEquals(res,carte1.toString())
            }
            if (autreCarte==1){
                assertNotEquals(res,carte1.toString())
            }
        }
        if (typeTest == "equals"){
            if (res == true){
                assertTrue(carte1.equals(carte2))
            }
            if (res == false){
                assertFalse(carte1.equals(carte2))
            }
        }
    }


    companion object {
        @JvmStatic
        fun parametreCarte3Chance(): Stream<Arguments> {
            return Stream.of(
                of("estCarte3aLaSuite", Carte3aLaSuite(), CarteBonusMultiplie(), false),
                of("estCarte3aLaSuite", Carte3aLaSuite(), CarteStop(), false),
                of("estCarte3aLaSuite", Carte3aLaSuite(), CarteBonusPlus(2), false),
                of("estCarte3aLaSuite", Carte3aLaSuite(), Carte3aLaSuite(), true),
                of("estCarte3aLaSuite", Carte3aLaSuite(), Carte2ndeChance(), false),
                of("estCarte3aLaSuite", Carte3aLaSuite(), CarteNum(1), false),
                of("toString", Carte3aLaSuite(), 0, "[Carte 3 à la suite]"),
                of("toString", Carte3aLaSuite(), 1, "[carte 2nde chance]"),
                of("equals", Carte3aLaSuite(), Carte3aLaSuite(), true),
                of("equals", Carte3aLaSuite(), CarteStop(), false),
                of("equals", Carte3aLaSuite(), CarteBonusPlus(2), false),
                of("equals", Carte3aLaSuite(), CarteBonusMultiplie(), false),
                of("equals", Carte3aLaSuite(), CarteNum(1), false),
            )
        }
    }
}