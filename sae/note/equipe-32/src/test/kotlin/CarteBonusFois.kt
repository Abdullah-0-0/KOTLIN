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


class CarteBonusFois {

    @Test
    fun `new CarteBonusMultiplie = pas d'exception`() {
        assertDoesNotThrow {
            CarteBonusMultiplie()
        }
    }
    @Test
    fun `valeur == 2`() {
        val carte = CarteBonusMultiplie()
        assertEquals(2, carte.valeur, "La valeur par défaut doit être égale à 2")
    }

    @Test
    fun `estCarteBonusMultiplie - true`() {
        val carte = CarteBonusMultiplie()
        assertTrue(carte.estCarteBonusMultiplie(), "Devrait renvoyer true")
    }

    @Test
    fun `estCarteBonusPlus - false`() {
        val carte = CarteBonusMultiplie()
        assertFalse(carte.estCarteBonusPlus(), "Devrait renvoyer false")
    }

    @Test
    fun `equals(autre CarteBonusMultiplie) - true`() {
        val carte1 = CarteBonusMultiplie()
        val carte2 = CarteBonusMultiplie()
        assertTrue(carte1.equals(carte2), "Deux cartes CarteBonusMultiplie doivent être égales")
    }

    @Test
    fun `equals(CarteBonusPlus(2)) - false`() {
        val carte = CarteBonusMultiplie()
        val carteBonusPlus = CarteBonusPlus(2)
        assertFalse(carte.equals(carteBonusPlus), "Une CarteBonusMultiplie ne doit pas être égale à une CarteBonusPlus")
    }
}