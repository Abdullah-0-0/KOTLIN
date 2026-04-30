import iut.collections.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

/***** Usage Tests for Cellule *****/

class TestCelluleUsage {


	@Test
	fun `cellule creation with null suivant`() {
		val cellule = Cellule(42, null)
		assertEquals(42, cellule.valeur())
		assertNull(cellule.suivant())
	}

	@Test
	fun `cellule creation with 1 param`() {
		val cell = Cellule(1)
		assertEquals(1, cell.valeur())
		assertEquals(null, cell.suivant())
	}

	@Test
	fun `cellule avec suivant`() {
		val suivant = Cellule(0, null)
		val cellule = Cellule(42, suivant)
		assertEquals(42, cellule.valeur())
		assertEquals(suivant, cellule.suivant())
	}

	@Test
	fun `cellule chainage`() {
		val cell2 = Cellule(2, Cellule(1))
		assertAll(
			{ assertEquals(2, cell2.valeur()) },
			{ assertEquals(1, cell2.suivant()?.valeur()) },
			{ assertEquals(null, cell2.suivant()?.suivant()) }
		)
	}

	@Test
	fun `modifie suivant`() {
		val cellule = Cellule(42, null)
		val nouveau = Cellule(0, null)
		cellule.modifieSuivant(nouveau)
		assertEquals(nouveau, cellule.suivant())
	}

	@Test
	fun `modifie suivant avec chainage`() {
		val cell = Cellule(1)
		val cell2 = Cellule(2)
		cell.modifieSuivant(cell2)
		assertAll(
			{ assertEquals(1, cell.valeur()) },
			{ assertEquals(cell2, cell.suivant())},
			{ assertEquals(2, cell.suivant()?.valeur()) },
			{ assertEquals(null, cell.suivant()?.suivant()) },
		)
	}

	@Test
	fun `chaine de cellules`() {
		val c3 = Cellule(3, null)
		val c2 = Cellule(2, c3)
		val c1 = Cellule(1, c2)
		assertEquals(1, c1.valeur())
		assertEquals(2, c1.suivant()!!.valeur())
		assertEquals(3, c1.suivant()!!.suivant()!!.valeur())
		assertNull(c1.suivant()!!.suivant()!!.suivant())
	}

}
