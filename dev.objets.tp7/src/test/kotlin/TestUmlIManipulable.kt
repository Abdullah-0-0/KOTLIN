import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for Manipulable *****/

class TestUmlIManipulable {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(IManipulable::class)
    }

    @Test
    fun `test0 - class Manipulable is abstract or not`() {
        uml.isAbstract(true)
    }

    @Test
    fun `test1 - class Manipulable is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Manipulable' extends other classe(s)`() {
        uml.extendNothing()
    }

    @Test
    fun `test3 - 'Manipulable' has attribute(s)`() {
        uml.attributeNumber(0)
    }

    @Test
    fun `test4 - 'Manipulable' has method(s)`() {
        uml.methodNumber(2)
    }

    @Test
    fun `test5 - 'Manipulable' has a method 'deplacer'`() {
        uml.methodCheck("deplacer", methParamAndTypes = arrayOf(Pair("deplacement", Vecteur::class)), isAbstract = true)
    }


    @Test
    fun `test5 - 'Manipulable' has a method 'selectionner'`() {
        uml.methodCheck("selectionner", methParamAndTypes = arrayOf(Pair("ok", Boolean::class)), isAbstract = true)
    }


}
