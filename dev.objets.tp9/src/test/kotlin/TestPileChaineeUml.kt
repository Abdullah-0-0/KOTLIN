import iut.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** Generated JUnit/UMLChecker Test Cases for PileChainee *****/

class TestPileChaineeUml {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(PileChainee::class)
	}

	@Test
	fun `test0 - class PileChainee is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class PileChainee is open or not`() {
		uml.isOpen(true)
	}

	@Test
	fun `test2 - 'PileChainee' extends other classe(s)`() {
		uml.extend("iPile")
	}

	@Test
	fun `test3 - 'PileChainee' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf())
	}

	@Test
	fun `test4 - 'PileChainee' has attribute(s)`() {
		uml.attributeNumber(1, strict = false)
	}

	@Test
	fun `test5 - 'PileChainee' has an attribute 'debut'`() {
		uml.attributeCheck("debut", Cellule::class, KVisibility.PROTECTED, nullable=true)
	}

	@Test
	fun `test6 - 'PileChainee' has method(s)`() {
		uml.methodNumber(5)
	}

	@Test
	fun `test7 - 'PileChainee' has a method 'estVide'`() {
		uml.methodCheck("estVide", Boolean::class, isOpenOrOverride=true)
	}

	@Test
	fun `test8 - 'PileChainee' has a method 'taille'`() {
		uml.methodCheck("taille", Int::class, isOpenOrOverride=true)
	}

	@Test
	fun `test9 - 'PileChainee' has a method 'empiler'`() {
		uml.methodCheck("empiler", methParamAndTypes=arrayOf(Pair("element", Int::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test10 - 'PileChainee' has a method 'consulter'`() {
		uml.methodCheck("consulter", Int::class, isOpenOrOverride=true)
	}

	@Test
	fun `test11 - 'PileChainee' has a method 'depiler'`() {
		uml.methodCheck("depiler", Int::class, isOpenOrOverride=true)
	}

}

