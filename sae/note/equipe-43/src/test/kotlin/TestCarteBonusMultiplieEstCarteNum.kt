import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteNum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteBonusMultiplieEstCarteNum {
    @Test
    fun testCarteEstCarteNum(){
        val carte = CarteBonusMultiplie()
        assertEquals(carte.estCarteNum(),false)
    }
}