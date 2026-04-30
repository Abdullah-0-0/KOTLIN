import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for A *****/

class TestUmlExo1A {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A::class)
	}

	@Test
	fun `test0 - class A is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A is open or not`() {
		uml.isOpen(true)
	}

	@Test
	fun `test2 - 'A' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'A' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("o", Another::class)))
	}

	@Test
	fun `test4 - 'A' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A' has an attribute 'other'`() {
		uml.attributeCheck("other", Another::class)
	}

	@Test
	fun `test6 - 'A' has method(s)`() {
		uml.methodNumber(0)
	}


}
