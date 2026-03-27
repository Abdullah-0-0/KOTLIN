package testexo1

import exo1.*
import info.but1.collections.NutArray
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Personnage *****/

class TestUmlPersonnage {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Personnage::class)
	}

	@Test
	fun `test0 - class Personnage is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Personnage is open or not`() {
		uml.isOpen(true)
	}

	@Test
	fun `test2 - 'Personnage' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Personnage' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("nom", String::class), Pair("force", Int::class), Pair("agi", Int::class), Pair("intel", Int::class)))
	}

	@Test
	fun `test4 - 'Personnage' has attribute(s)`() {
		uml.attributeNumber(6)
	}

	@Test
	fun `test5 - 'Personnage' has an attribute 'nom'`() {
		uml.attributeCheck("nom", String::class)
	}

	@Test
	fun `test6 - 'Personnage' has an attribute 'carac_force'`() {
		uml.attributeCheck("carac_force", Int::class)
	}

	@Test
	fun `test7 - 'Personnage' has an attribute 'carac_agilite'`() {
		uml.attributeCheck("carac_agilite", Int::class)
	}

	@Test
	fun `test8 - 'Personnage' has an attribute 'carac_intelligence'`() {
		uml.attributeCheck("carac_intelligence", Int::class, KVisibility.PROTECTED)
	}

	@Test
	fun `test9 - 'Personnage' has an attribute 'nbEquipementsPossedes'`() {
		uml.attributeCheck("nbEquipementsPossedes", Int::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test10 - 'Personnage' has an attribute 'inventaire'`() {
		uml.attributeCheck("inventaire", NutArray::class)
	}

	@Test
	fun `test11 - 'Personnage' has method(s)`() {
		uml.methodNumber(6)
	}

	@Test
	fun `test12 - 'Personnage' has a method 'listeCaracteristiques'`() {
		uml.methodCheck("listeCaracteristiques", String::class, isOpenOrOverride=true)
	}

	// lEquipementContient(equipement : Equipement)
	@Test
	fun `test13 - 'Personnage' has a method 'lEquipementContient`() {
		uml.methodCheck("lEquipementContient", Boolean::class, methParamAndTypes=arrayOf(Pair("equipement", Equipement::class)))
	}


		@Test
	fun `test13 - 'Personnage' has a method 'prend'`() {
		uml.methodCheck("prend", Boolean::class, methParamAndTypes=arrayOf(Pair("equipement", Equipement::class)))
	}

	@Test
	fun `test14 - 'Personnage' has a method 'attaque'`() {
		uml.methodCheck("attaque", Boolean::class, methParamAndTypes=arrayOf(Pair("arme", Arme::class), Pair("cible", Personnage::class)))
	}

	@Test
	fun `test15 - 'Personnage' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}

	@Test
	fun `test16 - 'Personnage' has a method 'listeInventaire'`() {
		uml.methodCheck("listeInventaire", String::class)
	}


}
