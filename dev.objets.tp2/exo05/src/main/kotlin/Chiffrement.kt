import info.but1.collections.*

/*
La fonction chiffrement renvoie une chaine de caractère chfiffrée en ayant exécutée 
un décalage de caractères.
 
@param original : la chaine à chiffrer
@param decalage : le décalage à appliquer

@return la chaine chiffrée (en majuscule)
*/

fun chiffrement(original : String, decalage : Int) : String {
    TODO()
}

/* definie un tableau de lettres pour représenter l'alphabet */
val ALPHABET = nutArrayOf(' ',
                       'A','B','C','D','E',
                       'F','G','H','I','J',
                       'K','L','M','N','O',
                       'P','Q','R','S','T',
                       'U','V','W','X','Y','Z')

/**
 * donne la lettre de l'ALPHABET à la position indiquée
 * @param pos : la position
 * @return le caractère à la position demandée
 */
fun lettre(pos : Int) = ALPHABET[pos]                       

/**
* donne la position d'un caractère dans le tableau ALPHABET
* @param c : le caractère recherché
* @return la position
*/
fun position(c : Char) : Int {
    TODO()
}




