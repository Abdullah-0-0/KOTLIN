import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for B1 *****/

class TestUmlExo1B1 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(B1::class)
	}

	@Test
	fun `test0 - class B1 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class B1 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'B1' extends other classe(s)`() {
		uml.extend("B")
	}

	@Test
	fun `test3 - 'B1' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("b", Int::class)))
	}

	@Test
	fun `test4 - 'B1' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test5 - 'B1' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test6 - 'B1' has a method 'incBB'`() {
		uml.methodCheck("incBB", methParamAndTypes=arrayOf(Pair("inc", Int::class)))
	}


}
