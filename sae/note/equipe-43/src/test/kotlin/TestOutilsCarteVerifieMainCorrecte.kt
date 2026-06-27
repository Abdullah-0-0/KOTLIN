import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import iut.info1.flip7.cartes.*
import iut.info1.flip7.exceptions.MainInvalideException

class TestOutilsCarteVerifieMainCorrecte {

    val outils_carte = OutilsCarte()

    @Test
    fun `verifieMainCorrecte - vide`(){
        val main = listOf<Carte>()
        assertDoesNotThrow{
            outils_carte.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `verifieMainCorrecte - carte 4`(){
        val main = listOf<Carte>(CarteNum(4))
        assertDoesNotThrow{
            outils_carte.verifieMainCorrecte(main)
        }
    }


    @Test
    fun `verifieMainCorrecte - carte 4, 5 et 7`(){
        val main = listOf<Carte>(CarteNum(4), CarteNum(5), CarteNum(7))
        assertDoesNotThrow{
            outils_carte.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `verifieMainCorrecte - carte 4*2`(){
        val main = listOf<Carte>(CarteNum(4), CarteNum(4))
        assertDoesNotThrow{
            outils_carte.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `verifieMainCorrecte - carte 4*2 et 5*2`(){
        val main = listOf<Carte>(CarteNum(4), CarteNum(4), CarteNum(5), CarteNum(5))
        assertThrows(MainInvalideException::class.java){
            outils_carte.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `verifieMainCorrecte - carte bonus +2`(){
        val main = listOf<Carte>(CarteBonusPlus(2))
        assertDoesNotThrow{
            outils_carte.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `verifieMainCorrecte - carte multiple`(){
        val main = listOf<Carte>(CarteBonusMultiplie())
        assertDoesNotThrow{
            outils_carte.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `verifieMainCorrecte - carte 4, bonus +4, bonus *`(){
        val main = listOf<Carte>(CarteNum(4),CarteBonusPlus(4),CarteBonusMultiplie())
        assertDoesNotThrow{
            outils_carte.verifieMainCorrecte(main)
        }
    }

}