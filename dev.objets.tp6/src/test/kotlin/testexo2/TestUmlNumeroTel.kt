package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for NumeroTel *****/

class TestUmlNumeroTel {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(NumeroTel::class)
	}

	@Test
	fun `test0 - class NumeroTel is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class NumeroTel is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'NumeroTel' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'NumeroTel' has a constructor`() {
		uml.constructorCheck(paramsAndTypes=arrayOf(Pair("chaineNumTel", String::class)))
	}

	@Test
	fun `test4 - 'NumeroTel' has attribute(s)`() {
		uml.attributeNumber(1)
	}

	@Test
	fun `test5 - 'NumeroTel' has an attribute 'numTel'`() {
		uml.attributeCheck("numTel", Long::class)
	}

	@Test
	fun `test6 - 'NumeroTel' has method(s)`() {
		uml.methodNumber(0)
	}


}
