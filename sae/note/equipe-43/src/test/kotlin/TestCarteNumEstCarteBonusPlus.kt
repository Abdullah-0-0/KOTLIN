import iut.info1.flip7.cartes.CarteNum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteNumEstCarteBonusPlus {
    @Test
    fun testCarteEstCarteBonusPlus(){
        val carte = CarteNum(6)
        assertEquals(carte.estCarteBonusPlus(),false)
    }
}