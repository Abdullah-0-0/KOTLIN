import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Parking *****/

class TestUmlParking {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Parking::class)
	}

	@Test
	fun `test0 - class Parking is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Parking is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Parking' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Parking' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("placesMax", Int::class)))
	}

	@Test
	fun `test4 - 'Parking' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'Parking' has an attribute 'stationnement'`() {
		uml.attributeCheck("stationnement", NutArray::class)
	}

	@Test
	fun `test6 - 'Parking' has method(s)`() {
		uml.methodNumber(6)
	}

	@Test
	fun `test7 - 'Parking' has a method 'nombreDePlacesLibres'`() {
		uml.methodCheck("nombreDePlacesLibres", Int::class)
	}

	@Test
	fun `test8 - 'Parking' has a method 'nombreDePlacesTotales'`() {
		uml.methodCheck("nombreDePlacesTotales", Int::class)
	}

	@Test
	fun `test9 - 'Parking' has a method 'placeLibre'`() {
		uml.methodCheck("placeLibre", Boolean::class, methParamAndTypes=arrayOf(Pair("numeroPlace", Int::class)))
	}

	@Test
	fun `test10 - 'Parking' has a method 'donnePremierePlaceLibre'`() {
		uml.methodCheck("donnePremierePlaceLibre", Int::class)
	}

	@Test
	fun `test11 - 'Parking' has a method 'stationner'`() {
		uml.methodCheck("stationner", Boolean::class, methParamAndTypes=arrayOf(Pair("numeroPlace", Int::class), Pair("voitureStationnee", Voiture::class)))
	}

	@Test
	fun `test12 - 'Parking' has a method 'libererPlace'`() {
		uml.methodCheck("libererPlace", Boolean::class, methParamAndTypes=arrayOf(Pair("voitureGaree", Voiture::class)))
	}


}
