import iut.info1.flip7.cartes.CarteNum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CarteNumTest {

    @Test
    fun `Test de verification de l'import de la bibliotheque`() {
        val carteValide = CarteNum(7)
        assertNotNull(carteValide, "La carte ne devrait pas être nulle")
        assertEquals(7, carteValide.valeur, "La propriété 'valeur' doit valoir 7")
        assertTrue(carteValide.estCarteNum(), "La méthode estCarteNum() devrait renvoyer true")
    }

    @Test
    fun `CarteNum valeur  0`() {
        val carte = CarteNum(0)

        assertEquals(0, carte.valeur, "La valeur de la carte devrait être 0")
    }

    @Test
    fun `CarteNum valeur 12`() {
        val carte = CarteNum(12)
        assertEquals(12,carte.valeur,"La valeur de la carte devrait être 12")
    }
    @Test
    fun `Carte valeur 7`() {
        val carte = CarteNum(7)
        assertEquals(7,carte.valeur,"La valeur de la carte devrait être 7")
    }
    @Test
    fun `Carte valeur -1 err`() {
        assertThrows(IllegalArgumentException::class.java) {
            CarteNum(-1)
        }
    }
    @Test
    fun `Carte valeur 13 err`() {
        assertThrows(IllegalArgumentException::class.java) {
            CarteNum(13)
        }
    }
    @Test
    fun `Tests des methodes de type de carte (estX)`() {
        val carte = CarteNum(7)

        assertTrue(carte.estCarteNum(), "Doit être une CarteNum")

        assertFalse(carte.estCarteBonusPlus(), "Ne devrait pas être une CarteBonusPlus")
        assertFalse(carte.estCarteBonusMultiplie(), "Ne devrait pas être une CarteBonusMultiplie")
        assertFalse(carte.estCarteStop(), "Ne devrait pas être une CarteStop")
        assertFalse(carte.estCarte2ndeChance(), "Ne devrait pas être une Carte2ndeChance")
        assertFalse(carte.estCarte3aLaSuite(), "Ne devrait pas être une Carte3aLaSuite")
    }

    @Test
    fun `Tests de la methode compareTo`() {
        val carte = CarteNum(7)

        val memeValeur = CarteNum(7)
        assertEquals(0, carte.compareTo(memeValeur), "La comparaison avec une même valeur doit retourner 0")

        val valeurInferieure = CarteNum(5)
        assertTrue(carte.compareTo(valeurInferieure) > 0, "7 comparé à 5 doit retourner une valeur > 0")

        val valeurSuperieure = CarteNum(10)
        assertTrue(carte.compareTo(valeurSuperieure) < 0, "7 comparé à 10 doit retourner une valeur < 0")
    }

    @Test
    fun `Tests de la methode equals`() {
        val carte = CarteNum(7)

        val memeValeur = CarteNum(7)
        assertTrue(carte.equals(memeValeur), "Deux cartes de même valeur doivent être égales")

        val valeurDifferente = CarteNum(4)
        assertFalse(carte.equals(valeurDifferente), "Deux cartes de valeurs différentes ne doivent pas être égales")

        assertFalse(carte.equals("Une chaîne de caractères"), "Une CarteNum ne peut pas être égale à un String")
    }
}
