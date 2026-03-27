import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for A7 *****/

class TestUmlA7 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A7::class)
	}

	@Test
	fun `test0 - class A7 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A7 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'A7' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'A7' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'A7' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A7' has an attribute 'b'`() {
		uml.attributeCheck("b", B7::class, nullable=true)
	}

	@Test
	fun `test6 - 'A7' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test7 - 'A7' has a method 'setB'`() {
		uml.methodCheck("setB", methParamAndTypes=arrayOf(Pair("b", B7::class)))
	}


}
