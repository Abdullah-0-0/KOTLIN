import iut.math.Rational
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestMinusRational {

    var r12 = Rational(1,2)
    var r23 = Rational(2,3)
    var r43 = Rational(4,3)

    @Test
    fun   test1() {
        val result = r23 - r12
        assertEquals("1/6", result.toString())
    }

    @Test
    fun test2() {
        val result = r23 - r43
        println(result)
        assertEquals("-6/9", result.toString())
    }

    @Test
    fun test3() {
        val result = r12 - r43
        println(result)
        assertEquals("-5/6", result.toString())
    }

    @Test
    fun test6() {
        val result = r12 - Rational(-1,3)
        println(result)
        assertEquals("5/6", result.toString())
    }

}