package exo1

import info.but1.collections.NutArray
import info.but1.collections.nutArrayOf
import info.but1.collections.nutArrayOfNulls

open class Personnage(nom: String, force : Int, agi : Int, intel : Int) {
    // TODO
    private var nom : String
    private  var carac_force : Int
    private  var carac_agilite : Int
    protected var carac_intelligence : Int
    var  nbEquipementsPossedes : Int = 0
    private var inventaire : NutArray<Equipement?> = nutArrayOfNulls(6)
    //var listInventaire = nutArrayOfNulls<Equipement>(nbEquipementPossedes)
    init{
        this.nom = nom
        this.carac_agilite = agi
        this.carac_force = force
        this.carac_intelligence = intel

    }
    open fun listeCaracteristiques() : String{
        return "for: $carac_force , agi : $carac_agilite,int :$carac_intelligence  "
    }

    fun lEquipementContient(equipement: Equipement) : Boolean {
        for (i in 0..inventaire.size){
            if (inventaire[i]==equipement){
                return  true
            }
            return false
        }
        return false
    }
    fun prend(equipement: Equipement) : Boolean {
        if(lEquipementContient(equipement)== true){
            return false
        }
        for (i in 0..inventaire.size){
            if (inventaire[i]==null){
                inventaire[i] = equipement
                return  true

            }

        }
        return  false
    }

    fun attaque(arme: Arme ,cible : Personnage ): Boolean = TODO()

    override fun toString(): String  {
        return "$nom<FOR=$carac_force,AGI=$carac_agilite,INT=$carac_intelligence>"
    }
    fun listeInventaire(): String {
        var res : String =""
        for (i in 0..inventaire.size) res +=inventaire[i]
        return  res
    }




}