
/*    modifier UNIQUEMENT la ligne suivante, le reste du code doit rester inchangé   */
fun foo(name: String, number: Int=42, toUpperCase: Boolean=false) : String  {
    // ne rien modifier en dessous ----
    val resultat = 
        if (toUpperCase) {
            name.toUpperCase()
            // .toUpperCase() transforme la chaine considérée en majuscule
            // remplacé par .uppercase() à partir de kotlin 1.5
        } 
        else {
            name
        }
        
    return resultat + number
}

fun main() {
    val res1 = foo("a")
    val res2 = foo("b", number = 1)
    val res3 = foo("c", toUpperCase = true)
    val res4 = foo(name = "d", number = 2, toUpperCase = true)

    println(if (res1 == "a42") "OK" else "ko")
    println(if (res2  == "b1") "OK"  else "ko")
    println(if (res3 == "C42") "OK"  else "ko")
    println(if (res4 == "D2") "OK" else "ko" )
}



