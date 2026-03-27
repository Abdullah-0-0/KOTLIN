import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for B6 *****/

class TestUmlB6 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(B6::class)
	}

	@Test
	fun `test0 - class B6 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class B6 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'B6' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'B6' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'B6' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test5 - 'B6' has method(s)`() {
		uml.methodNumber(0)
	}


}
