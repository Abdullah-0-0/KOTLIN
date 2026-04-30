package exo5

fun main() {
    println(evaluation("3 2 4 * +"))
    println(evaluation("2 8 + 2 /"))
    println(evaluation("2 8 + /")) // erreur
    println(evaluation("2 8 + 2 4 /")) // erreur
}

fun evaluation(expression : String) : Int {
    // NB : une chaine de caractères = un tableau de caractères
    TODO()
}