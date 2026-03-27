package exo1

class Sort(nom : String , desc : String)  {
    // TODO
    var nom : String
    private  var description : String
    init {
        this.nom =nom
        this.description =desc
    }

    override fun toString(): String {
        return "$nom : $description"
    }
    override fun equals(other: Any?): Boolean = TODO()

}