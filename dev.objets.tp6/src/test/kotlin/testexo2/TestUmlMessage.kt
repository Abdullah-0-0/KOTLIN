package testexo2

import exo2.*
import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Message *****/

class TestUmlMessage {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Message::class)
	}

	@Test
	fun `test0 - class Message is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Message is open or not`() {
		uml.isOpen(true)
	}

	@Test
	fun `test2 - 'Message' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Message' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("texte", String::class), Pair("msgInit", Message::class)))
	}

	@Test
	fun `test4 - 'Message' has attribute(s)`() {
		uml.attributeNumber(4)
	}

	@Test
	fun `test5 - 'Message' has an attribute 'texte'`() {
		uml.attributeCheck("texte", String::class, KVisibility.PROTECTED)
	}

	@Test
	fun `test6 - 'Message' has an attribute 'nbDestinataires'`() {
		uml.attributeCheck("nbDestinataires", Int::class)
	}

	@Test
	fun `test7 - 'Message' has an attribute 'destinataires'`() {
		uml.attributeCheck("destinataires", NutArray::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test8 - 'Message' has an attribute 'messageInitial'`() {
		uml.attributeCheck("messageInitial", Message::class, nullable=true)
	}

	@Test
	fun `test9 - 'Message' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test10 - 'Message' has a method 'ajouterDestinataire'`() {
		uml.methodCheck("ajouterDestinataire", methParamAndTypes=arrayOf(Pair("destinataire", Personne::class)))
	}


}
