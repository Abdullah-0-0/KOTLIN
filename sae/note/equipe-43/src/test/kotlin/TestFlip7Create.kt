import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.IOutilsCarte
import iut.info1.flip7.cartes.OutilsCarte
import iut.info1.flip7.exceptions.CarteInvalideException
import iut.info1.flip7.exceptions.ListeJoueursInvalideException
import iut.info1.flip7.exceptions.NombreJoueursInvalideException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TestFlip7Create {
    var cartes = mutableListOf<Carte>()

    val joueur1: IJoueur = object : IJoueur {
        override fun donneNom(): String {
            TODO("Not yet implemented")
        }
    }

    @BeforeEach
    fun set_up() {
        for (i in 0 until 13) {
            cartes.add(CarteNum(i))
        }
        for (i in 2 until cartes.size) {
            for (j in 0 until cartes[i].valeur - 1) {
                cartes.add(CarteNum(i))
            }
        }
        for (i in 2 until 11 step (2)) {
            cartes.add(CarteBonusPlus(i))
        }
        for (i in 0 until 3) {
            cartes.add(Carte2ndeChance())
            cartes.add(Carte3aLaSuite())
            cartes.add(CarteStop())
        }
        cartes.add(CarteBonusMultiplie())
    }

    @ParameterizedTest
    @CsvSource(
        "-5, 1,-1,false,200",
        "-5, 5,0,true,150",
        "-5, 0,1,false,-200",
        "5, 1,-1,false,200",
        "5, 5,0,true,150",
        "5, 0,1,false,-200"
    )
    fun NbJoueursInvalide(nbJoueurs: Int, lenJoueurs: Int, addCartes: Int, debug: Boolean, scoreFinPartie: Int) {

        if (addCartes == -1) {
            cartes = mutableListOf<Carte>()
        } else if (addCartes == 1) {
            cartes.add(CarteNum(1))
        }

        var joueurs = mutableListOf<IJoueur>()
        for (i in 0 until lenJoueurs) {
            joueurs.add(joueur1)
        }

        assertThrows<NombreJoueursInvalideException> {
            Flip7(
                nbJoueurs,
                joueurs,
                cartes,
                debug,
                scoreFinPartie,
                OutilsCarte()
            )
        }
    }

    @ParameterizedTest
    @CsvSource(
        "3, 1,-1,false,200",
        "3, 1,0,true,150",
        "3, 1,1,false,-200",
        "3, 5,-1,false,200",
        "3, 5,0,true,150",
        "3, 5,1,false,-200"
    )
    fun ListeJoueursInvalide(nbJoueurs: Int, lenJoueurs: Int, addCartes: Int, debug: Boolean, scoreFinPartie: Int) {

        if (addCartes == -1) {
            cartes = mutableListOf<Carte>()
        } else if (addCartes == 1) {
            cartes.add(CarteNum(1))
        }

        var joueurs = mutableListOf<IJoueur>()
        for (i in 0 until lenJoueurs) {
            joueurs.add(joueur1)
        }

        assertThrows<ListeJoueursInvalideException> {
            Flip7(
                nbJoueurs,
                joueurs,
                cartes,
                debug,
                scoreFinPartie,
                OutilsCarte()
            )
        }
    }


    @ParameterizedTest
    @CsvSource(
        "3, 3, 0,false,-200",
        "3, 3, 0,true,-200"
    )
    fun ScoreFinPartieInvalide(nbJoueurs: Int, lenJoueurs: Int, addCartes: Int, debug: Boolean, scoreFinPartie: Int) {

        if (addCartes == -1) {
            cartes = mutableListOf<Carte>()
        } else if (addCartes == 1) {
            cartes.add(CarteNum(1))

            var joueurs = mutableListOf<IJoueur>()
            for (i in 0 until lenJoueurs) {
                joueurs.add(joueur1)
            }

            assertThrows<CarteInvalideException> {
                Flip7(
                    nbJoueurs,
                    joueurs,
                    cartes,
                    debug,
                    scoreFinPartie,
                    OutilsCarte()
                )
            }
        }
    }

    @ParameterizedTest
    @CsvSource(
        "3, 3, 0,false,200",
        "3, 3, 0,true,200",
    )
    fun Flip7Valide(nbJoueurs: Int, lenJoueurs: Int, addCartes: Int, debug: Boolean, scoreFinPartie: Int) {

        if (addCartes == -1) {
            cartes = mutableListOf<Carte>()
        } else if (addCartes == 1) {
            cartes.add(CarteNum(1))

            var joueurs = mutableListOf<IJoueur>()
            for (i in 0 until lenJoueurs) {
                joueurs.add(joueur1)
            }

            assertThrows<CarteInvalideException> {
                Flip7(
                    nbJoueurs,
                    joueurs,
                    cartes,
                    debug,
                    scoreFinPartie,
                    OutilsCarte()
                )
            }
        }
    }
}
