package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Signal *****/

class TestUmlSignal {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Signal::class)
	}

	@Test
	fun `test0 - class Signal is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Signal is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Signal' extends other classe(s)`() {
		uml.extend("Messenger")
	}

	@Test
	fun `test3 - 'Signal' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("utilisateur", Personne::class)))
	}

	@Test
	fun `test4 - 'Signal' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'Signal' has an attribute 'clefChiffrement'`() {
		uml.attributeCheck("clefChiffrement", String::class)
	}

	@Test
	fun `test6 - 'Signal' has method(s)`() {
		uml.methodNumber(0)
	}


}
