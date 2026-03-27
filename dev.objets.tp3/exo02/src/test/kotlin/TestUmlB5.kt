import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for B5 *****/

class TestUmlB5 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(B5::class)
	}

	@Test
	fun `test0 - class B5 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class B5 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'B5' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'B5' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'B5' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test5 - 'B5' has method(s)`() {
		uml.methodNumber(0)
	}


}
