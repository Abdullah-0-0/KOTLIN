import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for A6 *****/

class TestUmlA6 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A6::class)
	}

	@Test
	fun `test0 - class A6 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A6 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'A6' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'A6' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("b", B6::class)))
	}

	@Test
	fun `test4 - 'A6' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A6' has an attribute 'bb'`() {
		uml.attributeCheck("bb", NutArray::class)
	}

	@Test
	fun `test6 - 'A6' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test7 - 'A6' has a method 'addB'`() {
		uml.methodCheck("addB", Boolean::class, methParamAndTypes=arrayOf(Pair("b", B6::class)))
	}


}
