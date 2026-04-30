import iut.collections.*
import org.junit.jupiter.api.*
import kotlin.random.Random
import org.junit.jupiter.api.Assertions.*

/***** Usage Tests for PileChainee *****/

class TestPileChaineeUsage {


	@Test
	fun `pile vide`() {
		val pile = PileChainee()
		assertTrue(pile.estVide())
	}

	@Test
	fun `pile vide - taille`() {
		val pile = PileChainee()
		assertEquals(0, pile.taille())
	}

	@Test
	fun `pile vide - consulter`() {
		val pile = PileChainee()
		assertThrows<NoSuchElementException> { pile.consulter() }
	}

	@Test
	fun `pile vide depiler`() {
		val pile = PileChainee()
		assertThrows<NoSuchElementException> { pile.depiler() }
	}

	@Test
	fun `empiler Beaucoup beaucoup`() {
		val pile = PileChainee()
		repeat(10_000_000) {
			pile.empiler(Random.nextInt(-100,101))
		}
		repeat(10_000_000) {
				pile.depiler()
		}
		assertAll(
			{ assertTrue(pile.estVide()) },
			{ assertEquals(0, pile.taille()) },
		)
		assertThrows<NoSuchElementException> { pile.depiler()}
	}

	@Test
	fun `pile 1 elt`() {
		val pile = PileChainee()
		pile.empiler(42)
		assertFalse(pile.estVide())
	}

	@Test
	fun `pile 1 elt - taille`() {
		val pile = PileChainee()
		pile.empiler(42)
		assertEquals(1, pile.taille())
	}

	@Test
	fun `pile 1 elt - consulter`() {
		val pile = PileChainee()
		pile.empiler(42)
		assertDoesNotThrow { pile.consulter() }
	}

	@Test
	fun `pile 1 elt - consulter - res`() {
		val pile = PileChainee()
		pile.empiler(42)
		assertEquals(42, pile.consulter())
	}

	@Test
	fun `pile 1 elt - depiler`() {
		val pile = PileChainee()
		pile.empiler(42)
		assertDoesNotThrow { pile.depiler() }
	}

	@Test
	fun `pile 1 elt - depiler -res`() {
		val pile = PileChainee()
		pile.empiler(42)
		assertEquals(42, pile.depiler())
	}

	@Test
	fun `pile 1 elt - depiler - apres`() {
		val pile = PileChainee()
		pile.empiler(42)
		assertDoesNotThrow {pile.depiler()}
		assertThrows<NoSuchElementException> { pile.depiler() }
	}

	@Test
	fun `pile 1 elt - après consulter`() {
		val pile = PileChainee()
		pile.empiler(42)
		val res = pile.consulter()
		assertEquals(42, res)
		assertAll(
			{ assertFalse(pile.estVide()) },
			{ assertEquals(1, pile.taille()) },
		)
	}

	@Test
	fun `pile 1 elt - après depiler`() {
		val pile = PileChainee()
		pile.empiler(42)
		val res = pile.depiler()
		assertEquals(42, res)
		assertAll(
			{ assertTrue(pile.estVide()) },
			{ assertEquals(0, pile.taille()) },
		)
	}

	@Test
	fun `pile plusieurs elts`() {
		val pile = PileChainee()
		pile.empiler(42)
		pile.empiler(0)
		pile.empiler(-2)
		pile.empiler(4)
		pile.empiler(-42)
		pile.empiler(-4)
		pile.empiler(99)
		assertFalse(pile.estVide())
	}

	@Test
	fun `pile plusieurs elts - taille`() {
		val pile = PileChainee()
		pile.empiler(42)
		pile.empiler(0)
		pile.empiler(-2)
		pile.empiler(4)
		pile.empiler(-42)
		pile.empiler(-4)
		pile.empiler(99)
		assertEquals(7, pile.taille())
	}

	@Test
	fun `pile plusieurs elts - consulter res`() {
		val pile = PileChainee()
		pile.empiler(42)
		pile.empiler(0)
		pile.empiler(-2)
		pile.empiler(4)
		pile.empiler(-42)
		pile.empiler(-4)
		pile.empiler(99)
		val res = pile.consulter()
		assertEquals(99, res)
	}

	@Test
	fun `pile plusieurs elts - consulter apres`() {
		val pile = PileChainee()
		pile.empiler(42)
		pile.empiler(0)
		pile.empiler(-2)
		pile.empiler(4)
		pile.empiler(-42)
		pile.empiler(-4)
		pile.empiler(99)
		val res = pile.consulter()
		assertFalse(pile.estVide())
	}

	@Test
	fun `pile plusieurs elts - consulter apres taille`() {
		val pile = PileChainee()
		pile.empiler(42)
		pile.empiler(0)
		pile.empiler(-2)
		pile.empiler(4)
		pile.empiler(-42)
		pile.empiler(-4)
		pile.empiler(99)
		val res = pile.consulter()
		assertEquals(7, pile.taille())
	}

	@Test
	fun `pile plusieurs elts - depiler`() {
		val pile = PileChainee()
		pile.empiler(42)
		pile.empiler(0)
		pile.empiler(-2)
		pile.empiler(4)
		pile.empiler(-42)
		pile.empiler(-4)
		pile.empiler(99)
		pile.depiler()
		pile.depiler()
		pile.depiler()
		val res = pile.depiler()
		assertEquals(4, res)
	}

	@Test
	fun `pile plusieurs elts - depiler apres`() {
		val pile = PileChainee()
		pile.empiler(42)
		pile.empiler(0)
		pile.empiler(-2)
		pile.empiler(4)
		pile.empiler(-42)
		pile.empiler(-4)
		pile.empiler(99)
		pile.depiler()
		pile.depiler()
		pile.depiler()
		pile.depiler()
		assertFalse(pile.estVide())
	}

	@Test
	fun `pile plusieurs elts - depiler apres taille`() {
		val pile = PileChainee()
		pile.empiler(42)
		pile.empiler(0)
		pile.empiler(-2)
		pile.empiler(4)
		pile.empiler(-42)
		pile.empiler(-4)
		pile.empiler(99)
		pile.depiler()
		pile.depiler()
		pile.depiler()
		assertEquals(4, pile.taille())
	}

	@Test
	fun `pile plusieurs elts - depiler trop`() {
		val pile = PileChainee()
		pile.empiler(42)
		pile.empiler(0)
		pile.empiler(-2)
		pile.empiler(4)
		pile.empiler(-42)
		pile.empiler(-4)
		pile.empiler(99)

		assertDoesNotThrow { pile.depiler()}
		assertDoesNotThrow { pile.depiler()}
		assertDoesNotThrow { pile.depiler()}
		assertDoesNotThrow { pile.depiler()}
		assertDoesNotThrow { pile.depiler()}
		assertDoesNotThrow { pile.depiler()}
		assertDoesNotThrow { pile.depiler()}
		assertThrows<NoSuchElementException> { pile.depiler()}
	}

}
