import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for Carre *****/

class TestUmlCarre {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Carre::class)
    }

    @Test
    fun `test0 - class Carre is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Carre is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Carre' extends other classe(s)`() {
        uml.extend("Rectangle")
    }

    @Test
    fun `test3 - 'Carre' has a constructor`() {
        uml.constructorCheck(
            paramsAndTypes = arrayOf(
                Pair("x", Int::class),
                Pair("y", Int::class),
                Pair("cote", Int::class)
            )
        )
    }

    @Test
    fun `test4 - 'Carre' has attribute(s)`() {
        uml.attributeNumber(0)
    }

    @Test
    fun `test5 - 'Carre' has method(s)`() {
        uml.methodNumber(0)
    }


}
