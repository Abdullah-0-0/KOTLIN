import iut.info1.flip7.cartes.Carte3aLaSuite
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarte3aLaSuiteEstCarteBonusMultiplie {
    @Test
    fun testCarteEstCarteBonusMultiplie(){
        val carte = Carte3aLaSuite()
        assertEquals(carte.estCarteBonusMultiplie(),false)
    }
}