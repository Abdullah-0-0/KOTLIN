import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.exceptions.CarteInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Essai {

    @Test
    fun `Test de verification de l'import de la bibliotheque`() {
        // 1. On teste la création d'une carte valide (Ex: 7)
        val carteValide = CarteNum(7)
        assertNotNull(carteValide, "La carte ne devrait pas être nulle")
        assertEquals(7, carteValide.valeur, "La propriété 'valeur' doit valoir 7")
        assertTrue(carteValide.estCarteNum(), "La méthode estCarteNum() devrait renvoyer true")
    }
}
