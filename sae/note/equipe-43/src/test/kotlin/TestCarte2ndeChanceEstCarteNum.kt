import iut.info1.flip7.cartes.Carte2ndeChance
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarte2ndeChanceEstCarteNum {
    @Test
    fun testCarteEstCarteNum(){
        val carte = Carte2ndeChance()
        assertEquals(carte.estCarteNum(),false)
    }
}