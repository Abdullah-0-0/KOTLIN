import info.but1.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Theme *****/

class TestUmlTheme {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Theme::class)
	}

	@Test
	fun `test0 - class Theme is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Theme is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Theme' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Theme' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("l", String::class)))
	}

	@Test
	fun `test4 - 'Theme' has attribute(s)`() {
		uml.attributeNumber(3)
	}

	@Test
	fun `test5 - 'Theme' has an attribute 'libelle'`() {
		uml.attributeCheck("libelle", String::class)
	}

	@Test
	fun `test6 - 'Theme' has an attribute 'sousThemes'`() {
		uml.attributeCheck("sousThemes", NutArray::class)
	}

	@Test
	fun `test6 - 'Theme' has an attribute 'nbSousThemes'`() {
		uml.attributeCheck("nbSousThemes", Int::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test7 - 'Theme' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test8 - 'Theme' has a method 'preciserSousTheme'`() {
		uml.methodCheck("preciseSousTheme", methParamAndTypes=arrayOf(Pair("theme", Theme::class)))
	}


}
