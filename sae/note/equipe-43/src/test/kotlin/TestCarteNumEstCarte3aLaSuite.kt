import iut.info1.flip7.cartes.CarteNum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestCarteNumEstCarte3aLaSuite {
    @Test
    fun testCarteEstCarteBonusMultiplie(){
        val carte = CarteNum(6)
        assertEquals(carte.estCarte3aLaSuite(),false)
    }
}