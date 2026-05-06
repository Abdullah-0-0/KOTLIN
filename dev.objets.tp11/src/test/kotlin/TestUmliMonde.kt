import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for iMonde *****/

class TestUmliMonde {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(iMonde::class)
	}

	@Test
	fun `test0 - class iMonde is abstract or not`() {
		uml.isAbstract(true)
	}

	@Test
	fun `test1 - class iMonde is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'iMonde' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'iMonde' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'iMonde' has method(s)`() {
		uml.methodNumber(12)
	}

	@Test
	fun `test5 - 'iMonde' has a method 'taille'`() {
		uml.methodCheck("taille", Int::class, isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test6 - 'iMonde' has a method 'ajouter'`() {
		uml.methodCheck("ajouter", Boolean::class, methParamAndTypes=arrayOf(Pair("p", Pays::class)), isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test7 - 'iMonde' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test8 - 'iMonde' has a method 'remplir'`() {
		uml.methodCheck("remplir", methParamAndTypes=arrayOf(Pair("nomFichier", String::class)), isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test9 - 'iMonde' has a method 'getPays'`() {
		uml.methodCheck("getPays", Pays::class, methParamAndTypes=arrayOf(Pair("nom", String::class)), isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test10 - 'iMonde' has a method 'getPays2'`() {
		uml.methodCheck("getPays2", Pays::class, methParamAndTypes=arrayOf(Pair("nom", String::class)), isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test11 - 'iMonde' has a method 'plusPeuple'`() {
		uml.methodCheck("plusPeuple", MutableList::class, methParamAndTypes=arrayOf(Pair("ref", Pays::class)), isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test12 - 'iMonde' has a method 'populationSuperieureA'`() {
		uml.methodCheck("populationSuperieureA", MutableList::class, methParamAndTypes=arrayOf(Pair("limite", Int::class)), isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test13 - 'iMonde' has a method 'monde'`() {
		uml.methodCheck("monde", MutableList::class, isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test14 - 'iMonde' has a method 'mondeTrie'`() {
		uml.methodCheck("mondeTrie", MutableList::class, isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test15 - 'iMonde' has a method 'mondeTrieTab'`() {
		uml.methodCheck("mondeTrieTab", Array<Pays>::class, isOpenOrOverride=false, isAbstract = true)
	}

	@Test
	fun `test16 - 'iMonde' has a method 'mondeTrieSelonSuperficie'`() {
		uml.methodCheck("mondeTrieSelonSuperficie", MutableList::class, isOpenOrOverride=false, isAbstract = true)
	}


}
