package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for Image *****/

class TestUmlImage {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Image::class)
	}

	@Test
	fun `test0 - class Image is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Image is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Image' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Image' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'Image' has method(s)`() {
		uml.methodNumber(0)
	}


}
