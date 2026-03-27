package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Equipement *****/

class TestUmlEquipement {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Equipement::class)
	}

	@Test
	fun `test0 - class Equipement is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Equipement is open or not`() {
		uml.isOpen(true)
	}

	@Test
	fun `test2 - 'Equipement' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Equipement' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("desc", String::class)))
	}

	@Test
	fun `test4 - 'Equipement' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'Equipement' has an attribute 'description'`() {
		uml.attributeCheck("description", String::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test6 - 'Equipement' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test7 - 'Equipement' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}


}
