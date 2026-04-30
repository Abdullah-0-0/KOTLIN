import iut.math.Rational
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestReduceRational {


    @Test
    fun   test1() {
        val ratio = Rational(4,8)
        val result = ratio.reduce()
        assertEquals("1/2", result.toString())
    }

    @Test
    fun   test2() {
        val ratio = Rational(12,3)
        val result = ratio.reduce()
        assertEquals("4/1", result.toString())
    }

    @Test
    fun   test3() {
        val ratio = Rational(5*5*5*5*5*6*7*3, 5*5*5*5*3*3*2)
        val result = ratio.reduce()
        assertEquals("35/1", result.toString())
    }

    @Test
    fun   test4() {
        val ratio = Rational(-12,3)
        val result = ratio.reduce()
        assertEquals("-4/1", result.toString())
    }


    @Test
    fun   test5() {
        val ratio = Rational(12,-3)
        val result = ratio.reduce()
        assertEquals("-4/1", result.toString())
    }

    @Test
    fun   test6() {
        val ratio = Rational(-12,-3)
        val result = ratio.reduce()
        assertEquals("4/1", result.toString())
    }



}