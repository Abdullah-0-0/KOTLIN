import iut.collections.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

/***** Usage Tests for ListeChainee *****/

class TestListeChainneeUsage {


	@Test
	fun `liste vide`() {
		val liste: iListe = ListeChainee()
		assertAll(
			{ assertTrue(liste.estVide()) },
			{ assertEquals(0, liste.taille()) },
		)
	}

	@ParameterizedTest
	@ValueSource(ints = [-100, 0, -1, 7, 100])
	fun `consulter liste vide position exception`(position: Int) {
		val liste: iListe = ListeChainee()
		assertThrows<IndexOutOfBoundsException> {
			liste.consulter(position)
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [-99, 2, 1, 3, 5, 6, 7, 100])
	fun `index dans liste vide exception`(valeur: Int) {
		val liste: iListe = ListeChainee()
		assertThrows<NoSuchElementException> {
			liste.index(valeur)
		}
	}

	@Test
	fun `liste remplie ok`() {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertAll(
			{ assertFalse(liste.estVide()) },
			{ assertEquals(7, liste.taille()) },
		)
	}

	@Test
	fun `consulter debut liste`() {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		val res = liste.consulter()
		assertEquals(99, res)
	}

	@ParameterizedTest
	@CsvSource("0, 99", "1, -4", "2, -42", "3, 4", "4, -2", "5, 0", "6, 42")
	fun `consulter liste position`(position: Int, valeur: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertDoesNotThrow {
			val res = liste.consulter(position)
			assertEquals(valeur, res)
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [-100, -1, 7, 100])
	fun `consulter liste position exception`(position: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertThrows<IndexOutOfBoundsException> {
			liste.consulter(position)
		}
	}

	@ParameterizedTest
	@CsvSource("0, 99", "1, -4", "2, -42", "3, 4", "4, -2", "5, 0", "6, 42")
	fun `index dans liste`(position: Int, valeur: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertDoesNotThrow {
			val pos = liste.index(valeur)
			assertEquals(position, pos)
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [-99, 2, 1, 3, 5, 6, 7, 100])
	fun `index dans liste exception`(valeurInconnue: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertThrows<NoSuchElementException> {
			liste.index(valeurInconnue)
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [-100, -1, 8, 100])
	fun `inserer dans liste exception`(positionFausse: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertThrows<IndexOutOfBoundsException> {
			liste.inserer(positionFausse, 666)
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7])
	fun `inserer dans liste ok`(position: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertDoesNotThrow {
			liste.inserer(position, 666)
			assertAll(
				{ assertFalse(liste.estVide()) },
				{ assertEquals(8, liste.taille()) },
			)
		}
	}

	@ParameterizedTest
	@CsvSource("0, 99", "1, -4", "2, -42", "3, 4", "4, -2", "5, 0", "6, 42")
	fun `inserer dans liste, puis consulter`(position: Int, valeurPrecedente: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertDoesNotThrow {
			liste.inserer(position, 666)
			assertAll(
				{ assertEquals(666, liste.consulter(position)) },
				{ assertEquals(valeurPrecedente, liste.consulter(position+1)) }
			)
		}
	}

	@Test
	fun `inserer dans liste fin ok`() {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertDoesNotThrow {
			liste.inserer(7, 666)
			assertEquals(666, liste.consulter(7))
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [-100, -1, 7, 100])
	fun `supprimer dans liste exception`(positionFausse: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertThrows<IndexOutOfBoundsException> {
			liste.supprimer(positionFausse)
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [0, 1, 2, 3, 4, 5, 6])
	fun `supprimer dans liste ok`(position: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertDoesNotThrow {
			liste.supprimer(position)
			assertAll(
				{ assertFalse(liste.estVide()) },
				{ assertEquals(6, liste.taille()) },
			)
		}
	}

	@ParameterizedTest
	@CsvSource("0, -4", "1, -42", "2, 4", "3, -2", "4, 0", "5, 42")
	fun `supprimer dans liste, puis consulter`(position: Int, valeurApres: Int) {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertDoesNotThrow {
			liste.supprimer(position)
			assertAll(
				{ assertEquals(valeurApres, liste.consulter(position)) }
			)
		}
	}

	@Test
	fun `supprimer dernier dans liste`() {
		val liste: iListe = ListeChainee()
		liste.empiler(42)
		liste.empiler(0)
		liste.empiler(-2)
		liste.empiler(4)
		liste.empiler(-42)
		liste.empiler(-4)
		liste.empiler(99)
		assertDoesNotThrow {
			liste.supprimer(6)
		}
	}

	@Test
	fun `liste = file vide - consulterTete`() {
		val file: iFile = ListeChainee()
		assertThrows<NoSuchElementException> { file.consulterEnTete() }
	}

	@Test
	fun `liste = file vide - supprimerTete`() {
		val file: iFile = ListeChainee()
		assertThrows<NoSuchElementException> { file.supprimerEnTete() }
	}

	@Test
	fun `liste = file vide - consulterQueue`() {
		val file: iFile = ListeChainee()
		assertThrows<NoSuchElementException> { file.consulterEnQueue() }
	}

	@Test
	fun `liste = file vide - supprimerQueue`() {
		val file: iFile = ListeChainee()
		assertThrows<NoSuchElementException> { file.supprimerEnQueue() }
	}

	@Test
	fun `liste = file vide - taille`() {
		val file: iFile = ListeChainee()
		assert(file.taille() == 0)
	}

	@Test
	fun `liste = file vide - estVide`() {
		val file: iFile = ListeChainee()
		assertTrue(file.estVide())
	}

	@Test
	fun `liste = file vide - insererEnTete`() {
		val file: iFile = ListeChainee()
		file.insererEnTete(666)
		assertTrue(file.taille() == 1)
		assertEquals(666, file.consulterEnTete())
		assertEquals(666, file.consulterEnQueue())
	}

	@Test
	fun `liste = file vide - insererEnQueue`() {
		val file: iFile = ListeChainee()
		file.insererEnTete(666)
		assert(file.taille() == 1)
		assertEquals(666, file.consulterEnTete())
		assertEquals(666, file.consulterEnQueue())
	}

	@Test
	fun `consulter debut file`() {
		val file: iFile = ListeChainee()
		file.empiler(42)
		file.empiler(0)
		file.empiler(-2)
		file.empiler(4)
		file.empiler(-42)
		file.empiler(-4)
		file.empiler(99)
		val res = file.consulterEnTete()
		assertEquals(99, res)
	}

	@Test
	fun `consulter fin file`() {
		val file: iFile = ListeChainee()
		file.empiler(42)
		file.empiler(0)
		file.empiler(-2)
		file.empiler(4)
		file.empiler(-42)
		file.empiler(-4)
		file.empiler(99)
		val res = file.consulterEnQueue()
		assertEquals(42, res)
	}

	@Test
	fun `supprimer debut file`() {
		val file: iFile = ListeChainee()
		file.empiler(42)
		file.empiler(0)
		file.empiler(-2)
		file.empiler(4)
		file.empiler(-42)
		file.empiler(-4)
		file.empiler(99)
		file.supprimerEnTete()
		val res = file.consulterEnTete()
		assertEquals(-4, res)
	}

	@Test
	fun `supprimer fin file`() {
		val file: iFile = ListeChainee()
		file.empiler(42)
		file.empiler(0)
		file.empiler(-2)
		file.empiler(4)
		file.empiler(-42)
		file.empiler(-4)
		file.empiler(99)
		file.supprimerEnQueue()
		val res = file.consulterEnQueue()
		assertEquals(0, res)
	}

	@Test
	fun `inserer debut file`() {
		val file: iFile = ListeChainee()
		file.empiler(42)
		file.empiler(0)
		file.empiler(-2)
		file.empiler(4)
		file.empiler(-42)
		file.empiler(-4)
		file.empiler(99)
		file.insererEnTete(666)
		val res = file.consulterEnTete()
		assertEquals(666, res)
	}

	@Test
	fun `inserer fin file`() {
		val file: iFile = ListeChainee()
		file.empiler(42)
		file.empiler(0)
		file.empiler(-2)
		file.empiler(4)
		file.empiler(-42)
		file.empiler(-4)
		file.empiler(99)
		file.insererEnQueue(666)
		val res = file.consulterEnQueue()
		assertEquals(666, res)
	}

}
