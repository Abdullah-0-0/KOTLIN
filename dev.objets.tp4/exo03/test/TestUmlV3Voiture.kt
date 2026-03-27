import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Voiture *****/

class TestUmlV3Voiture {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Voiture::class)
	}

	@Test
	fun `test0 - class Voiture is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Voiture is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Voiture' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Voiture' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("mod", String::class), Pair("coul", String::class), Pair("vitMax", Double::class)))
	}

	@Test
	fun `test4 - 'Voiture' has attribute(s)`() {
		uml.attributeNumber(7)
	}

	@Test
	fun `test5 - 'Voiture' has an attribute 'modele'`() {
		uml.attributeCheck("modele", String::class)
	}

	@Test
	fun `test6 - 'Voiture' has an attribute 'couleur'`() {
		uml.attributeCheck("couleur", String::class)
	}

	@Test
	fun `test7 - 'Voiture' has an attribute 'vitesseCourante'`() {
		uml.attributeCheck("vitesseCourante", Double::class)
	}

	@Test
	fun `test8 - 'Voiture' has an attribute 'vitesseMaximum'`() {
		uml.attributeCheck("vitesseMaximum", Double::class)
	}

	@Test
	fun `test9 - 'Voiture' has an attribute 'enMarche'`() {
		uml.attributeCheck("enMarche", Boolean::class)
	}

	@Test
	fun `test10 - 'Voiture' has an attribute 'proprietaire'`() {
		uml.attributeCheck("proprietaire", Personne::class, nullable=true)
	}

	@Test
	fun `test11 - 'Voiture' has an attribute 'parking'`() {
		uml.attributeCheck("parking", Parking::class, nullable=true)
	}

	@Test
	fun `test12 - 'Voiture' has method(s)`() {
		uml.methodNumber(11)
	}

	@Test
	fun `test13 - 'Voiture' has a method 'acheter'`() {
		uml.methodCheck("acheter", methParamAndTypes=arrayOf(Pair("acheteur", Personne::class)))
	}

	@Test
	fun `test14 - 'Voiture' has a method 'repeindre'`() {
		uml.methodCheck("repeindre", methParamAndTypes=arrayOf(Pair("nouvelleCouleur", String::class)))
	}

	@Test
	fun `test15 - 'Voiture' has a method 'donneInformations'`() {
		uml.methodCheck("donneInformations", String::class)
	}

	@Test
	fun `test16 - 'Voiture' has a method 'demarrer'`() {
		uml.methodCheck("demarrer", methParamAndTypes=arrayOf())
	}

	@Test
	fun `test17 - 'Voiture' has a method 'arreter'`() {
		uml.methodCheck("arreter", methParamAndTypes=arrayOf())
	}

	@Test
	fun `test18 - 'Voiture' has a method 'estEnMarche'`() {
		uml.methodCheck("estEnMarche", Boolean::class)
	}

	@Test
	fun `test19 - 'Voiture' has a method 'accelerer'`() {
		uml.methodCheck("accelerer", Double::class, methParamAndTypes=arrayOf(Pair("acceleration", Double::class)))
	}

	@Test
	fun `test20 - 'Voiture' has a method 'decelerer'`() {
		uml.methodCheck("decelerer", Double::class, methParamAndTypes=arrayOf(Pair("deceleration", Double::class)))
	}

	@Test
	fun `test21 - 'Voiture' has a method 'estGaree'`() {
		uml.methodCheck("estGaree", Boolean::class)
	}

	@Test
	fun `test22 - 'Voiture' has a method 'garerDans'`() {
		uml.methodCheck("garerDans", Boolean::class, methParamAndTypes=arrayOf(Pair("unParking", Parking::class)))
	}

	@Test
	fun `test23 - 'Voiture' has a method 'quitterStationnement'`() {
		uml.methodCheck("quitterStationnement", Boolean::class)
	}


}
