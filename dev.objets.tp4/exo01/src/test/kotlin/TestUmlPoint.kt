import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Point *****/

class TestUmlPoint {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Point::class)
	}

	@Test
	fun `test0 - class Point is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Point is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Point' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Point' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("x", Int::class), Pair("y", Int::class)))
	}

	@Test
	fun `test4 - 'Point' has attribute(s)`() {
		uml.attributeNumber(2)
	}

	@Test
	fun `test5 - 'Point' has an attribute 'x'`() {
		uml.attributeCheck("x", Int::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test6 - 'Point' has an attribute 'y'`() {
		uml.attributeCheck("y", Int::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test7 - 'Point' has method(s)`() {
		uml.methodNumber(2)
	}

	@Test
	fun `test8 - 'Point' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}

	@Test
	fun `test9 - 'Point' has a method 'translater'`() {
		uml.methodCheck("translater", Point::class, methParamAndTypes=arrayOf(Pair("v", Vecteur::class)))
	}


}
