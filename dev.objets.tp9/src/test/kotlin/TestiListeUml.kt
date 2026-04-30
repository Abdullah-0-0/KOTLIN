import iut.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for iListe *****/

class TestiListeUml {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(iListe::class)
	}

	@Test
	fun `test0 - class iListe is abstract or not`() {
		uml.isAbstract(true)
	}

	@Test
	fun `test1 - class iListe is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'iListe' extends other classe(s)`() {
		uml.extend("iFile")
	}

	@Test
	fun `test3 - 'iListe' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'iListe' has method(s)`() {
		uml.methodNumber(4)
	}

	@Test
	fun `test5 - 'iListe' has a method 'index'`() {
		uml.methodCheck("index", Int::class, methParamAndTypes=arrayOf(Pair("element", Int::class)), isAbstract=true)
	}

	@Test
	fun `test6 - 'iListe' has a method 'consulter'`() {
		uml.methodCheck("consulter", Int::class, methParamAndTypes=arrayOf(Pair("index", Int::class)), isAbstract=true)
	}

	@Test
	fun `test7 - 'iListe' has a method 'inserer'`() {
		uml.methodCheck("inserer", methParamAndTypes=arrayOf(Pair("index", Int::class), Pair("element", Int::class)), isAbstract=true)
	}

	@Test
	fun `test8 - 'iListe' has a method 'supprimer'`() {
		uml.methodCheck("supprimer", methParamAndTypes=arrayOf(Pair("index", Int::class)), isAbstract=true)
	}


}
