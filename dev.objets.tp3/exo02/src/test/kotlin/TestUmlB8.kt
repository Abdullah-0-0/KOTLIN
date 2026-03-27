import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for B8 *****/

class TestUmlB8 {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(B8::class)
	}

	@Test
	fun `test0 - class B8 is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class B8 is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'B8' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'B8' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'B8' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'B8' has an attribute 'aa'`() {
		uml.attributeCheck("aa", NutArray::class)
	}

	@Test
	fun `test6 - 'B8' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test7 - 'B8' has a method 'addA'`() {
		uml.methodCheck("addA", Boolean::class, methParamAndTypes=arrayOf(Pair("a", A8::class)))
	}


}
