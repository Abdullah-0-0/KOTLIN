import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for C *****/

class TestUmlExo1C {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(C::class)
	}

	@Test
	fun `test0 - class C is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class C is open or not`() {
		uml.isOpen(true)
	}

	@Test
	fun `test2 - 'C' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'C' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'C' has method(s)`() {
		uml.methodNumber(3)
	}

	@Test
	fun `test5 - 'C' has a method 'ope0'`() {
		uml.methodCheck("ope0", Double::class)
	}

	@Test
	fun `test6 - 'C' has a method 'ope1'`() {
		uml.methodCheck("ope1", Double::class, isOpenOrOverride=true)
	}

	@Test
	fun `test7 - 'C' has a method 'ope2'`() {
		uml.methodCheck("ope2", Double::class, isOpenOrOverride=true)
	}


}
