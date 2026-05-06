import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Monde *****/

class TestUmlMonde {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Monde::class)
	}

	@Test
	fun `test0 - class Monde is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Monde is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Monde' extends other classe(s)`() {
		uml.extend("iMonde")
	}

	@Test
	fun `test3 - 'Monde' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'Monde' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'Monde' has an attribute 'lesPays'`() {
		uml.attributeCheck("lesPays", MutableList::class)
	}

	@Test
	fun `test6 - 'Monde' has method(s)`() {
		uml.methodNumber(12)
	}

	@Test
	fun `test7 - 'Monde' has a method 'taille'`() {
		uml.methodCheck("taille", Int::class, isOpenOrOverride=true)
	}

	@Test
	fun `test8 - 'Monde' has a method 'ajouter'`() {
		uml.methodCheck("ajouter", Boolean::class, methParamAndTypes=arrayOf(Pair("p", Pays::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test9 - 'Monde' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}

	@Test
	fun `test10 - 'Monde' has a method 'remplir'`() {
		uml.methodCheck("remplir", methParamAndTypes=arrayOf(Pair("nomFichier", String::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test11 - 'Monde' has a method 'getPays'`() {
		uml.methodCheck("getPays", Pays::class, methParamAndTypes=arrayOf(Pair("nom", String::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test12 - 'Monde' has a method 'getPays2'`() {
		uml.methodCheck("getPays2", Pays::class, methParamAndTypes=arrayOf(Pair("nom", String::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test13 - 'Monde' has a method 'plusPeuple'`() {
		uml.methodCheck("plusPeuple", MutableList::class, methParamAndTypes=arrayOf(Pair("ref", Pays::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test14 - 'Monde' has a method 'populationSuperieureA'`() {
		uml.methodCheck("populationSuperieureA", MutableList::class, methParamAndTypes=arrayOf(Pair("limite", Int::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test15 - 'Monde' has a method 'monde'`() {
		uml.methodCheck("monde", MutableList::class, isOpenOrOverride=true)
	}

	@Test
	fun `test16 - 'Monde' has a method 'mondeTrie'`() {
		uml.methodCheck("mondeTrie", MutableList::class, isOpenOrOverride=true)
	}

	@Test
	fun `test17 - 'Monde' has a method 'mondeTrieTab'`() {
		uml.methodCheck("mondeTrieTab", Array<Pays>::class, isOpenOrOverride=true)
	}

	@Test
	fun `test18 - 'Monde' has a method 'mondeTrieSelonSuperficie'`() {
		uml.methodCheck("mondeTrieSelonSuperficie", MutableList::class, isOpenOrOverride=true)
	}


}
