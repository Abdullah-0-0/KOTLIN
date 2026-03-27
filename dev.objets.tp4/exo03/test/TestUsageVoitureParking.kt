import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

class TestUsageVoitureParking {

    val batman = Personne(nom = "Wayne", prenom = "Bruce")
    val robin = Personne(nom = "Dick", prenom = "Grayson")

    lateinit var batmobile : Voiture
    lateinit var batmobile66 : Voiture
    lateinit var batmobile89 : Voiture
    lateinit var batcave : Parking

    @BeforeEach
    fun initTest() {
        batmobile = Voiture(mod = "Batmobile Tumbler", coul = "noire")
        batmobile.acheter(acheteur = batman)
        batmobile66 = Voiture(mod = "Batmobile 1966", coul = "noire")
        batmobile66.acheter(batman)
        batmobile89 = Voiture("Burton's Batmobile", "noire")
        batmobile89.acheter(robin)
        batcave = Parking(placesMax = 2)
    }
    
    @Test
    fun initparking() {
        assertAll(
            { assertFalse(batmobile.estGaree()) },
            { assertFalse(batmobile66.estGaree()) },
            { assertFalse(batmobile89.estGaree()) },
        )
        assertAll(
            { assertEquals(2, batcave.nombreDePlacesLibres()) },
            { assertTrue(batcave.placeLibre(numeroPlace = 0)) },
            { assertTrue(batcave.placeLibre(numeroPlace = 1)) },
        )
    }

    @Test
    fun garerBatmobile() {
        assertTrue(batmobile.garerDans(batcave))
        assertAll(
            { assertTrue(batmobile.estGaree()) },
            { assertFalse(batmobile66.estGaree()) },
            { assertFalse(batmobile89.estGaree()) },
        )
        assertAll(
            { assertEquals(1, batcave.nombreDePlacesLibres()) },
            { assertFalse(batcave.placeLibre(numeroPlace = 0)) },
            { assertTrue(batcave.placeLibre(numeroPlace = 1)) },
        )
    }

    @Test
    fun garer2Batmobiles() {
        batmobile.garerDans(batcave)
        assertTrue(batmobile89.garerDans(batcave))
        assertAll(
            { assertTrue(batmobile.estGaree()) },
            { assertFalse(batmobile66.estGaree()) },
            { assertTrue(batmobile89.estGaree()) },
        )
        assertAll(
            { assertEquals(0, batcave.nombreDePlacesLibres()) },
            { assertFalse(batcave.placeLibre(numeroPlace = 0)) },
            { assertFalse(batcave.placeLibre(numeroPlace = 1)) },
        )
    }

    @Test
    fun garer2Fois1Batmobile() {
        batmobile.garerDans(batcave)
        assertFalse(batmobile.garerDans(batcave))
        assertAll(
            { assertTrue(batmobile.estGaree()) },
            { assertFalse(batmobile66.estGaree()) },
            { assertFalse(batmobile89.estGaree()) },
        )
        assertAll(
            { assertEquals(1, batcave.nombreDePlacesLibres()) },
            { assertFalse(batcave.placeLibre(numeroPlace = 0)) },
            { assertTrue(batcave.placeLibre(numeroPlace = 1)) },
        )
    }

    @Test
    fun garer3batmobiles() {
        batmobile.garerDans(batcave)
        batmobile89.garerDans(batcave)
        assertFalse(batmobile66.garerDans(batcave))
        assertAll(
            { assertTrue(batmobile.estGaree()) },
            { assertFalse(batmobile66.estGaree()) },
            { assertTrue(batmobile89.estGaree()) },
        )
        assertAll(
            { assertEquals(0, batcave.nombreDePlacesLibres()) },
            { assertFalse(batcave.placeLibre(numeroPlace = 0)) },
            { assertFalse(batcave.placeLibre(numeroPlace = 1)) },
        )
    }

    @Test
    fun garer2batmobiles_sortir1batmobilePasGaree() {
        batmobile.garerDans(batcave)
        batmobile89.garerDans(batcave)
        assertFalse(batmobile66.quitterStationnement())
        assertAll(
            { assertTrue(batmobile.estGaree()) },
            { assertFalse(batmobile66.estGaree()) },
            { assertTrue(batmobile89.estGaree()) },
        )
        assertAll(
            { assertEquals(0, batcave.nombreDePlacesLibres()) },
            { assertFalse(batcave.placeLibre(numeroPlace = 0)) },
            { assertFalse(batcave.placeLibre(numeroPlace = 1)) },
        )
    }

    @Test
    fun garer2batmobiles_sortir1batmobile() {
        batmobile.garerDans(batcave)
        batmobile89.garerDans(batcave)
        assertTrue(batmobile.quitterStationnement())
        assertAll(
            { assertFalse(batmobile.estGaree()) },
            { assertFalse(batmobile66.estGaree()) },
            { assertTrue(batmobile89.estGaree()) },
        )
        assertAll(
            { assertEquals(1, batcave.nombreDePlacesLibres()) },
            { assertTrue(batcave.placeLibre(numeroPlace = 0)) },
            { assertFalse(batcave.placeLibre(numeroPlace = 1)) },
        )
    }

    @Test
    fun garer2batmobiles_sortirbatmobile89() {
        batmobile.garerDans(batcave)
        batmobile89.garerDans(batcave)
        assertTrue(batmobile89.quitterStationnement())
        assertAll(
            { assertTrue(batmobile.estGaree()) },
            { assertFalse(batmobile66.estGaree()) },
            { assertFalse(batmobile89.estGaree()) },
        )
        assertAll(
            { assertEquals(1, batcave.nombreDePlacesLibres()) },
            { assertFalse(batcave.placeLibre(numeroPlace = 0)) },
            { assertTrue(batcave.placeLibre(numeroPlace = 1)) },
        )
    }

        @Test
    fun garer2batmobiles_toutsortir() {
        batmobile.garerDans(batcave)
        batmobile89.garerDans(batcave)
        assertTrue(batmobile.quitterStationnement())
        assertTrue(batmobile89.quitterStationnement())
        assertAll(
            { assertFalse(batmobile.estGaree()) },
            { assertFalse(batmobile66.estGaree()) },
            { assertFalse(batmobile89.estGaree()) },
        )
        assertAll(
            { assertEquals(2, batcave.nombreDePlacesLibres()) },
            { assertTrue(batcave.placeLibre(numeroPlace = 0)) },
            { assertTrue(batcave.placeLibre(numeroPlace = 1)) },
        )
    }
}