

import kotlin.io.println
fun main() {
    var maSaxo = Vehicule("Saxo","rouge",4,180.0)
    println(maSaxo)
    // TODO
    var mato_m5 = Vehicule("M5","rouge",4,150.0)
    println(mato_m5)
    mato_m5.demarrer()
    mato_m5.accelerer(50.0)
    println(mato_m5)
    mato_m5.accelerer(40.0)
    println(mato_m5.vitesse())
    var mato_m4 = Vehicule("M4","rouge",4,250.0)
    println(mato_m4)
    mato_m4.demarrer()
    mato_m4.accelerer(50.0)
    println(mato_m4)
    mato_m4.accelerer(50.0)
    println(mato_m4.vitesse())
    println( "${mato_m5}\n"+ mato_m5.vaPlusVite(mato_m4))
    println(mato_m4.vaPlusVite(mato_m5))
}
