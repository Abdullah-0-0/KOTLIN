package exo1

import info.but1.collections.NutArray
import info.but1.collections.nutArrayOfNulls

class Magicien(nom : String , force : Int , agi : Int ,intel : Int , mag : Int, sort0: Sort)
    : Personnage(nom, force , agi , intel) {
    private  var carac_magie : Int
    private  var grimoire : NutArray<Sort?> = nutArrayOfNulls(10)
    init {
        this.carac_magie = mag
    }
    var nbSortsConnus : Int = 1
    override fun listeCaracteristiques(): String {
        return super.listeCaracteristiques() + " MAG : $carac_magie"
    }
    fun leGrimoireContient(sort: Sort) : Boolean {
        for (i in 0..grimoire.size){
            if (grimoire[i]==sort){
                return true
            }
            return  false
        }
        return  false
    }
    fun apprend(nouveau : Sort) : Boolean{
        if (leGrimoireContient(nouveau) || nbSortsConnus >= grimoire.size) {
            return false
        }
        grimoire[nbSortsConnus] = nouveau
        nbSortsConnus++
        return true
}
    fun lance(sort: Sort): Boolean {
        return leGrimoireContient(sort)
    }
    fun listeDesSorts(): NutArray<Sort?> {
        return grimoire
    }
}