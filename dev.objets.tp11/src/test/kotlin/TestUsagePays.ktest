import exo2.Pays
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestUsagePays {

    lateinit var france: Pays
    lateinit var allemagne: Pays
    lateinit var france2: Pays

    @BeforeEach
    fun setUp() {
        allemagne = Pays("Allemagne", "Berlin", "Europe", 81413145, 357021.0)
        france = Pays("France", "Paris", "Europe", 66808385, 640597.0)
        france2 = Pays("France", "", "", 0, 0.0)
    }

    @Test
    fun compareTo1() {
        Assertions.assertTrue(allemagne.compareTo(france) > 0)
    }

    @Test
    fun compareTo2() {
        Assertions.assertTrue(france.compareTo(france2) > 0)
    }

    @Test
    fun compareTo3() {
        Assertions.assertTrue(france.compareTo(france) == 0)
    }

    @Test
    fun comparaison1() {
        Assertions.assertTrue(allemagne > france)
    }

    @Test
    fun comparaison2() {
        Assertions.assertTrue(allemagne >= france)
    }

    @Test
    fun comparaison3() {
        Assertions.assertFalse(allemagne < france)
    }

    @Test
    fun comparaison4() {
        Assertions.assertTrue(france <= france)
    }

    @Test
    fun testEquals() {
        Assertions.assertEquals(france, france2)
    }

    @Test
    fun testNotEquals() {
        Assertions.assertNotEquals(allemagne, france)
    }

    @Test
    fun egalite() {
        Assertions.assertTrue(france == france2)
    }

    @Test
    fun egaliteRef() {
        Assertions.assertTrue(france == france)
    }

    @Test
    fun nonEgalite() {
        Assertions.assertFalse(france == allemagne)
    }

    @Test
    fun nonDifference() {
        Assertions.assertFalse(france != france2)
    }

    @Test
    fun difference() {
        Assertions.assertTrue(france != allemagne)
    }
}