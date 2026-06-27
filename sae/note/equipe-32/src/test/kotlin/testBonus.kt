import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.OutilsCarte

class testBonus {
    private val outils = OutilsCarte()

    @Test
    fun `plusieurs CarteBonus(Plus) de valeurs differentes additionnent au score`() {
        val main = listOf(CarteNum(5), CarteBonusPlus(2), CarteBonusPlus(4))
        assertEquals(11, outils.calculScore(main))
    }

    @Test
    fun `trois CarteBonus(Plus) differents additionnent tous au score`() {
        val main = listOf(CarteNum(3), CarteBonusPlus(2), CarteBonusPlus(4), CarteBonusPlus(6))
        assertEquals(15, outils.calculScore(main))
    }

    @Test
    fun `le x2 s applique avant accumulation de plusieurs bonus plus`() {
        val main = listOf(CarteNum(5), CarteBonusMultiplie(), CarteBonusPlus(2), CarteBonusPlus(4))
        assertEquals(16, outils.calculScore(main))
    }

    @Test
    fun `plusieurs CarteBonusPlus accumulent aussi en presence d un Flip7`() {
        val main = listOf(
            CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3),
            CarteNum(4), CarteNum(5), CarteNum(6),
            CarteBonusPlus(2), CarteBonusPlus(4)
        )
        assertEquals(42, outils.calculScore(main))
    }
}