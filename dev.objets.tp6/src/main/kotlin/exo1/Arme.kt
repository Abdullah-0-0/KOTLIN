package exo1

class Arme(desc : String , att : Int , def : Int) : Equipement(desc) {
    private  var attaque : Int
    private var defense : Int
    init {
        this.attaque = att
        this.defense = def
    }

    override fun toString(): String {
        return "($attaque ,$defense)"
    }
}
