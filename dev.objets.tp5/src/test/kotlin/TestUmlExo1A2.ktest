import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for A2 *****/

class TestUmlExo1A2 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A2::class)
	}

	@Test
	fun `test0 - class A2 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A2 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'A2' extends other classe(s)`() {
		uml.extend("A")
	}

	@Test
	fun `test3 - 'A2' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("b", Bip::class), Pair("o", Another::class)))
	}

	@Test
	fun `test4 - 'A2' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A2' has an attribute 'bip'`() {
		uml.attributeCheck("bip", Bip::class)
	}

	@Test
	fun `test6 - 'A2' has method(s)`() {
		uml.methodNumber(0)
	}


}
