import iut.collections.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for iCollection *****/

class TestiCollectionUml {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(iCollection::class)
	}

	@Test
	fun `test0 - class iCollection is abstract or not`() {
		uml.isAbstract(true)
	}

	@Test
	fun `test1 - class iCollection is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'iCollection' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'iCollection' has attribute(s)`() {
		uml.attributeNumber(0)
	}

	@Test
	fun `test4 - 'iCollection' has method(s)`() {
		uml.methodNumber(2)
	}

	@Test
	fun `test5 - 'iCollection' has a method 'estVide'`() {
		uml.methodCheck("estVide", Boolean::class, isAbstract=true)
	}

	@Test
	fun `test6 - 'iCollection' has a method 'taille'`() {
		uml.methodCheck("taille", Int::class, isAbstract=true)
	}


}
