import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for A5 *****/

class TestUmlA5 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(A5::class)
	}

	@Test
	fun `test0 - class A5 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class A5 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'A5' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'A5' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'A5' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'A5' has an attribute 'bb'`() {
		uml.attributeCheck("bb", NutArray::class)
	}

	@Test
	fun `test6 - 'A5' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test7 - 'A5' has a method 'addB'`() {
		uml.methodCheck("addB", Boolean::class, methParamAndTypes=arrayOf(Pair("b", B5::class)))
	}


}
