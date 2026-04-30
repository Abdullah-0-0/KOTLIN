import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for B *****/

class TestUmlExo1B {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(B::class)
	}

	@Test
	fun `test0 - class B is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class B is open or not`() {
		uml.isOpen(true)
	}

	@Test
	fun `test2 - 'B' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'B' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("b", Int::class)))
	}

	@Test
	fun `test4 - 'B' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'B' has an attribute 'b'`() {
		uml.attributeCheck("b", Int::class, KVisibility.PROTECTED)
	}

	@Test
	fun `test6 - 'B' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test7 - 'B' has a method 'getBB'`() {
		uml.methodCheck("getBB", Int::class)
	}


}
