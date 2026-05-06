import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Pays *****/

class TestUmlPays {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Pays::class)
	}

	@Test
	fun `test0 - class Pays is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Pays is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Pays' extends other classe(s)`() {
		uml.extend("Comparable")
	}

	@Test
	fun `test3 - 'Pays' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("no", String::class), Pair("cap", String::class), Pair("cont", String::class), Pair("pop", Int::class), Pair("sup", Double::class)))
	}

	@Test
	fun `test4 - 'Pays' has attribute(s)`() {
		uml.attributeNumber(5)
	}

	@Test
	fun `test5 - 'Pays' has an attribute 'nom'`() {
		uml.attributeCheck("nom", String::class)
	}

	@Test
	fun `test6 - 'Pays' has an attribute 'capitale'`() {
		uml.attributeCheck("capitale", String::class)
	}

	@Test
	fun `test7 - 'Pays' has an attribute 'continent'`() {
		uml.attributeCheck("continent", String::class)
	}

	@Test
	fun `test8 - 'Pays' has an attribute 'population'`() {
		uml.attributeCheck("population", Int::class)
	}

	@Test
	fun `test9 - 'Pays' has an attribute 'superficie'`() {
		uml.attributeCheck("superficie", Double::class)
	}

	@Test
	fun `test10 - 'Pays' has method(s)`() {
		uml.methodNumber(8)
	}

	@Test
	fun `test11 - 'Pays' has a method 'donneNom'`() {
		uml.methodCheck("donneNom", String::class)
	}

	@Test
	fun `test12 - 'Pays' has a method 'donneCapitale'`() {
		uml.methodCheck("donneCapitale", String::class)
	}

	@Test
	fun `test13 - 'Pays' has a method 'donneContinent'`() {
		uml.methodCheck("donneContinent", String::class)
	}

	@Test
	fun `test14 - 'Pays' has a method 'donnePopulation'`() {
		uml.methodCheck("donnePopulation", Int::class)
	}

	@Test
	fun `test15 - 'Pays' has a method 'donneSuperficie'`() {
		uml.methodCheck("donneSuperficie", Double::class)
	}

	@Test
	fun `test16 - 'Pays' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}

	@Test
	fun `test17 - 'Pays' has a method 'equals'`() {
		uml.methodCheck("equals", Boolean::class, methParamAndTypes=arrayOf(Pair("other", Any::class)), isOpenOrOverride=true)
	}

	@Test
	fun `test18 - 'Pays' has a method 'compareTo'`() {
		uml.methodCheck("compareTo", Int::class, methParamAndTypes=arrayOf(Pair("other", Pays::class)), isOpenOrOverride=true)
	}


}
