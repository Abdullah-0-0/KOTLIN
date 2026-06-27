import iut.info1.flip7.cartes.CarteBonusPlus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteBonusPlusEstCarteBonusPlus {
    @Test
    fun testCarteEstCarteBonusPlus(){
        val carte = CarteBonusPlus(6)
        assertEquals(carte.estCarteBonusPlus(),true)
    }
}