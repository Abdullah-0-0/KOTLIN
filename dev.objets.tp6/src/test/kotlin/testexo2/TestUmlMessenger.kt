package testexo2

import exo2.*
import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Messenger *****/

class TestUmlMessenger {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Messenger::class)
	}

	@Test
	fun `test0 - class Messenger is abstract or not`() {
		uml.isAbstract(true)
	}

	@Test
	fun `test1 - class Messenger is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Messenger' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Messenger' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("utilisateur", Personne::class)))
	}

	@Test
	fun `test4 - 'Messenger' has attribute(s)`() {
		uml.attributeNumber(2)
	}

	@Test
	fun `test5 - 'Messenger' has an attribute 'compte'`() {
		uml.attributeCheck("compte", Personne::class)
	}

	@Test
	fun `test6 - 'Messenger' has an attribute 'messages'`() {
		uml.attributeCheck("messages", NutArray::class)
	}

	@Test
	fun `test7 - 'Messenger' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test8 - 'Messenger' has a method 'envoyer'`() {
		uml.methodCheck("envoyer", methParamAndTypes=arrayOf(Pair("message", Message::class)))
	}


}
