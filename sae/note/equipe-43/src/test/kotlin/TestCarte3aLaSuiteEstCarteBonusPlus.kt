import iut.info1.flip7.cartes.Carte3aLaSuite
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarte3aLaSuiteEstCarteBonusPlus {
    @Test
    fun testCarteEstCarteBonusPlus(){
        val carte = Carte3aLaSuite()
        assertEquals(carte.estCarteBonusPlus(),false)
    }
}