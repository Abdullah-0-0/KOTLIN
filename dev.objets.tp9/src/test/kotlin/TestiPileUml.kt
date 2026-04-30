import iut.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for iPile *****/

class TestiPileUml {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(iPile::class)
	}

	@Test
	fun `test0 - class iPile is abstract or not`() {
		uml.isAbstract(true)
	}

	@Test
	fun `test1 - class iPile is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'iPile' extends other classe(s)`() {
		uml.extend("iCollection")
	}

	@Test
	fun `test3 - 'iPile' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'iPile' has method(s)`() {
		uml.methodNumber(3)
	}

	@Test
	fun `test5 - 'iPile' has a method 'empiler'`() {
		uml.methodCheck("empiler", methParamAndTypes=arrayOf(Pair("element", Int::class)), isAbstract=true)
	}

	@Test
	fun `test6 - 'iPile' has a method 'consulter'`() {
		uml.methodCheck("consulter", Int::class, isAbstract=true)
	}

	@Test
	fun `test7 - 'iPile' has a method 'depiler'`() {
		uml.methodCheck("depiler", Int::class, isAbstract=true)
	}


}
