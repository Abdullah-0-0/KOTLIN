import iut.math.Rational
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestSumRational {

    var r12 = Rational(1,2)
    var r23 = Rational(2,3)
    var r43 = Rational(4,3)

    @Test
    fun   test1() {
        val result = r23 + r12  // on peut utiliser le + car il y a la fonction plus()
        println(result) // 2/3 + 1/2 = 7/6
        assertEquals("7/6", result.toString()) // 2/3 + 1/2 = 7/6
    }

    @Test
    fun test2() {
        val result = r23 + r43
        println(result) // 18/9
        assertEquals("18/9", result.toString())
    }

    @Test
    fun test3() {
        val result = r12 + r43
        println(result) // 11/6
        assertEquals("11/6", result.toString())
    }

    @Test
    fun test4() {
        val result = r43 + 0
        println(result)
        assertEquals("4/3", result.toString())
    }

    @Test
    fun test5() {
        val result = r43 + 2
        println(result) // 10/3
        assertEquals("10/3", result.toString())
    }

    @Test
    fun test6() {
        val result = r12 + Rational(-1,3)
        println(result) // 1/6
        assertEquals("1/6", result.toString())
    }

    @Test
    fun test7() {
        val result = r43 + (-2)
        println(result) // 10/3
        assertEquals("-2/3", result.toString())
    }
}