import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Camion *****/

class TestUmlV1Camion {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Camion::class)
	}

	@Test
	fun `test0 - class Camion is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Camion is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Camion' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Camion' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("places", Int::class)))
	}

	@Test
	fun `test4 - 'Camion' has attribute(s)`() {
		uml.attributeNumber(2)
	}

	@Test
	fun `test5 - 'Camion' has an attribute 'placesOccupees'`() {
		uml.attributeCheck("placesOccupees", Int::class)
	}

	@Test
	fun `test6 - 'Camion' has an attribute 'remorque'`() {
		uml.attributeCheck("remorque", NutArray::class)
	}

	@Test
	fun `test7 - 'Camion' has method(s)`() {
		uml.methodNumber(4)
	}

	@Test
	fun `test8 - 'Camion' has a method 'estPlein'`() {
		uml.methodCheck("estPlein", Boolean::class)
	}

	@Test
	fun `test9 - 'Camion' has a method 'estVide'`() {
		uml.methodCheck("estVide", Boolean::class)
	}

	@Test
	fun `test10 - 'Camion' has a method 'charger'`() {
		uml.methodCheck("charger", Boolean::class, methParamAndTypes=arrayOf(Pair("voitureTransportee", Voiture::class)))
	}

	@Test
	fun `test11 - 'Camion' has a method 'decharger'`() {
		uml.methodCheck("decharger", Voiture::class)
	}


}
