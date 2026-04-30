package exo2

fun main() {
    val d = donneDoubleSaisi()
    println("La valeur saisie est $d")
}

fun donneDoubleSaisi() : Double {
    var result = 0.0
    // TODO : faire le necessaire pour eviter l'erreur
    print("Saisir un double : ")
    var chaineLue = readln()
    try {
        result = chaineLue.toDouble()
    }
    catch (e : java.lang.NumberFormatException){
        result = -1.0
        println("caractére invalide")

    }
    catch (e : java.lang.NumberFormatException){
        result = -1.0
        println("caractére invalide")

    }
    /*finally {
        chaineLue = readln()

    }*/

    // END OF TODO
    return result
}