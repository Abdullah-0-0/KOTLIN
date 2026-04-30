/** NE PAS MODIFIER */

class Vecteur(
    private val origine: Point,
    private val destination: Point
) {

    fun appliquer(point: Point): Point {
        val dx = destination.x - origine.x
        val dy = destination.y - origine.y
        return Point(point.x + dx, point.y + dy)
    }
}