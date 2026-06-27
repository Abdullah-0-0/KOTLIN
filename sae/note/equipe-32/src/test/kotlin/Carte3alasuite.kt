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


class Carte3alasuite {

    @Test
    fun `new carte 3alasuite`() {
        assertDoesNotThrow{
            Carte3alasuite()
        }
    }

    @Test
    fun `est carte3alasuite`() {
        val carte = Carte3aLaSuite()
        assertTrue( carte.estCarte3aLaSuite(),"Doit retourner vrai")
    }

    @Test
    fun `est carte stop`() {
        val carte = Carte3aLaSuite()
        assertFalse(carte.estCarteStop(),"Doit retourner False")
    }

    @Test
    fun `fait la meme chose q une carte 3 a la suite`(){
        val carte1 = Carte3aLaSuite()
        val carte2 = Carte3aLaSuite()
        assertTrue(carte1.equals(carte2),"Doit retourner true")
    }


}