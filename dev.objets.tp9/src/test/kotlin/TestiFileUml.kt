import iut.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for iFile *****/

class TestiFileUml {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(iFile::class)
	}

	@Test
	fun `test0 - class iFile is abstract or not`() {
		uml.isAbstract(true)
	}

	@Test
	fun `test1 - class iFile is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'iFile' extends other classe(s)`() {
		uml.extend("iPile")
	}

	@Test
	fun `test3 - 'iFile' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'iFile' has method(s)`() {
		uml.methodNumber(6)
	}

	@Test
	fun `test5 - 'iFile' has a method 'insererEnTete'`() {
		uml.methodCheck("insererEnTete", methParamAndTypes=arrayOf(Pair("element", Int::class)), isAbstract=false, isOpenOrOverride = true)
	}

	@Test
	fun `test6 - 'iFile' has a method 'consulterEnTete'`() {
		uml.methodCheck("consulterEnTete", Int::class, isAbstract=false, isOpenOrOverride = true)
	}

	@Test
	fun `test7 - 'iFile' has a method 'supprimerEnTete'`() {
		uml.methodCheck("supprimerEnTete", methParamAndTypes=arrayOf(), isAbstract=false, isOpenOrOverride = true)
	}

	@Test
	fun `test8 - 'iFile' has a method 'insererEnQueue'`() {
		uml.methodCheck("insererEnQueue", methParamAndTypes=arrayOf(Pair("element", Int::class)), isAbstract=true)
	}

	@Test
	fun `test9 - 'iFile' has a method 'consulterEnQueue'`() {
		uml.methodCheck("consulterEnQueue", Int::class, isAbstract=true)
	}

	@Test
	fun `test10 - 'iFile' has a method 'supprimerEnQueue'`() {
		uml.methodCheck("supprimerEnQueue", methParamAndTypes=arrayOf(), isAbstract=true)
	}


}
