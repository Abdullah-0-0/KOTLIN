import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.OutilsCarte
import iut.info1.flip7.exceptions.MainInvalideException
import iut.info1.flip7.exceptions.PiocheInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OutilsCarteTest {

    private val outils = OutilsCarte()

    // calculScore : somme des numeros, x2 si multiplie, + les bonus plus, +15 si Flip7

    @Test fun calculScoreMainVide() {
        //Teste que la méthode calculScore avec une main vide renvoie 0
        assertEquals(0, outils.calculScore(listOf()))
    }

    @Test fun calculScoreUnNumero() {
        //Teste que la méthode calculScore avec une main composée d'une carte 7 renvoie 7
        assertEquals(7, outils.calculScore(listOf(CarteNum(7))))
    }

    @Test fun calculScoreDeuxNumeros() {
        //Teste que la méthode calculScore avec une main composée d'une carte 3 et d'une carte 5 renvoie 8
        assertEquals(8, outils.calculScore(listOf(CarteNum(3), CarteNum(5))))
    }

    @Test fun calculScoreLeZeroNajouteRien() {
        //Teste que la méthode calculScore avec une main composée d'une carte 0 et d'une carte 5 renvoie 5
        assertEquals(5, outils.calculScore(listOf(CarteNum(0), CarteNum(5))))
    }

    @Test fun calculScoreAvecMultiplie() {
        //Teste que la méthode calculScore avec une main composée de cartes 3 et 5 et d'une carte BonusMultiple x2 renvoie 16
        assertEquals(16, outils.calculScore(listOf(CarteNum(3), CarteNum(5), CarteBonusMultiplie())))
    }

    @Test fun calculScoreMultiplieSurUnNumero() {
        //Teste que la méthode calculScore avec une main composée d'une carte 5 et d'une carte BonusMultiple x2 renvoie 10
        assertEquals(10, outils.calculScore(listOf(CarteNum(5), CarteBonusMultiplie())))
    }

    @Test fun calculScoreAvecUnBonusPlus() {
        //Teste que la méthode calculScore avec une main composée de cartes 3 et 5 et d'une carte BonusPlus +4 renvoie 12
        assertEquals(12, outils.calculScore(listOf(CarteNum(3), CarteNum(5), CarteBonusPlus(4))))
    }

    @Test fun calculScoreAvecPlusieursBonusPlus() {
        //Teste que la méthode calculScore avec une main composée d'une carte 3 et de cartes BonusPlus +2 et +4 renvoie 9
        assertEquals(9, outils.calculScore(listOf(CarteNum(3), CarteBonusPlus(2), CarteBonusPlus(4))))
    }

    @Test fun calculScoreMultipliePuisBonusPlus() {
        //Teste que la méthode calculScore avec une main composée de cartes 3 et 5, d'une carte BonusMultiple x2 et d'une carte BonusPlus +4 renvoie 20.
        //Le BonusPlus +4 ne doit pas être comptabilisé dans le BonusMultiple x2.
        assertEquals(20, outils.calculScore(listOf(CarteNum(3), CarteNum(5), CarteBonusMultiplie(), CarteBonusPlus(4))))
    }

    @Test fun calculScoreFlip7() {
        //Teste que la méthode calculScore avec une main composée de cartes 0, 1, 2, 3, 4, 5 et 6 renvoie 36.
        //Il y a 7 cartes différentes alors il faut comptabiliser le bonus Flip7.
        val main = listOf(CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(5), CarteNum(6))
        assertEquals(36, outils.calculScore(main))
    }

    @Test fun calculScoreFlip7AvecBonusPlus() {
        //Teste que la méthode calculScore avec une main composée de cartes 0, 1, 2, 3, 4, 5 et 6 et d'une carte BonusPlus +10 renvoie 46.
        //Il y a 7 cartes différentes alors il faut comptabiliser le bonus Flip7.
        val main = listOf(CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(5), CarteNum(6), CarteBonusPlus(10))
        assertEquals(46, outils.calculScore(main))
    }

    @Test fun calculScoreCartesSpecialesIgnorees() {
        //Teste que la méthode calculScore avec une main composée d'une carte 3 et de cartes Stop et 2ndeChance renvoie 3.
        //Le but de ce test est de vérifier que les deux cartes spéciales n'ont aucun effet sur le score renvoyé.
        assertEquals(3, outils.calculScore(listOf(CarteNum(3), CarteStop(), Carte2ndeChance())))
    }

    @Test fun calculScoreStopNeChangeRien() {
        //Teste que la méthode calculScore avec une main composée d'une carte 5 et d'une cartes Stop renvoie 5.
        //Le but de ce test est de vérifier que la carte Stop n'a aucun effet sur le score renvoyé.
        assertEquals(5, outils.calculScore(listOf(CarteNum(5), CarteStop())))
    }

    @Test fun calculScoreQueDesSpeciales() {
        //Teste que la méthode calculScore avec une main composée uniquement de cartes spéciales renvoie 0.
        //Le but de ce test est de vérifier que aucune cartes spéciale ne rajoute de points.
        assertEquals(0, outils.calculScore(listOf(CarteStop(), Carte2ndeChance(), Carte3aLaSuite())))
    }

    @Test fun calculScoreAvecDoublonRetourneZero() {
        //Teste que la méthode calculScore avec une main composée de deux cartes identiques renvoie 0.
        //Un doublon de CarteNum fait immédiatemenet perdre le joueur, son score devenant 0 peu importe ses cartes.
        assertEquals(0, outils.calculScore(listOf(CarteNum(5), CarteNum(5))))
    }

    // estFlip7 : true si il y a  7 numeros distincts

    @Test fun estFlip7SeptNumerosDistincts() {
        val main = listOf(CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(5), CarteNum(6))
        assertTrue(outils.estFlip7(main))
    }

    @Test fun estFlip7SixNumeros() {
        val main = listOf(CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(5))
        assertFalse(outils.estFlip7(main))
    }

    @Test fun estFlip7AvecUnDoublon() {
        // doublon sur une valeur autorisee (le 0 et le 1 ne peuvent pas etre en double)
        val main = listOf(CarteNum(5), CarteNum(5), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(6), CarteNum(7))
        assertFalse(outils.estFlip7(main))
    }

    @Test fun estFlip7SeptNumerosEtUneSpeciale() {
        val main = listOf(CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(5), CarteNum(6), CarteStop())
        assertTrue(outils.estFlip7(main))
    }

    @Test fun estFlip7MainVide() {
        assertFalse(outils.estFlip7(listOf()))
    }

    @Test fun estFlip7UnSeulNumero() {
        assertFalse(outils.estFlip7(listOf(CarteNum(5))))
    }

    // verifieMainCorrecte : leve MainInvalideException si la main est impossible

    @Test fun verifieMainSimpleValide() {
        assertDoesNotThrow { outils.verifieMainCorrecte(listOf(CarteNum(3), CarteNum(5), CarteBonusPlus(4))) }
    }

    @Test fun verifieMainVide() {
        assertDoesNotThrow { outils.verifieMainCorrecte(listOf()) }
    }

    @Test fun verifieMainSeptNumUnDoublon() {
        // le doublon est autorise si les valeurs sont de 2 a 12 (ici deux fois le 5)
        val main = listOf(CarteNum(5), CarteNum(5), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(6), CarteNum(7))
        assertDoesNotThrow { outils.verifieMainCorrecte(main) }
    }

    @Test fun verifieMainHuitNumeros() {
        val main = listOf(CarteNum(0), CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(5), CarteNum(6), CarteNum(7))
        assertThrows<MainInvalideException> { outils.verifieMainCorrecte(main) }
    }


    @Test fun verifieMainMemeNumeroTroisFois() {
        //Teste si la méthode verifieMainCorrecte lève une exception si une main a trois cartes aux numéros identiques
        //Un jour ayant deux cartes identiques dans sa main ne peut plus jouer et n'a pas moyen de récupérer une troisième carte identique.
        assertThrows<MainInvalideException> { outils.verifieMainCorrecte(listOf(CarteNum(5), CarteNum(5), CarteNum(5))) }
    }

    @Test fun verifieMainUnMultiplie() {
        // Teste si la méthode verifieMainCorrecte ne lève pas d'exception dans le cas d'une main ayant une carte numéro 3 et une carte Bonus Multiple
        assertDoesNotThrow { outils.verifieMainCorrecte(listOf(CarteNum(3), CarteBonusMultiplie())) }
    }

    @Test fun verifieMainDeuxMultiplie() {
        //Teste si la méthode verifieMainCorrecte lève une exception si une main a trois cartes aux numéros identiques
        //Un jour ayant deux cartes identiques dans sa main ne peut plus jouer et n'a pas moyen de récupérer une troisième carte identique.
        assertThrows<MainInvalideException> { outils.verifieMainCorrecte(listOf(CarteBonusMultiplie(), CarteBonusMultiplie())) }
    }

    @Test fun verifieMainCinqBonusPlusDistincts() {
        val main = listOf(CarteBonusPlus(2), CarteBonusPlus(4), CarteBonusPlus(6), CarteBonusPlus(8), CarteBonusPlus(10))
        assertDoesNotThrow { outils.verifieMainCorrecte(main) }
    }

    @Test fun verifieMainDeuxFoisLeMemeBonusPlus() {
        assertThrows<MainInvalideException> { outils.verifieMainCorrecte(listOf(CarteBonusPlus(4), CarteBonusPlus(4))) }
    }

    @Test fun verifieMainUnStop() {
        assertDoesNotThrow { outils.verifieMainCorrecte(listOf(CarteStop())) }
    }

    @Test fun verifieMainDeuxStop() {
        assertThrows<MainInvalideException> { outils.verifieMainCorrecte(listOf(CarteStop(), CarteStop())) }
    }

    @Test fun verifieMainTroisSecondeChance() {
        assertDoesNotThrow { outils.verifieMainCorrecte(listOf(Carte2ndeChance(), Carte2ndeChance(), Carte2ndeChance())) }
    }

    @Test fun verifieMainQuatreSecondeChance() {
        assertThrows<MainInvalideException> {
            outils.verifieMainCorrecte(listOf(Carte2ndeChance(), Carte2ndeChance(), Carte2ndeChance(), Carte2ndeChance()))
        }
    }

    @Test fun verifieMainTroisTroisALaSuite() {
        assertDoesNotThrow { outils.verifieMainCorrecte(listOf(Carte3aLaSuite(), Carte3aLaSuite(), Carte3aLaSuite())) }
    }

    @Test fun verifieMainQuatreTroisALaSuite() {
        assertThrows<MainInvalideException> {
            outils.verifieMainCorrecte(listOf(Carte3aLaSuite(), Carte3aLaSuite(), Carte3aLaSuite(), Carte3aLaSuite()))
        }
    }

    // verifiePiocheInitiale : envoie l erreur PiocheInvalideException si la pioche n'est pas conforme

    @Test fun verifiePiocheComplete() {
        assertDoesNotThrow { outils.verifiePiocheInitiale(piocheComplete()) }
    }

    @Test fun verifiePiocheUneCarteEnMoins() {
        val incomplete = piocheComplete()
        incomplete.removeAt(0)
        assertThrows<PiocheInvalideException> { outils.verifiePiocheInitiale(incomplete) }
    }

    @Test fun verifiePiocheUneCarteEnTrop() {
        val trop = piocheComplete()
        trop.add(CarteNum(0))
        assertThrows<PiocheInvalideException> { outils.verifiePiocheInitiale(trop) }
    }

    @Test fun verifiePiocheMauvaiseRepartition() {
        val p = piocheComplete()
        var i = 0
        while (i < p.size) {
            if (p[i].estCarte2ndeChance()) { p.removeAt(i); break }
            i++
        }
        p.add(CarteBonusMultiplie())
        assertThrows<PiocheInvalideException> { outils.verifiePiocheInitiale(p) }
    }

    @Test fun verifiePiocheVide() {
        assertThrows<PiocheInvalideException> { outils.verifiePiocheInitiale(listOf()) }
    }

    // construit la pioche complete de 94 cartes
    private fun piocheComplete(): MutableList<Carte> {
        val cartes = mutableListOf<Carte>()
        cartes.add(CarteNum(0))
        for (valeur in 1..12) {
            for (n in 1..valeur) {
                cartes.add(CarteNum(valeur))
            }
        }
        cartes.add(CarteBonusMultiplie())
        cartes.add(CarteBonusPlus(2))
        cartes.add(CarteBonusPlus(4))
        cartes.add(CarteBonusPlus(6))
        cartes.add(CarteBonusPlus(8))
        cartes.add(CarteBonusPlus(10))
        cartes.add(Carte2ndeChance())
        cartes.add(Carte2ndeChance())
        cartes.add(Carte2ndeChance())
        cartes.add(Carte3aLaSuite())
        cartes.add(Carte3aLaSuite())
        cartes.add(Carte3aLaSuite())
        cartes.add(CarteStop())
        cartes.add(CarteStop())
        cartes.add(CarteStop())
        return cartes
    }
}