import iut.info1.flip7.cartes.Carte
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteEstCarteNum {
    @Test
    fun testCarteEstCarteNum(){
        val carte: Carte = object : Carte(0) {}
        assertEquals(carte.estCarteNum(),false)
    }
}