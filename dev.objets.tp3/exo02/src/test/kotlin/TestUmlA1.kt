import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for A1 *****/

class TestUmlA1 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A1::class)
	}

	@Test
	fun `test0 - class A1 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A1 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'A1' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'A1' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("c", C::class)))
	}

	@Test
	fun `test4 - 'A1' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A1' has an attribute 'c'`() {
		uml.attributeCheck("c", C::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test6 - 'A1' has method(s)`() {
		uml.methodNumber(0)
	}


}
