package testexo1

import exo1.*
import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Magicien *****/

class TestUmlMagicien {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Magicien::class)
	}

	@Test
	fun `test0 - class Magicien is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Magicien is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Magicien' extends other classe(s)`() {
		uml.extend("Personnage")
	}

	@Test
	fun `test3 - 'Magicien' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("nom", String::class), Pair("force", Int::class), Pair("agi", Int::class), Pair("intel", Int::class), Pair("mag", Int::class), Pair("sort0", Sort::class)))
	}

	@Test
	fun `test4 - 'Magicien' has attribute(s)`() {
		uml.attributeNumber(3)
	}

	@Test
	fun `test5 - 'Magicien' has an attribute 'carac_magie'`() {
		uml.attributeCheck("carac_magie", Int::class)
	}

	@Test
	fun `test6 - 'Magicien' has an attribute 'nbSortsConnus'`() {
		uml.attributeCheck("nbSortsConnus", Int::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test7 - 'Magicien' has an attribute 'grimoire'`() {
		uml.attributeCheck("grimoire", NutArray::class)
	}

	@Test
	fun `test8 - 'Magicien' has method(s)`() {
		uml.methodNumber(5)
	}

	@Test
	fun `test9 - 'Magicien' has a method 'listeCaracteristiques'`() {
		uml.methodCheck("listeCaracteristiques", String::class, isOpenOrOverride=true)
	}

	@Test
	fun `test10 - 'Magicien' has a method 'leGrimoireContient'`() {
		uml.methodCheck("leGrimoireContient", Boolean::class, methParamAndTypes=arrayOf(Pair("sort", Sort::class)))
	}

	@Test
	fun `test10 - 'Magicien' has a method 'apprend'`() {
		uml.methodCheck("apprend", Boolean::class, methParamAndTypes=arrayOf(Pair("nouveau", Sort::class)))
	}

	@Test
	fun `test11 - 'Magicien' has a method 'lance'`() {
		uml.methodCheck("lance", Boolean::class, methParamAndTypes=arrayOf(Pair("sort", Sort::class)))
	}

	@Test
	fun `test12 - 'Magicien' has a method 'listeDesSorts'`() {
		uml.methodCheck("listeDesSorts", NutArray::class)
	}


}
