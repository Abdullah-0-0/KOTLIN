package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Arme *****/

class TestUmlArme {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Arme::class)
	}

	@Test
	fun `test0 - class Arme is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Arme is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Arme' extends other classe(s)`() {
		uml.extend("Equipement")
	}

	@Test
	fun `test3 - 'Arme' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("desc", String::class), Pair("att", Int::class), Pair("def", Int::class)))
	}

	@Test
	fun `test4 - 'Arme' has attribute(s)`() {
		uml.attributeNumber(2)
	}

	@Test
	fun `test5 - 'Arme' has an attribute 'attaque'`() {
		uml.attributeCheck("attaque", Int::class)
	}

	@Test
	fun `test6 - 'Arme' has an attribute 'defense'`() {
		uml.attributeCheck("defense", Int::class)
	}

	@Test
	fun `test7 - 'Arme' has method(s)`() {
		uml.methodNumber(1)
	}

	@Test
	fun `test8 - 'Arme' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}


}
