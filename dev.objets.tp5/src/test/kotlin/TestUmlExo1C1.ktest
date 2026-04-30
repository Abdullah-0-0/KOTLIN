import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for C1 *****/

class TestUmlExo1C1 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(C1::class)
	}

	@Test
	fun `test0 - class C1 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class C1 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'C1' extends other classe(s)`() {
		uml.extend("C")
	}

	@Test
	fun `test3 - 'C1' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'C1' has method(s)`() {
		uml.methodNumber(2)
	}

	@Test
	fun `test5 - 'C1' has a method 'ope1'`() {
		uml.methodCheck("ope1", Double::class, isOpenOrOverride=true)
	}

	@Test
	fun `test6 - 'C1' has a method 'ope3'`() {
		uml.methodCheck("ope3", Double::class)
	}


}
