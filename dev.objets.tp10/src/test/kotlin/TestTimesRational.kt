import org.junit.jupiter.api.Test
import iut.math.Rational
import org.junit.jupiter.api.Assertions.*

class TestTimesRational {

    @Test
    fun testTimesRational1() {
        val a = Rational(1, 2)
        val b = Rational(3, 4)
        val result = a * b
        assertEquals(Rational(3, 8), result)
    }

    @Test
    fun testTimesRational2() {
        val a = Rational(1, 2)
        val b = Rational(6, 4)
        val result = a * b
        assertEquals(Rational(6, 8), result)
    }

    @Test
    fun testTimesRationalWithInt3() {
        val a = Rational(1, 2)
        val b = 3
        val result = a * b
        assertEquals(Rational(3, 2), result)
    }

    @Test
    fun testTimesRationalWithInt2() {
        val a = Rational(1, 2)
        val b = 2
        val result = a * b
        assertEquals(Rational(2, 2), result)
    }

    @Test
    fun testTimesRationalWithInt0() {
        val a = Rational(1, 2)
        val b = 0
        val result = a * b
        assertEquals(Rational(0, 1), result)
    }

    @Test
    fun testTimesRationalWithIntMinus3() {
        val a = Rational(1, 2)
        val b = -3
        val result = a * b
        assertEquals(Rational(-3, 2), result)
    }

}