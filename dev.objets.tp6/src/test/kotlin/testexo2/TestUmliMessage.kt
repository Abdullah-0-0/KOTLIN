package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for iMessage *****/

class TestUmliMessage {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(iMessage::class)
	}

	@Test
	fun `test0 - class iMessage is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class iMessage is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'iMessage' extends other classe(s)`() {
		uml.extend("Messenger")
	}

	@Test
	fun `test3 - 'iMessage' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("utilisateur", Personne::class)))
	}

	@Test
	fun `test4 - 'iMessage' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test5 - 'iMessage' has method(s)`() {
		uml.methodNumber(0)
	}


}
