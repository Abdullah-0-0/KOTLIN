import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteNum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteBonusMultiplieEstCarteStop {
    @Test
    fun testCarteEstCarteBonusMultiplie(){
        val carte = CarteBonusMultiplie()
        assertEquals(carte.estCarteStop(),false)
    }
}