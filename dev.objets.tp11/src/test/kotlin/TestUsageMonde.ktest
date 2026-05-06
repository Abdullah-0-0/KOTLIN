import exo2.Monde
import exo2.Pays
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.FileNotFoundException

class TestUsageMonde {

    lateinit var md : Monde
    var allemagne = Pays("Allemagne", "Berlin", "Europe", 81413145, 357021.0)
    var france = Pays("France", "Paris", "Europe", 66808385, 640597.0)

    @BeforeEach
    fun setUp() {
        md = Monde()
    }

    @Test
    fun mondeVide() {
        assertEquals(md.taille(), 0)
    }

    @Test
    fun monde1Pays() {
        assertTrue(md.ajouter(france))
        assertEquals(md.taille(), 1)
    }

    @Test
    fun monde1Pays2fois() {
        assertTrue(md.ajouter(france))
        assertEquals(md.taille(), 1)
        assertFalse(md.ajouter(france))
        assertEquals(md.taille(), 1)
    }

    @Test
    fun monde2Pays() {
        assertTrue(md.ajouter(france))
        assertTrue(md.ajouter(allemagne))
        assertEquals(md.taille(), 2)
    }

    @Test
    fun mondeRempliPays() {
        assertDoesNotThrow { md.remplir("data/pays.csv") }
        assertEquals(196, md.taille())
    }

    @Test
    fun mondeRempliVide() {
        assertDoesNotThrow { md.remplir("data/vide.csv") }
        assertEquals(0, md.taille())
    }

    @Test
    fun mondeRempliFichierNonPresent() {
        assertThrows<FileNotFoundException> { md.remplir("data/fichierNonPresent.csv") }
    }

    @Test
    fun mondeRempliFichierMauvaisFormat() {
        assertThrows<NumberFormatException> { md.remplir("data/mauvaisFormat.csv") }
    }

}
