import info.but1.collections.*
import kotlin.math.abs

class Rectangle(p1: Point , p2:Point){
    var sommets : NutArray<Point> = nutArrayOf(p1,p2,Point(p1.x,p1.y),Point(p2.x,p2.y))
        private set
    
    fun surface(): Int {
        var lar : Int
        var lon : Int
        var som : Int
        for (i in sommets.size) {
            if (i%2==0){
                lon+=sommets[i].x
            }
            lar+=sommets[i].y
        }
        return som = lar + lon
    }
    fun perimetre(): Int {
        var per : Int
        for (i in sommets.size){
            if (i%2==0){
                per+=sommets[i].x
            }
            per+=sommets[i].y
        }
        return per
    }
    fun deplacer(v:Vecteur){

        for (i in sommets.size) {
            sommets[i].x += v.distanceX()
            sommets[i].y += v.distanceY()
        }
        return sommets
    } 
    fun translater(v:Vecteur) :Rectangle = TODO()
    override fun toString():String = TODO()
}