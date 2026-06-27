import iut.info1.flip7.cartes.OutilsCarte
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.exceptions.PiocheInvalideException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class TestOutilCarteVerifiePioche  {

    val outilsCarte = OutilsCarte()

    @Test
    fun testVerifiePiocheInitialeMoins() {

        var pioche = mutableListOf<Carte>()

        assertThrows<PiocheInvalideException>{
            outilsCarte.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun testVerifiePiocheInitialeOk(){

        var pioche = mutableListOf<Carte>()

        for (i in 0 until 13){
            pioche.add(CarteNum(i))
        }
        for (i in 2 until pioche.size){
            for (j in 0 until pioche[i].valeur-1){
                pioche.add(CarteNum(i))
            }
        }
        for (i in 2 until 11 step(2)){
            pioche.add(CarteBonusPlus(i))
        }
        for (i in 0 until 3){
            pioche.add(Carte2ndeChance())
            pioche.add(Carte3aLaSuite())
            pioche.add(CarteStop())
        }
        pioche.add(CarteBonusMultiplie())

        assertDoesNotThrow {
            outilsCarte.verifiePiocheInitiale(pioche)
        }
    }

    @Test
    fun testVerifiePiocheInitialePlus() {
        var pioche = mutableListOf<Carte>()

        for (i in 0 until 13) {
            pioche.add(CarteNum(i))
        }
        for (i in 2 until pioche.size) {
            for (j in 0 until pioche[i].valeur - 1) {
                pioche.add(CarteNum(i))
            }
        }
        for (i in 2 until 11 step (2)) {
            pioche.add(CarteBonusPlus(i))
        }
        for (i in 0 until 3) {
            pioche.add(Carte2ndeChance())
            pioche.add(Carte3aLaSuite())
            pioche.add(CarteStop())
        }
        pioche.add(CarteBonusMultiplie())
        pioche.add(CarteNum(1))

        assertThrows <PiocheInvalideException> {
            outilsCarte.verifiePiocheInitiale(pioche)
        }
    }
}

