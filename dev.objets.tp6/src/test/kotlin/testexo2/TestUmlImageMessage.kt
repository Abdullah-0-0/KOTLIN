package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for ImageMessage *****/

class TestUmlImageMessage {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(ImageMessage::class)
	}

	@Test
	fun `test0 - class ImageMessage is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class ImageMessage is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'ImageMessage' extends other classe(s)`() {
		uml.extend("Message")
	}

	@Test
	fun `test3 - 'ImageMessage' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("monTexte", String::class), Pair("monImg", Image::class), Pair("msgInit", Message::class)))
	}

	@Test
	fun `test4 - 'ImageMessage' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'ImageMessage' has an attribute 'img'`() {
		uml.attributeCheck("img", Image::class)
	}

	@Test
	fun `test6 - 'ImageMessage' has method(s)`() {
		uml.methodNumber(0)
	}


}
