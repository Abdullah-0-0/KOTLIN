import iut.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** Generated JUnit/UMLChecker Test Cases for Cellule *****/

class TestCelluleUml {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Cellule::class)
	}

	@Test
	fun `test0 - class Cellule is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Cellule is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Cellule' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Cellule' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("valeur", Int::class), Pair("suivant", Cellule::class)))
	}

	@Test
	fun `test4 - 'Cellule' has attribute(s)`() {
		uml.attributeNumber(2)
	}

	@Test
	fun `test5 - 'Cellule' has an attribute 'valeur'`() {
		uml.attributeCheck("valeur", Int::class)
	}

	@Test
	fun `test6 - 'Cellule' has an attribute 'suivant'`() {
		uml.attributeCheck("suivant", Cellule::class, nullable=true)
	}

	@Test
	fun `test7 - 'Cellule' has method(s)`() {
		uml.methodNumber(3)
	}

	@Test
	fun `test8 - 'Cellule' has a method 'valeur'`() {
		uml.methodCheck("valeur", Int::class)
	}

	@Test
	fun `test9 - 'Cellule' has a method 'suivant'`() {
		uml.methodCheck("suivant", Cellule::class)
	}

	@Test
	fun `test10 - 'Cellule' has a method 'modifieSuivant'`() {
		uml.methodCheck("modifieSuivant", methParamAndTypes=arrayOf(Pair("nouveauSuivant", Cellule::class)))
	}

}

