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

class CarteStop {

    @Test
    fun `new CarteStop`(){
        assertDoesNotThrow {
            CarteStop()
        }
    }

    @Test
    fun `est carte stop`(){
        val carte = CarteStop()
        assertTrue(carte.estCarteStop(), "Devrait renvoyer true")
    }

    @Test
    fun `est carte 2ndchanche`(){
        val carte = CarteStop()
        assertFalse(carte.estCarte2ndeChance(),"Devrait renvoyer false")
    }

    @Test
    fun `est carte trois a la suite`() {
        val carte = CarteStop()
        assertFalse(carte.estCarte3aLaSuite(),"Devrait renvoyer false")
    }

    @Test
    fun `fait la meme chose q une carte stop`(){
        val carte1 = CarteStop()
        val carte2 = CarteStop()
        assertTrue(carte1.equals(carte2),"Doit retourner vrai")
    }
}
