import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for A8 *****/

class TestUmlA8 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A8::class)
	}

	@Test
	fun `test0 - class A8 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A8 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'A8' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'A8' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("b", B8::class)))
	}

	@Test
	fun `test4 - 'A8' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A8' has an attribute 'b'`() {
		uml.attributeCheck("b", B8::class)
	}

	@Test
	fun `test6 - 'A8' has method(s)`() {
		uml.methodNumber(0)
	}


}
