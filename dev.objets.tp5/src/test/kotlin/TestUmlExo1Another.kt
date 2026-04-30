import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for Another *****/

class TestUmlExo1Another {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Another::class)
	}

	@Test
	fun `test0 - class Another is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Another is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Another' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Another' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'Another' has method(s)`() {
		uml.methodNumber(0)
	}


}
