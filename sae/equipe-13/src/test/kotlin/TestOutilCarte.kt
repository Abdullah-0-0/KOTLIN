import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.OutilsCarte
import iut.info1.flip7.exceptions.MainInvalideException
import iut.info1.flip7.exceptions.PiocheInvalideException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TestOutilCarte {
    @ParameterizedTest
    @MethodSource("parametreOutilsCarte")
    fun testOutilsCarte(typeTest: String, liste_carte_control: ArrayList<Int>, res : Any){
        val outils = OutilsCarte()
        var liste_carte = arrayListOf<Carte>()
        if (typeTest=="estFlip7") {
            if (res == false){
                for (i in 0 until liste_carte_control.size) {
                    liste_carte.add(CarteNum(liste_carte_control[i]))
                }
                assertFalse(outils.estFlip7(liste_carte))
            }
            if(res == MainInvalideException::class.java){
                for (i in 0 until liste_carte_control.size) {
                    liste_carte.add(CarteNum(liste_carte_control[i]))
                }
                assertThrows(MainInvalideException::class.java) { outils.estFlip7(liste_carte) }
            }
        }
        if(typeTest == "calculScore"){
            if (res == MainInvalideException::class.java){
                for (i in 0 until liste_carte_control.size) {
                    liste_carte.add(CarteNum(liste_carte_control[i]))
                }
                assertThrows(MainInvalideException::class.java){
                    outils.calculScore(liste_carte)
                }
            }
            if (res is Int){
                for (i in 0 until liste_carte_control.size) {
                    liste_carte.add(CarteNum(liste_carte_control[i]))
                }
                assertEquals(res,outils.calculScore(liste_carte))
            }

        }
        if (typeTest=="verifiePiocheInitiale"){

            val outil = OutilsCarte()
            val liste_carte = arrayListOf<Carte>()
            liste_carte.add(CarteNum(0))

            for (i in 0 until liste_carte_control.size){
                for (j in 0 until liste_carte_control[i]){
                    liste_carte.add(CarteNum(liste_carte_control[i]))
                }
            }


            for (i in 2..10 step 2) {
                liste_carte.add(CarteBonusPlus(i))
            }
            liste_carte.add(CarteBonusMultiplie())
            for(i in 1 until 4){
                liste_carte.add(CarteStop())
                liste_carte.add(Carte2ndeChance())
                liste_carte.add(Carte3aLaSuite())
            }
            if (res == true){
                assertDoesNotThrow{
                    outils.verifiePiocheInitiale(liste_carte)
                }
            }
            if(res == false){
                assertThrows(PiocheInvalideException::class.java){
                    outils.verifiePiocheInitiale(liste_carte)
                }
            }

        }
        if(typeTest=="verifieMainCorrecte"){
            val outil = OutilsCarte()
            val liste_carte = arrayListOf<Carte>()
            for (i in 0 until liste_carte_control.size){
                liste_carte.add(CarteNum(liste_carte_control[i]))
            }
            if (res==true){
                assertDoesNotThrow {
                    outils.verifieMainCorrecte(liste_carte)
                }
            }
            if(res == false){
                assertThrows(MainInvalideException::class.java){
                    outils.verifieMainCorrecte(liste_carte)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun parametreOutilsCarte(): Stream<Arguments> {
            return Stream.of(
                of("estFlip7", arrayListOf<Int>(2, 5, 9, 9, 6, 8), false),
                of("estFlip7", arrayListOf<Int>(8, 3, 8, 8, 8, 5), MainInvalideException::class.java),
                of("estFlip7", arrayListOf<Int>(10, 11, 0, 10, 4, 9), false),
                of("estFlip7", arrayListOf<Int>(11, 5, 0, 6, 1, 0), MainInvalideException::javaClass),
                of("estFlip7", arrayListOf<Int>(7, 10, 9, 1, 6, 3), true),
                of("estFlip7", arrayListOf<Int>(6, 4, 9, 1, 7, 10), true),
                of("estFlip7", arrayListOf<Int>(6, 0, 10, 5, 1, 1), MainInvalideException::javaClass),
                of("estFlip7", arrayListOf<Int>(1, 7, 0, 0, 11, 7), MainInvalideException::javaClass),
                of("calculScore", arrayListOf<Int>(8, 9, 3, 4, 5, 6), 35),
                of("calculScore", arrayListOf<Int>(9, 0, 9, 7, 1, 10), 0),
                of("calculScore", arrayListOf<Int>(6, 9, 9, 9, 2, 9), MainInvalideException::class.java),
                of("calculScore", arrayListOf<Int>(6, 4, 9, 1, 7, 10), 37),
                of("calculScore", arrayListOf<Int>(5, 2, 1, 5, 3, 4), MainInvalideException::javaClass),
                of("verifiePiocheInitiale", arrayListOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), true),
                of("verifiePiocheInitiale", arrayListOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12), false),
                of("verifiePiocheInitiale", arrayListOf<Int>(0, 1, 2, 7, 4, 5), false),
                of("verifieMainCorrecte", arrayListOf<Int>(0, 1, 2, 5, 7, 8, 9, 5), false),
                of("verifieMainCorrecte", arrayListOf<Int>(0, 5), true),
                of("verifieMainCorrecte", arrayListOf<Int>(0, 1, 2, 3, 4, 5, 6), true),
            )
        }
    }

}