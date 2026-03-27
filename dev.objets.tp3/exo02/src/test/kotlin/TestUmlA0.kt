import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for A0 *****/

class TestUmlA0 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A0::class)
	}

	@Test
	fun `test0 - class A0 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A0 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'A0' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'A0' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("c", C::class)))
	}

	@Test
	fun `test4 - 'A0' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A0' has an attribute 'c'`() {
		uml.attributeCheck("c", C::class)
	}

	@Test
	fun `test6 - 'A0' has method(s)`() {
		uml.methodNumber(0)
	}


}
