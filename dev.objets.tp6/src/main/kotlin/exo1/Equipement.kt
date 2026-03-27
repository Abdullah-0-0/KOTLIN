package exo1

open class Equipement(desc : String) {
    var description : String
    init {
        this.description = desc
    }

    override fun toString(): String {
        return "Description : $description"
    }
}