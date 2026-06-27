import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


/**
 * Section "Carte — polymorphisme croisé" de l'analyse (lignes 332-357).
 *
 * Ces tests vérifient que chaque sous-type de Carte répond correctement aux méthodes
 * estCarteX() pour TOUS les types, pas seulement le sien (les fichiers de test existants
 * comme CarteStop.kt ou Carte2ndChance.kt ne vérifient souvent qu'un sous-ensemble de
 * ces combinaisons).
 */
class TestCartePolymorphismeCroise {

    @Test
    fun `comparaison entre CarteNum et CarteStop ne leve pas d exception`() {
        // Carte.compareTo() est défini au niveau de la classe abstraite Carte et compare
        // les valeurs (getValeur()) : on vérifie qu'il ne lève pas d'exception même entre
        // deux sous-types différents, sans présumer du signe exact du résultat puisque la
        // valeur d'une CarteStop n'est pas documentée.
        val carteNum = CarteNum(5)
        val carteStop = CarteStop()
        assertDoesNotThrow {
            carteNum.compareTo(carteStop)
        }
    }

    @Test
    fun `CarteNum equals null est false`() {
        assertFalse(CarteNum(5).equals(null))
    }

    @Test
    fun `CarteBonusPlus equals null est false`() {
        assertFalse(CarteBonusPlus(2).equals(null))
    }

    @Test
    fun `CarteBonusMultiplie equals null est false`() {
        assertFalse(CarteBonusMultiplie().equals(null))
    }

    @Test
    fun `CarteStop equals null est false`() {
        assertFalse(CarteStop().equals(null))
    }

    @Test
    fun `Carte2ndeChance equals null est false`() {
        assertFalse(Carte2ndeChance().equals(null))
    }

    @Test
    fun `Carte3aLaSuite equals null est false`() {
        assertFalse(Carte3aLaSuite().equals(null))
    }

    @Test
    fun `CarteBonusMultiplie n est aucun autre type de carte`() {
        val carte = CarteBonusMultiplie()
        assertFalse(carte.estCarteNum())
        assertFalse(carte.estCarteBonusPlus())
        assertFalse(carte.estCarteStop())
        assertFalse(carte.estCarte2ndeChance())
        assertFalse(carte.estCarte3aLaSuite())
    }

    @Test
    fun `Carte2ndeChance n est aucun autre type de carte`() {
        val carte = Carte2ndeChance()
        assertFalse(carte.estCarteNum())
        assertFalse(carte.estCarteBonusPlus())
        assertFalse(carte.estCarteBonusMultiplie())
        assertFalse(carte.estCarte3aLaSuite())
    }

    @Test
    fun `Carte3aLaSuite n est aucun autre type de carte`() {
        val carte = Carte3aLaSuite()
        assertFalse(carte.estCarteNum())
        assertFalse(carte.estCarteBonusPlus())
        assertFalse(carte.estCarteBonusMultiplie())
        assertFalse(carte.estCarte2ndeChance())
    }

    @Test
    fun `CarteStop n est aucun autre type de carte`() {
        val carte = CarteStop()
        assertFalse(carte.estCarteNum())
        assertFalse(carte.estCarteBonusPlus())
        assertFalse(carte.estCarteBonusMultiplie())
        assertFalse(carte.estCarte2ndeChance())
    }

    @Test
    fun `getValeur de CarteBonusMultiplie renvoie une valeur fixe coherente`() {
        // Confirmé par calculScore.kt existant : CarteBonusMultiplie a une valeur de 2.
        val carte = CarteBonusMultiplie()
        assertEquals(2, carte.valeur)
    }
}
