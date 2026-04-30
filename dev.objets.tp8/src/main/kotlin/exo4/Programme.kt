package exo4

import exo3.pile.Pile
import kotlin.random.Random

fun main() {

    var ram = Random.nextInt(-10,10)
    while(ram <=0) {
        ram = Random.nextInt(-10,10)
    }
    println("la taille est de $ram")
    var pile = Pile(ram)
//    try{
//        var pile = Pile(ram)
//    }
//    catch(e : java.lang.NegativeArraySizeException) {
//        ram = Random.nextInt(-10,10)
//    }
    var count = 100
    while(count > 0){
        count--
        ram = Random.nextInt(0, 2)
        if (ram == 0) {
            pile.empiler(Random.nextInt(-10,10))
        } else {
            pile.depiler()
        }
        println(pile)

    }
//    pile.empiler(Random.nextInt(-10,10))
//    println(pile)
//    pile.empiler(Random.nextInt(-10,10))
//    println(pile)
//    pile.empiler(Random.nextInt(-10,10))
//    println(pile)
//    pile.depiler()
//    println(pile)
//    pile.depiler()
//    println(pile)

}

