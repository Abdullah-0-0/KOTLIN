import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Vecteur *****/

class TestUmlVecteur {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Vecteur::class)
	}

	@Test
	fun `test0 - class Vecteur is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Vecteur is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Vecteur' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Vecteur' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("orig", Point::class), Pair("dest", Point::class)))
	}

	@Test
	fun `test4 - 'Vecteur' has attribute(s)`() {
		uml.attributeNumber(2)
	}

	@Test
	fun `test5 - 'Vecteur' has an attribute 'origine'`() {
		uml.attributeCheck("origine", Point::class)
	}

	@Test
	fun `test6 - 'Vecteur' has an attribute 'destination'`() {
		uml.attributeCheck("destination", Point::class)
	}

	@Test
	fun `test7 - 'Vecteur' has method(s)`() {
		uml.methodNumber(3)
	}

	@Test
	fun `test8 - 'Vecteur' has a method 'distanceX'`() {
		uml.methodCheck("distanceX", Int::class)
	}

	@Test
	fun `test9 - 'Vecteur' has a method 'distanceY'`() {
		uml.methodCheck("distanceY", Int::class)
	}

	@Test
	fun `test10 - 'Vecteur' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}


}
