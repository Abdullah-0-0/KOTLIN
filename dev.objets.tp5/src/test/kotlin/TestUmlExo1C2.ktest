import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for C2 *****/

class TestUmlExo1C2 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(C2::class)
	}

	@Test
	fun `test0 - class C2 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class C2 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'C2' extends other classe(s)`() {
		uml.extend("C")
	}

	@Test
	fun `test3 - 'C2' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'C2' has method(s)`() {
		uml.methodNumber(2)
	}

	@Test
	fun `test5 - 'C2' has a method 'ope2'`() {
		uml.methodCheck("ope2", Double::class, isOpenOrOverride=true)
	}

	@Test
	fun `test6 - 'C2' has a method 'ope4'`() {
		uml.methodCheck("ope4", Double::class)
	}


}
