import iut.math.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Rational *****/

class TestUmlRational {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Rational::class)
	}

	@Test
	fun `test0 - class Rational is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Rational is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Rational' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Rational' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("numerator", Int::class), Pair("denominator", Int::class)))
	}

	@Test
	fun `test4 - 'Rational' has attribute(s)`() {
		uml.attributeNumber(2)
	}

	@Test
	fun `test5 - 'Rational' has an attribute 'numerator'`() {
		uml.attributeCheck("numerator", Int::class)
	}

	@Test
	fun `test6 - 'Rational' has an attribute 'denominator'`() {
		uml.attributeCheck("denominator", Int::class)
	}

	@Test
	fun `test7 - 'Rational' has method(s)`() {
		uml.methodNumber(1, strict = false)
	}

	@Test
	fun `test8 - 'Rational' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}

		@Test
		fun `test9 - 'Rational' has a method 'plus'`() {
			uml.methodCheck("plus", Rational::class, methParamAndTypes = arrayOf(Pair("rational", Rational::class)))
		}

}
