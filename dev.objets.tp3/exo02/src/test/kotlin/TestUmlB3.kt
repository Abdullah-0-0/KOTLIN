import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for B3 *****/

class TestUmlB3 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(B3::class)
	}

	@Test
	fun `test0 - class B3 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class B3 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'B3' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'B3' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'B3' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test5 - 'B3' has method(s)`() {
		uml.methodNumber(0)
	}


}
