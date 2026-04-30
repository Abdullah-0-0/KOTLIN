import iut.math.Rational
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestDivRational {
    
    @Test
    fun testDivide1() {
        val a = Rational(1, 2)
        val b = Rational(3, 4)
        val result = a / b
        assertEquals(Rational(2, 3), result)
    }

    @Test
    fun testDivide2() {
        val a = Rational(1, 2)
        val b = Rational(4, 3)
        val result = a / b
        assertEquals(Rational(3, 8), result)
    }

    @Test
    fun testDivide3() {
        val a = Rational(1, 2)
        val b = Rational(-4, 3)
        val result = a / b
        assertEquals(Rational(-3, 8), result)
    }

    @Test
    fun testDivide4() {
        val a = Rational(1, 2)
        val b = 2
        val result = a / b
        assertEquals(Rational(1, 4), result)
    }

    @Test
    fun testDivide5() {
        val a = Rational(1, 2)
        val b = 1
        val result = a / b
        assertEquals(Rational(1, 2), result)
    }

    @Test
    fun testDivide6() {
        val a = Rational(1, 2)
        val b = -2
        val result = a / b
        assertEquals(Rational(-1, 4), result)
    }

    @Test
    fun testDivide7() {
        val a = Rational(0, 2)
        val b = Rational(1, 2)
        val result = a / b
        assertEquals(Rational(0, 4), result)
    }

    @Test
    fun testDivide8() {
        val a = Rational(1, 2)
        val b = Rational(0, 2)
        assertThrows<ArithmeticException> { val result = a / b }

    }

}