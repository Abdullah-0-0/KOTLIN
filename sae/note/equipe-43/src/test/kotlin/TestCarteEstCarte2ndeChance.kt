import iut.info1.flip7.cartes.Carte
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteEstCarte2ndeChance {
    @Test
    fun testCarteEstCarte2ndeChance(){
        val carte: Carte = object : Carte(0) {}
        assertEquals(carte.estCarte2ndeChance(),false)
    }
}