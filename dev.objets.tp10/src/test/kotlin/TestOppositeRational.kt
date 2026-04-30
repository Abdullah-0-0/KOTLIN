import iut.math.Rational
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestOppositeRational {


    @Test
    fun   test1() {
        val ratio = Rational(1,2)
        val result = ratio.opposite()
        assertEquals("-1/2", result.toString())
    }

    @Test
    fun   test2() {
        val ratio = Rational(2,3)
        val result = ratio.opposite()
        assertEquals("-2/3", result.toString())
    }

    @Test
    fun   test3() {
        val ratio = Rational(4,3)
        val result = ratio.opposite()
        assertEquals("-4/3", result.toString())
    }

    @Test
    fun   test4() {
        val ratio = Rational(-4,3)
        val result = ratio.opposite()
        assertEquals("4/3", result.toString())
    }


}