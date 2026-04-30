package iut.exo1

import java.net.InetAddress

fun main() {
    print("Saisir un nom : ")
    var ip : String =""
    val nom = readln()
    try {
        ip = nomVersIP(nom)
    }
    catch (e : java.net.UnknownHostException) {
       ip = " l'adresse n'est pas valide"
    }

//    ip = nomVersIP(nom)

    println("IP correspondante :$ip")
}


fun nomVersIP(nom : String) : String {
    /*try {
        InetAddress.getByName(nom).hostAddress
    }
    catch (e : java.net.UnknownHostException){
        return "l'adresse n'est pas valide"
    }
    catch (e : IllegalArgumentException){
        return "error : IllegalArgumentException"
    }*/
    return InetAddress.getByName(nom).hostAddress
}