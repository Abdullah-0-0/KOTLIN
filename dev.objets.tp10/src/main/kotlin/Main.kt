import iut.math.Rational

fun main() {


    // usage toString()
    var r12 = Rational(1,2)
    var r23 = Rational(2,3)
    var r43 = Rational(4,3)
    println(r23)     // 2/3
    println(r12)     // 1/2
    println(r43)     // 4/3

    println(Rational(3))      // 3/1
    println(Rational(0,6))     // 0/6
    println(Rational(2,2))     // 2/2
    println(Rational(-1,3))    // -1/3
    println(Rational(1,-3))    // 1/-3
    /*
        try {
            Rational(1,0)
        }
        catch (e : IllegalArgumentException)   {
            println("le denominateur ne peut pas être nul")
        }
        */

}