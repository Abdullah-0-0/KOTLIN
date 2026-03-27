

import  kotlin.random.*

//import java.awt.Point
fun main() {
	//PARTIE 1
	var p1 = Point()
	var p2 = Point(1,2)
	println("x = ${p1.getX()}, y =${p2.getX()}")
	p2.setY(3)
	println(p2)
	p2.translater(1,1)
	println(p2)
	p1.deplacer(2,10)
	println("la distance de l'origine du point p2 : ${p2.distanceOrigine()}")
	println("la distance de p1 a p2 vaut ${p1.distance(p2)}")
	p2.setX(p1.getX())
	p2.setY(p1.getY())
	println("p1 : ${p1} , p2 : ${p2}")
	p1.translater(2,1)
	println("p1 : ${p1} , p2 : ${p2}")
	println("_____________________PARTIE2________________")
	// PARTIE 2
	val tab = arrayOfNulls<Point>(10)
	tab[0]=p1
	affiche(tab)
	
}
fun affiche(points : Array<Point?>){
	for ( i in points){
			println("${i}")
	}
}

fun remplir(point : Array<Point?>): (tab:Array<Point?>){
	var poi = Point()
	for (i in point){
		if (i == Null){
			poi.setX((0..10).random())
			poi.setY((0..10).random())
		}
	}
}