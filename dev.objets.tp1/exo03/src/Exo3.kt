fun main() {
    // TODO
    var alpha : Double
    var beta : Int = 42
    var delta = 42.0
    /*for (a  in beta downTo 0 ) {
        print(a)
    } */
    beta--
    println(delta)
    alpha = delta
    println(alpha)
    delta+=3
    println("${delta},${alpha}")
    var test : Boolean = alpha==delta
    println(test)
    alpha = dec2(alpha,beta)
    println("${alpha} ${delta}")

}
fun dec2(epsilon : Double , gamma: Int)  = epsilon /gamma
