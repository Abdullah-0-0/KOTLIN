import iut.math.pgcd
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestUsagePgcd {

    @Test
    fun testPgcd_6_9() {
        assertEquals(3, pgcd(6, 9))
    }

    @Test
    fun testPgcd_2_3() {
        assertEquals(1, pgcd(2, 3))
    }

    @Test
    fun testPgcd_18_12() {
        assertEquals(6, pgcd(18, 12))
    }

    @Test
    fun testPgcd_100_45() {
        assertEquals(5, pgcd(100, 45))
    }

    @Test
    fun testPgcd_minus18_12() {
        assertEquals(6, pgcd(-18, 12))
    }

    @Test
    fun testPgcd_18_minus12() {
        assertEquals(6, pgcd(18, -12))
    }

    @Test
    fun testPgcd_minus18_minus12() {
        assertEquals(6, pgcd(-18, -12))
    }

}
