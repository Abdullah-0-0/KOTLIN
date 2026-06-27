import iut.info1.flip7.cartes.Carte2ndeChance
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarte2ndeChanceEstCarte3aLaSuite {
    @Test
    fun testCarteEstCarteBonusMultiplie(){
        val carte = Carte2ndeChance()
        assertEquals(carte.estCarte3aLaSuite(),false)
    }
}