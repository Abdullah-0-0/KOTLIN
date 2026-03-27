import info.but1.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Oeuvre *****/

class TestUmlOeuvre {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Oeuvre::class)
	}

	@Test
	fun `test0 - class Oeuvre is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Oeuvre is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Oeuvre' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Oeuvre' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("titre", String::class), Pair("auteur", Personne::class), Pair("themeInitial", Theme::class)))
	}

	@Test
	fun `test4 - 'Oeuvre' has attribute(s)`() {
		uml.attributeNumber(4)
	}

	@Test
	fun `test5 - 'Oeuvre' has an attribute 'titre'`() {
		uml.attributeCheck("titre", String::class)
	}

	@Test
	fun `test5 - 'Oeuvre' has an attribute 'nbThemesAssocies'`() {
		uml.attributeCheck("nbThemesAssocies", Int::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test6 - 'Oeuvre' has an attribute 'auteur'`() {
		uml.attributeCheck("auteur", Personne::class)
	}

	@Test
	fun `test7 - 'Oeuvre' has an attribute 'themesAssocies'`() {
		uml.attributeCheck("themesAssocies", NutArray::class)
	}

	@Test
	fun `test8 - 'Oeuvre' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test9 - 'Oeuvre' has a method 'ajouterUnTheme'`() {
		uml.methodCheck("ajouteUnTheme", methParamAndTypes=arrayOf(Pair("theme", Theme::class)))
	}


}
