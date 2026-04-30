import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestUsageObjetsGeometriques {

// TODO : renommer le fichier .ktest en .kt

    @Test
    fun `dessiner un cercle`() {
        val objet: FormeGeo = Cercle(4, 2, 42)
        assertEquals("(X=4,Y=2,AV=42,AH=42)", objet.dessiner())
        assertEquals(131.94689, objet.perimetre(), 0.1)
        assertEquals(1385.44236, objet.surface(), 0.1)
    }

    @Test
    fun `dessiner un carre`() {
        val objet: FormeGeo = Carre(4, 2, 42)
        assertEquals("[X=4,Y=2,H=42,L=42]", objet.dessiner())
        assertEquals(42 * 4.0, objet.perimetre(), 0.1)
        assertEquals(42 * 42.0, objet.surface(), 0.1)
    }

    @Test
    fun `dessiner une ellipse`() {
        val objet: FormeGeo = Ellipse(4, 2, 420, 42)
        assertEquals("(X=4,Y=2,AV=420,AH=42)", objet.dessiner())
        assertEquals(937.658839, objet.perimetre(), 0.1)
        assertEquals(13854.4236, objet.surface(), 0.1)
    }

    @Test
    fun `dessiner un rectangle`() {
        val objet: FormeGeo = Rectangle(4, 2, 420, 42)
        assertEquals("[X=4,Y=2,H=420,L=42]", objet.dessiner())
        assertEquals(420 * 2.0 + 42 * 2.0, objet.perimetre(), 0.1)
        assertEquals(420 * 42.0, objet.surface(), 0.1)
    }

    @Test
    fun `dessiner un texte`() {
        val objet: IDessinable = Texte(4, 2, "La réponse universelle est 42")
        assertEquals("\"La réponse universelle est 42\":X=4,Y=2,P=Times new roman,S=10", objet.dessiner())
    }

    @Test
    fun `deplacer, puis dessiner un rectangle`() {
        val objet: ObjetDessinable = Rectangle(4, 2, 420, 42)
        objet.deplacer(Vecteur(Point(0, 0), Point(62, 4)))
        assertEquals("[X=66,Y=6,H=420,L=42]", objet.dessiner())
    }

    @Test
    fun `deplacer, puis dessiner un texte`() {
        val objet: ObjetDessinable = Texte(4, 2, "La réponse universelle est 42")
        objet.deplacer(Vecteur(Point(0, 0), Point(62, 4)))
        assertEquals("\"La réponse universelle est 42\":X=66,Y=6,P=Times new roman,S=10", objet.dessiner())
    }

    @Test
    fun `regrouper des objets graphiques`() {
        val groupe = Groupe(
            Ellipse(4, 2, 420, 42),
            Texte(4, 2, "La réponse universelle est 42")
        )
        assertEquals(2, groupe.nbObjetsRegroupes)
        assertTrue(groupe.regrouper(Rectangle(4, 2, 420, 42)))
        assertTrue(groupe.regrouper(Carre(0, 0, 10)))
        assertTrue(groupe.regrouper(Cercle(10, 15, 42)))
        assertEquals(5, groupe.nbObjetsRegroupes)
    }

    @Test
    fun `regrouper trop d'objets graphiques`() {
        val groupe = Groupe(
            Ellipse(4, 2, 420, 42),
            Texte(4, 2, "La réponse universelle est 42")
        )
        assertEquals(2, groupe.nbObjetsRegroupes)
        for (i in 3..15) // On remplit au maximum
            assertTrue(groupe.regrouper(Carre(i, i, i)))
        assertEquals(15, groupe.nbObjetsRegroupes)
        for (i in 3..15) // On fait pleins d'ajouts en trop
            assertFalse(groupe.regrouper(Cercle(i, i, i)))
        assertEquals(15, groupe.nbObjetsRegroupes)
    }

    @Test
    fun document_1feuille() {
        val doc = Document("monsuperfichier")
        assertAll(
            { assertNotNull(doc.donneFeuille(0)) },
            { assertEquals(1, doc.compteurFeuilles) },
            { assertNull(doc.donneFeuille(1)) },
            { assertNull(doc.donneFeuille(9)) },
            { assertNull(doc.donneFeuille(10)) },
            { assertNull(doc.donneFeuille(100)) },
        )
    }

    @Test
    fun document_6feuilles() {
        val doc = Document("monsuperfichier")
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        assertTrue(doc.nouvelleFeuille())
        assertAll(
            { assertNotNull(doc.donneFeuille(0)) },
            { assertEquals(6, doc.compteurFeuilles) },
            { assertNull(doc.donneFeuille(6)) },
            { assertNull(doc.donneFeuille(9)) },
            { assertNull(doc.donneFeuille(10)) },
            { assertNull(doc.donneFeuille(100)) },
        )
    }

    @Test
    fun document_10feuilles() {
        val doc = Document("monsuperfichier")
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        assertTrue(doc.nouvelleFeuille())
        assertAll(
            { assertNotNull(doc.donneFeuille(0)) },
            { assertEquals(10, doc.compteurFeuilles) },
            { assertNotNull(doc.donneFeuille(9)) },
            { assertNull(doc.donneFeuille(10)) },
            { assertNull(doc.donneFeuille(100)) },
        )
    }

    @Test
    fun document_11feuilles() {
        val doc = Document("monsuperfichier")
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        doc.nouvelleFeuille()
        assertFalse(doc.nouvelleFeuille())
    }


}