import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Rectangle *****/

class TestUmlRectangle {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Rectangle::class)
	}

	@Test
	fun `test0 - class Rectangle is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Rectangle is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Rectangle' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Rectangle' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("p1", Point::class), Pair("p2", Point::class)))
	}

	@Test
	fun `test4 - 'Rectangle' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'Rectangle' has an attribute 'sommets'`() {
		uml.attributeCheck("sommets", NutArray::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test6 - 'Rectangle' has method(s)`() {
		uml.methodNumber(5)
	}

	@Test
	fun `test7 - 'Rectangle' has a method 'surface'`() {
		uml.methodCheck("surface", Int::class)
	}

	@Test
	fun `test8 - 'Rectangle' has a method 'perimetre'`() {
		uml.methodCheck("perimetre", Int::class)
	}

	@Test
	fun `test9 - 'Rectangle' has a method 'deplacer'`() {
		uml.methodCheck("deplacer", methParamAndTypes=arrayOf(Pair("v", Vecteur::class)))
	}

	@Test
	fun `test10 - 'Rectangle' has a method 'translater'`() {
		uml.methodCheck("translater", Rectangle::class, methParamAndTypes=arrayOf(Pair("v", Vecteur::class)))
	}

	@Test
	fun `test11 - 'Rectangle' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}


}
