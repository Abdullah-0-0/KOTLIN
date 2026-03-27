package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Whatsapp *****/

class TestUmlWhatsapp {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Whatsapp::class)
	}

	@Test
	fun `test0 - class Whatsapp is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Whatsapp is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Whatsapp' extends other classe(s)`() {
		uml.extend("Messenger")
	}

	@Test
	fun `test3 - 'Whatsapp' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("utilisateur", Personne::class)))
	}

	@Test
	fun `test4 - 'Whatsapp' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test5 - 'Whatsapp' has method(s)`() {
		uml.methodNumber(0)
	}


}
