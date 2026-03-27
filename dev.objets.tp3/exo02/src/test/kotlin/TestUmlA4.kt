import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for A4 *****/

class TestUmlA4 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A4::class)
	}

	@Test
	fun `test0 - class A4 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A4 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'A4' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'A4' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'A4' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A4' has an attribute 'b'`() {
		uml.attributeCheck("b", B4::class, nullable=true)
	}

	@Test
	fun `test6 - 'A4' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test7 - 'A4' has a method 'setB'`() {
		uml.methodCheck("setB", methParamAndTypes=arrayOf(Pair("b", B4::class)))
	}


}
