import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for ObjetGraphique *****/

class TestUmlObjetGraphique {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(ObjetGraphique::class)
    }

    @Test
    fun `test0 - class ObjetGraphique is abstract or not`() {
        uml.isAbstract(true)
    }

    @Test
    fun `test1 - class ObjetGraphique is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'ObjetGraphique' extends other classe(s)`() {
        uml.extend("IManipulable")
    }

    @Test
    fun `test3 - 'ObjetGraphique' has a constructor`() {
        uml.constructorCheck(paramsAndTypes = arrayOf())
    }

    @Test
    fun `test4 - 'ObjetGraphique' has attribute(s)`() {
        uml.attributeNumber(0)
    }

    @Test
    fun `test5 - 'ObjetGraphique' has method(s)`() {
        uml.methodNumber(0)
    }


}
