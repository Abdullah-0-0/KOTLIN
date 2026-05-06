import exo2.Monde
import exo2.Pays
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestUsageMondeRempliPays {

    var md = Monde()
    var france = Pays("France", "Paris", "Europe", 66808385, 640597.0)


    @BeforeEach
    fun setUp() {
            md.remplir("data/pays.csv")
    }

    @Test
    fun getPays() {
        val france2 = md.getPays("France")
        assertEquals(france, france2)
        assertNotSame(france, france2)
        assertNull(md.getPays("Nexistepas"))
    }

    @Test
    fun getPays2() {
        val france2 = md.getPays2("France")
        assertEquals(france, france2)
        assertNotSame(france, france2)
        assertNull(md.getPays2("Nexistepas"))
    }

    @Test
    fun plusPeupleVatican() {
        val vatican = md.getPays("Vatican")
        val pluspeuple = md.plusPeuple(vatican!!)
        val pluspeupleAttendu = md.monde()
        assertTrue(pluspeupleAttendu.containsAll(pluspeuple))
        assertTrue(pluspeuple.containsAll(pluspeupleAttendu))
    }

    @Test
    fun plusPeuple5() {
        val chine = md.getPays("Chine")
        val inde = md.getPays("Inde")
        val eu = md.getPays("Etats-Unis")
        val indonesie = md.getPays("Indonésie")
        val bresil = md.getPays("Brésil")
        val pluspeupleAttendu: MutableList<Pays?> = ArrayList()
        pluspeupleAttendu.add(chine)
        pluspeupleAttendu.add(inde)
        pluspeupleAttendu.add(eu)
        pluspeupleAttendu.add(indonesie)
        pluspeupleAttendu.add(bresil)
        val pluspeuple: List<Pays?> = md.plusPeuple(bresil!!)
        assertTrue(pluspeupleAttendu.containsAll(pluspeuple))
        assertTrue(pluspeuple.containsAll(pluspeupleAttendu))
    }

    @Test
    fun mondeTrie() {
        var mt: MutableList<Pays> = md.mondeTrie()
        assertEquals(md.taille(), mt.size)
        var pp: Pays = mt.removeAt(0)
        for (p in mt) {
            assertTrue(pp.compareTo(p) < 0)
            pp = p
        }
    }

    @Test
    fun mondeTrieTab() {
        val mt = md.mondeTrieTab()
        assertEquals(md.taille(), mt.size)
        var pp = Pays("FAKE", "", "", 0, 0.0)
        for (p in mt) {
            assertTrue(pp.compareTo(p) < 0)
            pp = p
        }
    }
}
