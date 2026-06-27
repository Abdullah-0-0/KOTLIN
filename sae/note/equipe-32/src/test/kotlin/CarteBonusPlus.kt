import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.exceptions.CarteInvalideException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CarteBonusPlusTest {
    @Test
    fun `valeurs valides ne levent pas d exception`() {
        assertDoesNotThrow { CarteBonusPlus(2) }
        assertDoesNotThrow { CarteBonusPlus(6) }
        assertDoesNotThrow { CarteBonusPlus(10) }
    }
    
    @Test
    fun `valeurs invalides levent IllegalArgumentException`() {
        assertThrows(IllegalArgumentException::class.java) { CarteBonusPlus(1) }
        assertThrows(IllegalArgumentException::class.java) { CarteBonusPlus(3) }
        assertThrows(IllegalArgumentException::class.java) { CarteBonusPlus(12) }
    }

    @Test
    fun `estCarteBonusPlus retourne true`() {
        assertTrue(CarteBonusPlus(2).estCarteBonusPlus())
    }

    @Test
    fun `estCarteNum et estCarteBonusMultiplie retournent false`() {
        val carte = CarteBonusPlus(2)
        assertFalse(carte.estCarteNum())
        assertFalse(carte.estCarteBonusMultiplie())
    }

    @Test
    fun `equals fonctionne correctement`() {
        assertTrue(CarteBonusPlus(2) == CarteBonusPlus(2))
        assertFalse(CarteBonusPlus(2) == CarteBonusPlus(10))
    }
}