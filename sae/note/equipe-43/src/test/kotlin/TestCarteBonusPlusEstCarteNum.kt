import iut.info1.flip7.cartes.CarteBonusPlus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteBonusPlusEstCarteNum {
    @Test
    fun testCarteEstCarteNum(){
        val carte = CarteBonusPlus(6)
        assertEquals(carte.estCarteNum(),false)
    }
}