import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.OutilsCarte

class calculScore {

    private val outils = OutilsCarte()

    @Test
    fun `main vide donne un score de 0`() {
        val main = emptyList<Carte>()
        assertEquals(0, outils.calculScore(main), "Une main vide doit donner un score de 0")
    }

    @Test
    fun `une seule CarteNum 0 donne un score de 0`() {
        val main = listOf(CarteNum(0))
        assertEquals(0, outils.calculScore(main), "Le score doit être 0")
    }

    @Test
    fun `une seule CarteNum 5 donne un score de 5`() {
        val main = listOf(CarteNum(5))
        assertEquals(5, outils.calculScore(main), "Le score doit être égal à la valeur de la carte")
    }

    @Test
    fun `deux CarteNum s additionnent`() {
        val main = listOf(CarteNum(3), CarteNum(7))
        assertEquals(10, outils.calculScore(main), "Le score doit être la somme des deux cartes")
    }

    @Test
    fun `CarteBonusMultiplie double le score des CarteNum`() {
        val main = listOf(CarteNum(5), CarteBonusMultiplie())
        assertEquals(10, outils.calculScore(main), "Le ×2 doit s'appliquer sur la somme des CarteNum")
    }

    @Test
    fun `CarteBonusPlus s ajoute apres les CarteNum`() {
        val main = listOf(CarteNum(5), CarteBonusPlus(4))
        assertEquals(9, outils.calculScore(main), "Le bonus +4 doit s'ajouter à la somme des CarteNum")
    }

    @Test
    fun `CarteBonusMultiplie et CarteBonusPlus se combinent dans l ordre`() {
        val main = listOf(CarteNum(5), CarteBonusMultiplie(), CarteBonusPlus(4))
        assertEquals(14, outils.calculScore(main), "Le ×2 doit s'appliquer avant le +4 : (5×2)+4 = 14")
    }

    @Test
    fun `Flip7 ajoute un bonus de 15 points`() {
        val main = listOf(
            CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3),
            CarteNum(4), CarteNum(5), CarteNum(6)
        )
        assertEquals(36, outils.calculScore(main), "Le bonus Flip7 de +15 doit s'ajouter à la somme")
    }

    @Test
    fun `Flip7 avec CarteBonusMultiplie ne double pas le bonus de 15`() {
        val main = listOf(
            CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3),
            CarteNum(4), CarteNum(5), CarteNum(6),
            CarteBonusMultiplie()
        )
        assertEquals(57, outils.calculScore(main), "Le ×2 ne doit pas s'appliquer sur le bonus Flip7")
    }

    @Test
    fun `Flip7 avec CarteBonusPlus s ajoute avant le bonus de 15`() {
        val main = listOf(
            CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3),
            CarteNum(4), CarteNum(5), CarteNum(6),
            CarteBonusPlus(6)
        )
        assertEquals(42, outils.calculScore(main), "Le score doit être somme + bonus + 15")
    }
}