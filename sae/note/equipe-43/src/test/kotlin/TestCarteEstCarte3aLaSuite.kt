import iut.info1.flip7.cartes.Carte
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteEstCarte3aLaSuite {
    @Test
    fun testCarteEstCarteBonusMultiplie(){
        val carte: Carte = object : Carte(0) {}
        assertEquals(carte.estCarte3aLaSuite(),false)
    }
}