import info.but1.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for B7 *****/

class TestUmlB7 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(B7::class)
	}

	@Test
	fun `test0 - class B7 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class B7 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'B7' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'B7' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("a", A7::class)))
	}

	@Test
	fun `test4 - 'B7' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'B7' has an attribute 'a'`() {
		uml.attributeCheck("a", A7::class)
	}

	@Test
	fun `test6 - 'B7' has method(s)`() {
		uml.methodNumber(0)
	}


}
