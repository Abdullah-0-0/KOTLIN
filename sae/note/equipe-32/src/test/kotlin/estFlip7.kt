import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.OutilsCarte

class estFlip7 {
    private val outils = OutilsCarte()

    @Test
    fun `CarteNum 0 a 6 est un Flip7`() {
        val main: List<Carte> = (0..6).map { CarteNum(it) }
        assertTrue(outils.estFlip7(main))
    }

    @Test
    fun `CarteNum 0 a 6 plus un CarteBonusPlus reste un Flip7`() {
        val main: List<Carte> = (0..6).map { CarteNum(it) } + listOf(CarteBonusPlus(4))
        assertTrue(outils.estFlip7(main))
    }

    @Test
    fun `CarteNum 1 a 7 est un Flip7`() {
        val main: List<Carte> = (1..7).map { CarteNum(it) }
        assertTrue(outils.estFlip7(main))
    }

    @Test
    fun `CarteNum 0 a 5 (6 cartes seulement) n est pas un Flip7`() {
        val main: List<Carte> = (0..5).map { CarteNum(it) }
        assertFalse(outils.estFlip7(main))
    }

    @Test
    fun `main vide n est pas un Flip7`() {
        val main = emptyList<Carte>()
        assertFalse(outils.estFlip7(main))
    }
}