import info.but1.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Galerie *****/

class TestUmlGalerie {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Galerie::class)
	}

	@Test
	fun `test0 - class Galerie is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Galerie is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Galerie' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Galerie' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("n", String::class)))
	}

	@Test
	fun `test4 - 'Galerie' has attribute(s)`() {
		uml.attributeNumber(5)
	}

	@Test
	fun `test5 - 'Galerie' has an attribute 'nom'`() {
		uml.attributeCheck("nom", String::class)
	}

	@Test
	fun `test5 - 'Galerie' has an attribute 'nbVisiteurs'`() {
		uml.attributeCheck("nbVisiteurs", Int::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test5 - 'Galerie' has an attribute 'nbOeuvresExposes'`() {
		uml.attributeCheck("nbOeuvresExposees", Int::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test6 - 'Galerie' has an attribute 'exposition'`() {
		uml.attributeCheck("exposition", NutArray::class)
	}

	@Test
	fun `test7 - 'Galerie' has an attribute 'visiteurs'`() {
		uml.attributeCheck("visiteurs", NutArray::class)
	}

	@Test
	fun `test8 - 'Galerie' has method(s)`() {
		uml.methodNumber(2)
	}

	@Test
	fun `test9 - 'Galerie' has a method 'expose'`() {
		uml.methodCheck("expose", methParamAndTypes=arrayOf(Pair("nouvelleOeuvre", Oeuvre::class)))
	}

	@Test
	fun `test10 - 'Galerie' has a method 'EstVisitePar'`() {
		uml.methodCheck("estVisitePar", methParamAndTypes=arrayOf(Pair("visiteur", Personne::class)))
	}


}
