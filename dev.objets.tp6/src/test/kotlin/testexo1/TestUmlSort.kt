package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Sort *****/

class TestUmlSort {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Sort::class)
	}

	@Test
	fun `test0 - class Sort is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Sort is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Sort' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Sort' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("nom", String::class), Pair("desc", String::class)))
	}

	@Test
	fun `test4 - 'Sort' has attribute(s)`() {
		uml.attributeNumber(2)
	}

	@Test
	fun `test5 - 'Sort' has an attribute 'nom'`() {
		uml.attributeCheck("nom", String::class, KVisibility.PUBLIC)
	}

	@Test
	fun `test6 - 'Sort' has an attribute 'description'`() {
		uml.attributeCheck("description", String::class)
	}

	@Test
	fun `test7 - 'Sort' has method(s)`() {
		uml.methodNumber(2)
	}

	@Test
	fun `test8 - 'Sort' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, isOpenOrOverride=true)
	}

	@Test
	fun `test9 - 'Sort' has a method 'equals'`() {
		uml.methodCheck("equals", Boolean::class, methParamAndTypes=arrayOf(Pair("other", Any::class)), isOpenOrOverride=true)
	}


}
