import iut.info1.flip7.Flip7
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonus
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteSpeciale
import iut.info1.flip7.cartes.CarteStop
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CartesTest {

    @Test
    fun test() {
        Assertions.assertTrue(true)
    }

    // valeurs CarteNum
    @Test fun testValeurCarteNum0() { val c = CarteNum(0); Assertions.assertEquals(0, c.valeur) }
    @Test fun testValeurCarteNum1() { val c = CarteNum(1); Assertions.assertEquals(1, c.valeur) }
    @Test fun testValeurCarteNum2() { val c = CarteNum(2); Assertions.assertEquals(2, c.valeur) }
    @Test fun testValeurCarteNum3() { val c = CarteNum(3); Assertions.assertEquals(3, c.valeur) }
    @Test fun testValeurCarteNum4() { val c = CarteNum(4); Assertions.assertEquals(4, c.valeur) }
    @Test fun testValeurCarteNum5() { val c = CarteNum(5); Assertions.assertEquals(5, c.valeur) }
    @Test fun testValeurCarteNum6() { val c = CarteNum(6); Assertions.assertEquals(6, c.valeur) }
    @Test fun testValeurCarteNum7() { val c = CarteNum(7); Assertions.assertEquals(7, c.valeur) }
    @Test fun testValeurCarteNum8() { val c = CarteNum(8); Assertions.assertEquals(8, c.valeur) }
    @Test fun testValeurCarteNum9() { val c = CarteNum(9); Assertions.assertEquals(9, c.valeur) }
    @Test fun testValeurCarteNum10() { val c = CarteNum(10); Assertions.assertEquals(10, c.valeur) }
    @Test fun testValeurCarteNum11() { val c = CarteNum(11); Assertions.assertEquals(11, c.valeur) }
    @Test fun testValeurCarteNum12() { val c = CarteNum(12); Assertions.assertEquals(12, c.valeur) }

    // valeurs spéciales (score = 0)
    @Test
    fun testCarteStopVautZeroPointDansLeCalculDuScore() {
        Assertions.assertEquals(0, CarteStop().valeur)
    }

    @Test
    fun testCarteSecondeChanceVautZeroPointDansLeCalculDuScore() {
        Assertions.assertEquals(0, Carte2ndeChance().valeur)
    }

    @Test
    fun testCarteTroisALaSuiteVautZeroPointDansLeCalculDuScore() {
        Assertions.assertEquals(0, Carte3aLaSuite().valeur)
    }

    // types CarteNum
    @Test
    fun testCarteNumEstUneCarte() {
        Assertions.assertTrue(CarteNum(5) is Carte)
    }

    @Test
    fun testCarteNumNestPasUneCarteSpeciale() {
        Assertions.assertFalse(CarteNum(5).estCarteStop() || CarteNum(5).estCarte2ndeChance())
    }

    @Test
    fun testCarteNumNestPasUneCarteBonus() {
        Assertions.assertFalse(CarteNum(5).estCarteBonusPlus() || CarteNum(5).estCarteBonusMultiplie())
    }

    // comparaisons CarteNum
    @Test
    fun testCarteNum0PlusPetiteQueCarteNum1() {
        Assertions.assertTrue(CarteNum(0) < CarteNum(1))
    }

    @Test
    fun testCarteNum12PlusGrandeQueCarteNum11() {
        Assertions.assertTrue(CarteNum(12) > CarteNum(11))
    }

    @Test
    fun testDeuxCarteNumMemValeurCompareToZero() {
        Assertions.assertEquals(0, CarteNum(7).compareTo(CarteNum(7)))
    }

    @Test
    fun testCarteNum0CompareToCarteNum0() {
        Assertions.assertEquals(0, CarteNum(0).compareTo(CarteNum(0)))
    }

    @Test
    fun testCarteNum1PlusGrandeQueCarteNum0() {
        Assertions.assertTrue(CarteNum(1) > CarteNum(0))
    }

    // cas particulier CarteNum(0)
    @Test
    fun testCarteNum0NestPasUneCarteSpeciale() {
        Assertions.assertFalse(CarteNum(0).estCarteStop())
    }

    @Test
    fun testCarteNum0EstUneCarte() {
        Assertions.assertTrue(CarteNum(0) is Carte)
    }

    // types CarteStop
    @Test
    fun testCarteStopEstUneCarteSpeciale() {
        Assertions.assertTrue(CarteStop() is CarteSpeciale)
    }

    @Test
    fun testCarteStopEstUneCarte() {
        Assertions.assertTrue(CarteStop() is Carte)
    }

    @Test
    fun testCarteStopNestPasUneCarteNum() {
        Assertions.assertFalse(CarteStop().estCarteNum())
    }

    // types Carte2ndeChance
    @Test
    fun testCarte2ndeChanceEstUneCarteSpeciale() {
        Assertions.assertTrue(Carte2ndeChance() is CarteSpeciale)
    }

    @Test
    fun testCarte2ndeChanceEstUneCarte() {
        Assertions.assertTrue(Carte2ndeChance() is Carte)
    }

    @Test
    fun testCarte2ndeChanceNestPasUneCarteNum() {
        Assertions.assertFalse(Carte2ndeChance().estCarteNum())
    }

    // types Carte3aLaSuite
    @Test
    fun testCarte3aLaSuiteEstUneCarteSpeciale() {
        Assertions.assertTrue(Carte3aLaSuite() is CarteSpeciale)
    }

    @Test
    fun testCarte3aLaSuiteEstUneCarte() {
        Assertions.assertTrue(Carte3aLaSuite() is Carte)
    }

    @Test
    fun testCarte3aLaSuiteNestPasUneCarteNum() {
        Assertions.assertFalse(Carte3aLaSuite().estCarteNum())
    }

    // CarteBonusPlus
    @Test
    fun testCarteBonusPlusVautSaValeur() {
        Assertions.assertEquals(2, CarteBonusPlus(2).valeur)
    }

    @Test
    fun testCarteBonusPlusEstUneCarteBonus() {
        Assertions.assertTrue(CarteBonusPlus(2) is CarteBonus)
    }

    @Test
    fun testCarteBonusPlusEstUneCarte() {
        Assertions.assertTrue(CarteBonusPlus(2) is Carte)
    }

    @Test
    fun testCarteBonusPlusNestPasUneCarteSpeciale() {
        Assertions.assertFalse(CarteBonusPlus(2).estCarteStop())
    }

    @Test
    fun testCarteBonusPlusNestPasUneCarteNum() {
        Assertions.assertFalse(CarteBonusPlus(2).estCarteNum())
    }

    // CarteBonusMultiplie
    @Test
    fun testCarteBonusMultiplieVautDeuxEnInterne() {
        Assertions.assertEquals(2, CarteBonusMultiplie().valeur)
    }

    @Test
    fun testCarteBonusMultiplieEstUneCarteBonus() {
        Assertions.assertTrue(CarteBonusMultiplie() is CarteBonus)
    }

    @Test
    fun testCarteBonusMultiplieEstUneCarte() {
        Assertions.assertTrue(CarteBonusMultiplie() is Carte)
    }

    @Test
    fun testCarteBonusMultiplieNestPasUneCarteSpeciale() {
        Assertions.assertFalse(CarteBonusMultiplie().estCarteStop())
    }

    @Test
    fun testCarteBonusMultiplieNestPasUneCarteNum() {
        Assertions.assertFalse(CarteBonusMultiplie().estCarteNum())
    }

    // bornes et grands écarts
    @Test
    fun testCarteNum5PlusPetiteQueCarteNum6() {
        Assertions.assertTrue(CarteNum(5) < CarteNum(6))
    }

    @Test
    fun testCarteNum3PlusGrandeQueCarteNum2() {
        Assertions.assertTrue(CarteNum(3) > CarteNum(2))
    }

    @Test
    fun testCarteNum12CompareToCarteNum12EgalZero() {
        Assertions.assertEquals(0, CarteNum(12).compareTo(CarteNum(12)))
    }

    @Test
    fun testCarteBonusPlusValeurDeux() {
        Assertions.assertEquals(2, CarteBonusPlus(2).valeur)
    }

    @Test
    fun testCarteBonusPlusValeurQuatre() {
        Assertions.assertEquals(4, CarteBonusPlus(4).valeur)
    }

    @Test
    fun testCarteNum1PlusPetiteQueCarteNum12() {
        Assertions.assertTrue(CarteNum(1) < CarteNum(12))
    }

    @Test
    fun testCarteNum6PlusPetiteQueCarteNum7() {
        Assertions.assertTrue(CarteNum(6) < CarteNum(7))
    }

    @Test
    fun testCarteNum7PlusGrandeQueCarteNum6() {
        Assertions.assertTrue(CarteNum(7) > CarteNum(6))
    }

    @Test
    fun testCarteStopNestPasUneCarteBonus() {
        Assertions.assertFalse(CarteStop().estCarteBonusPlus() || CarteStop().estCarteBonusMultiplie())
    }
}