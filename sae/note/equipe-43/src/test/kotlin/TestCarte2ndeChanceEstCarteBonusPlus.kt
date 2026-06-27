import iut.info1.flip7.cartes.Carte2ndeChance
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarte2ndeChanceEstCarteBonusPlus {
    @Test
    fun testCarteEstCarteBonusPlus(){
        val carte = Carte2ndeChance()
        assertEquals(carte.estCarteBonusPlus(),false)
    }
}