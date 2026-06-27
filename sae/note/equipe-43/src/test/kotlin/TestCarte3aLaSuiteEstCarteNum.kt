import iut.info1.flip7.cartes.Carte3aLaSuite
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarte3aLaSuiteEstCarteNum {
    @Test
    fun testCarteEstCarteNum(){
        val carte = Carte3aLaSuite()
        assertEquals(carte.estCarteNum(),false)
    }
}