import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
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

class TestCarte2ndeChance(){
    val flip7 = Flip7(3, listOf< IJoueur>(Joueur("1"), Joueur("2"), Joueur("3")), listOf<Carte>(CarteNum(1)), true, 200)


    @Test
    fun testestCarte2ndeChance01() {
        val carte = Carte2ndeChance()
        assertTrue(carte.estCarte2ndeChance())
    }

    @Test
    fun testestCarte2ndeChance02(){
        val carte = Carte2ndeChance()
        assertTrue(carte.equals(1) is Boolean)
    }

    @Test
    fun testestCarte2ndeChance03(){
        val carte = Carte2ndeChance()
        assertTrue(carte.toString() is String)
    }

    @ParameterizedTest
    @MethodSource("parametreCarte2Chance")
    fun testCarte2Chance(carte2Ch : Carte2ndeChance,autre : Any, res : Any){
        if (res == true){
            val carte1 = carte2Ch
            val carte2 = autre
            assertTrue(carte1.equals(carte2))
        }
        if (res == true){
            val carte1 = autre as Carte
            if (carte1 is Carte2ndeChance){
                assertTrue(carte1.estCarte2ndeChance())
            }

        }
        if (res == false){
            val carte1 = carte2Ch
            val carte2 = autre
            assertFalse(carte1.equals(carte2))
        }
        if (res == false ){
            val carte2 = autre as Carte
            if (carte2 !is Carte2ndeChance){
                assertFalse(carte2.estCarte2ndeChance())
            }
        }

        if (res is String){
            val carte1 = carte2Ch
            assertEquals(res,carte1.toString())
        }
    }
    companion object {
        @JvmStatic
        fun parametreCarte2Chance(): Stream<Arguments> {
            return Stream.of(
                of(Carte2ndeChance(), CarteBonusMultiplie(), false),
                of(Carte2ndeChance(), CarteStop(), false),
                of(Carte2ndeChance(), CarteBonusPlus(2), false),
                of(Carte2ndeChance(), Carte3aLaSuite(), false),
                of(Carte2ndeChance(), Carte2ndeChance(), true),
                of(Carte2ndeChance(), 0, "[Carte 2nde chance]"),
            )
        }
    }
}
