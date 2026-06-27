package TestCarteStop

import iut.info1.flip7.cartes.CarteStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteStopEstCarte2ndeChance {
    @Test
    fun testCarteEstCarteBonusMultiplie(){
        val carte = CarteStop()
        assertEquals(carte.estCarte2ndeChance(),false)
    }
}