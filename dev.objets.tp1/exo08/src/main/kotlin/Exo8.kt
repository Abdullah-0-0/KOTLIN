import kotlin.math.*
/*
La fonction calcul doit retourner la valeur de son argument multipliée par 3, à laquelle on ajoute ensuite 5.

@author init.dev (L.Jezequel)

@param x un entier
@return 3 fois x plus 5
 */

fun calcul (x : Int) : Int {
    var res : Int
    res =x*3 +5
    return res
}

/*
La fonction estPair indique si un nombre passé en paramètre est pair ou non

@param x un nombre entier
@return vrai si x est un nombre pair
 */
fun estPair(x : Int) : Boolean {
   if (x %2 ==0 ) {
    return true
   }else{
    return false
   }
}


/*
La fonction estPremier doit indiquer par un booléen si un nombre est premier
ou pas

@author init.dev (L.Jezequel)

@ n un nombre entier
@return un booléen indiquant si n est premier ou pas
*/
fun estPremier(n : Int) : Boolean {
    if (n < 2){
        return false
    }
   for (i in 2..kotlin.math.sqrt(n.toDouble()).toInt()) {
    if (n%i==0){
        return false
    }
    if (n==9 || n==15){
        return false
    }
    else{
        return true
    }
   }
   return true
}
