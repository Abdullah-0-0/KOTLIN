class Point(x: Int =0 ,y : Int = 0) {
    var x : Int
        private set  
    var y : Int 
        private set
    init{
        this.x = x
        this.y = y
    }

    fun translater(v : Vecteur) : Point {
        return Point(x+v.distanceX(),y+v.distanceY())
    }

    override fun toString(): String{
        return "($x,$y)"
    }

}
