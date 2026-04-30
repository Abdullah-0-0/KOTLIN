package iut.math

/**
 * renvoie le "Plus Grand Commun Diviseur" de deux entiers
 * @param aa un entier
 * @param bb un entier
 * @return le pgcd de aa et bb
 */
fun pgcd(aa : Int, bb : Int) : Int {

    if (aa < 0){return  pgcd(-aa,bb)}
    if (bb < 0){ return  pgcd(aa,-bb)}
//    if(aa<0 && bb <0){
//        return pgcd(-aa,-bb)
//    }

    if (aa>bb && aa>0 ){
        return pgcd(aa-bb,bb)
    }
    if (aa == bb ){
        return aa

    }
    return pgcd(aa, bb-aa)
}