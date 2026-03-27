import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Voiture *****/

class TestUmlVoiture {

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
		uml.attributeNumber(5)
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
	fun `test11 - 'Voiture' has method(s)`() {
		uml.methodNumber(7)
	}

	@Test
	fun `test13 - 'Voiture' has a method 'repeindre'`() {
		uml.methodCheck("repeindre", methParamAndTypes=arrayOf(Pair("nouvelleCouleur", String::class)))
	}

	@Test
	fun `test14 - 'Voiture' has a method 'donneInformations'`() {
		uml.methodCheck("donneInformations", String::class)
	}

	@Test
	fun `test15 - 'Voiture' has a method 'demarrer'`() {
		uml.methodCheck("demarrer", methParamAndTypes=arrayOf())
	}

	@Test
	fun `test16 - 'Voiture' has a method 'arreter'`() {
		uml.methodCheck("arreter", methParamAndTypes=arrayOf())
	}

	@Test
	fun `test17 - 'Voiture' has a method 'estEnMarche'`() {
		uml.methodCheck("estEnMarche", Boolean::class)
	}

	@Test
	fun `test18 - 'Voiture' has a method 'accelerer'`() {
		uml.methodCheck("accelerer", Double::class, methParamAndTypes=arrayOf(Pair("acceleration", Double::class)))
	}

	@Test
	fun `test19 - 'Voiture' has a method 'decelerer'`() {
		uml.methodCheck("decelerer", Double::class, methParamAndTypes=arrayOf(Pair("deceleration", Double::class)))
	}


}
