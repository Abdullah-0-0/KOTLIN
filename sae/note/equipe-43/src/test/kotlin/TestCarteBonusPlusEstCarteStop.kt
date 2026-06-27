import iut.info1.flip7.cartes.CarteBonusPlus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteBonusPlusEstCarteStop {
    @Test
    fun testCarteEstCarteBonusMultiplie(){
        val carte = CarteBonusPlus(6)
        assertEquals(carte.estCarteStop(),false)
    }
}