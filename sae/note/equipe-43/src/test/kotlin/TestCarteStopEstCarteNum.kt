import iut.info1.flip7.cartes.CarteStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteStopEstCarteNum {
    @Test
    fun testCarteEstCarteNum(){
        val carte = CarteStop()
        assertEquals(carte.estCarteNum(),false)
    }
}