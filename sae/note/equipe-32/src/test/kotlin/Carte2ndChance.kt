// IMPORTATION DES CARTES !
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop


// LE RESTE
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

class Carte2ndChance {

    @Test
    fun `New 2nd chance`() {
        assertDoesNotThrow {
            Carte2ndeChance()
        }
    }

    @Test
    fun `est 2nd chance`() {
        val carte = Carte2ndeChance()
        assertTrue(carte.estCarte2ndeChance(),"Doit retourner vrai")
    }

    @Test
    fun `est carte stop`() {
        val carte = Carte2ndeChance()
        assertFalse(carte.estCarteStop(),"Doit retourné true")
    }

    @Test
    fun `fait la mm chose q une carte 2nd chance`(){
        val carte1 = Carte2ndeChance()
        val carte2 = Carte2ndeChance()
        assertTrue(carte1.equals(carte2),"Doit retourner true")
    }
}
