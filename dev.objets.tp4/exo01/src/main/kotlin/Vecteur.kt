class Vecteur(orig: Point = Point(0,0), dest: Point){
    private var origine: Point 
    private var destination : Point

    init{
        this.origine =orig
        this.destination =dest
    }
    
    fun distanceX(): Int {
        return destination.x - origine.x
    }

    fun distanceY(): Int {
        return destination.y - origine.y
    }
    override fun toString() : String {
        return "vecteur: (${distanceX()}), ${distanceY()})"
    }
}