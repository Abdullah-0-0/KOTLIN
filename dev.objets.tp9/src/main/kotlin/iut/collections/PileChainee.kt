package iut.collections

import kotlin.contracts.Returns

open class PileChainee(): iPile  {
    protected var debut : Cellule ? = null
    override fun estVide(): Boolean {
        if (debut == null){
            return true
        }
        return false
    }
     override fun taille(): Int {
         var som : Int= 1
         if (estVide()){
             return  0
         }
        while (debut?.suivant()!=null){
            som += 1
            debut = debut?.suivant()
        }
         return som
     }

     override fun consulter(): Int {
         if (estVide()){
             throw NoSuchElementException()
         }
         return debut!!.valeur()

     }

     override fun empiler(element: Int) {
         
     }

     override fun depiler(): Int {
         if (estVide()){
             throw NoSuchElementException()
         }
         debut = debut!!.suivant()
         return debut!!.valeur()
     }


 }