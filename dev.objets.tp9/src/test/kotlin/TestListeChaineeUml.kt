import iut.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** Generated JUnit/UMLChecker Test Cases for ListeChainee *****/

class TestListeChaineeUml {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(ListeChainee::class)
	}

	@Test
	fun `test0 - class ListeChainee is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class ListeChainee is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'ListeChainee' extends other classe(s)`() {
		uml.extend("iListe", "PileChainee")
	}

	@Test
	fun `test3 - 'ListeChainee' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'ListeChainee' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test5 - 'ListeChainee' has method(s)`() {
		uml.methodNumber(7)
	}

	@Test
	fun `test6 - 'ListeChainee' has a method 'index'`() {
		uml.methodCheck("index", Int::class, methParamAndTypes=arrayOf(Pair("element", Int::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test7 - 'ListeChainee' has a method 'consulter'`() {
		uml.methodCheck("consulter", Int::class, methParamAndTypes=arrayOf(Pair("index", Int::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test8 - 'ListeChainee' has a method 'inserer'`() {
		uml.methodCheck("inserer", methParamAndTypes=arrayOf(Pair("index", Int::class), Pair("element", Int::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test9 - 'ListeChainee' has a method 'supprimer'`() {
		uml.methodCheck("supprimer", methParamAndTypes=arrayOf(Pair("index", Int::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test10 - 'ListeChainee' has a method 'insererEnQueue'`() {
		uml.methodCheck("insererEnQueue", methParamAndTypes=arrayOf(Pair("element", Int::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test11 - 'ListeChainee' has a method 'consulterEnQueue'`() {
		uml.methodCheck("consulterEnQueue", Int::class, isOpenOrOverride=true)
	}

	@Test
	fun `test12 - 'ListeChainee' has a method 'supprimerEnQueue'`() {
		uml.methodCheck("supprimerEnQueue", methParamAndTypes=arrayOf(), isOpenOrOverride=true)
	}

}

