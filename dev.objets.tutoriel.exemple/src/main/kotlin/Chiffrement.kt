/*
La fonction chiffrement renvoie une chaine de caractère chfiffrée en ayant exécutée 
un décalage de caractères.
 
@param original : la chaine à chiffrer
@param decalage : le décalage à appliquer

@return la chaine chiffrée (en majuscule)
*/

fun chiffrement(original : String, decalage : Int) : String {

    val origin = original.uppercase();
    var chiffre = ""

    for(i in 0 until origin.length) {
        var position = position(origin[i])
        if (position == -1)
            chiffre += origin[i]
        else {
            position = (position + decalage) % 26
            position = if (position ==0) 26 else position
            chiffre += lettre(position)
        } 
    }
    return chiffre
}

fun position(c : Char) : Int {
    for(i in 1 until ALPHABET.size) {
        if(ALPHABET[i] == c) {
            return i;
        }
    }
    return -1;
}

fun lettre(pos : Int) = ALPHABET[pos]


val ALPHABET = arrayOf(' ',
                       'A','B','C','D','E',
                       'F','G','H','I','J',
                       'K','L','M','N','O',
                       'P','Q','R','S','T',
                       'U','V','W','X','Y','Z')