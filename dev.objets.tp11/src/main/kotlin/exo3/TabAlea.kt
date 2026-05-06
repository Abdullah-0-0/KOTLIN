package exo3

fun main() {
    val tableau = tableauAleatoireDistinct(10)
    print("[")
    for (v in tableau)
        print("$v-")
    println("]")
}

fun tableauAleatoireDistinct(n: Int): Array<Int> {
    val resultat = Array<Int>(n, { i -> 0 })
    // tableau de taille n complètement initialisé à 0

    TODO()

    return resultat
}