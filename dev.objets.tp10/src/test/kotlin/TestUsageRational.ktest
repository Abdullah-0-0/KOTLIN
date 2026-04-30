import iut.math.Rational
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class TestUsageRational {

    @Test
    fun test10() {
        var r = Rational(2,3)
        assertEquals("2/3", r.toString())
    }

    @Test
    fun test11() {
        var r = Rational(-2,3)
        assertEquals("-2/3", r.toString())
    }

    @Test
    fun test12() {
        var r = Rational(2,-3)
        assertEquals("2/-3", r.toString())
    }

    @Test
    fun test14() {
        assertThrows<IllegalArgumentException> {  Rational(2,0)}
    }

    @Test
    fun test15() {
        var r = Rational(2)
        assertEquals("2/1", r.toString())
    }

}