import info.but1.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for A3 *****/

class TestUmlA3 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A3::class)
	}

	@Test
	fun `test0 - class A3 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A3 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'A3' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'A3' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("b", B3::class)))
	}

	@Test
	fun `test4 - 'A3' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A3' has an attribute 'b'`() {
		uml.attributeCheck("b", B3::class)
	}

	@Test
	fun `test6 - 'A3' has method(s)`() {
		uml.methodNumber(0)
	}


}
