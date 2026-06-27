import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.exceptions.EtatPartieInvalideException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestFlip7joueurCourantPiocheUneCarte{
    val joueur1: IJoueur = object : IJoueur {
        override fun donneNom(): String {
            return ""
        }
    }
    val joueur2: IJoueur = object : IJoueur {
        override fun donneNom(): String {
            return ""
        }
    }
    val joueur3: IJoueur = object : IJoueur {
        override fun donneNom(): String {
            return ""
        }
    }
    val joueur4: IJoueur = object : IJoueur {
        override fun donneNom(): String {
            return ""
        }
    }

    var pioche = mutableListOf<Carte>()
    lateinit var jeu: Flip7

    @BeforeEach
    fun setUp() {
        pioche.clear()
        for (i in 0 until 10){
            pioche.add(CarteNum(i))
        }

        jeu = Flip7(4, listOf<IJoueur>(joueur1, joueur2, joueur3, joueur4), pioche, true, 50)
    }

    @Test
    fun attenteChoixJoueur(){
        jeu.joueurCourantDitStop()
        jeu.joueurCourantDitStop()
        jeu.joueurCourantDitStop()
        assertDoesNotThrow<Carte> {
            jeu.joueurCourantPiocheUneCarte()
        }
    }

    @Test
    fun piocheMancheTerminee(){
        jeu.joueurCourantDitStop()
        jeu.joueurCourantDitStop()
        jeu.joueurCourantDitStop()
        jeu.joueurCourantDitStop()
        assertThrows<EtatPartieInvalideException> {
            jeu.joueurCourantPiocheUneCarte()
        }
    }

    @Test
    fun piochePartieTerminee(){
        for (j in 0 until 2) {
            try {
                jeu.nouvelleManche()
            } catch (e: EtatPartieInvalideException) {
            }
            jeu.joueurCourantDitStop()
            jeu.joueurCourantDitStop()
            jeu.joueurCourantDitStop()
            for (i in 0 until 7) {
                jeu.joueurCourantPiocheUneCarte()
            }
            print(jeu.scoreManche())
        }
        print(jeu.etatPartie)
        assertThrows<EtatPartieInvalideException> {
            jeu.joueurCourantPiocheUneCarte()
        }
    }


}
