import iut.info1.flip7.cartes.CarteNum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteNumEstCarteNum {
    @Test
    fun testCarteEstCarteNum(){
        val carte = CarteNum(6)
        assertEquals(carte.estCarteNum(),true)
    }
}