import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for Cercle *****/

class TestUmlCercle {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Cercle::class)
    }

    @Test
    fun `test0 - class Cercle is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Cercle is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Cercle' extends other classe(s)`() {
        uml.extend("Ellipse")
    }

    @Test
    fun `test3 - 'Cercle' has a constructor`() {
        uml.constructorCheck(
            paramsAndTypes = arrayOf(
                Pair("x", Int::class),
                Pair("y", Int::class),
                Pair("diametre", Int::class)
            )
        )
    }

    @Test
    fun `test4 - 'Cercle' has attribute(s)`() {
        uml.attributeNumber(0)
    }

    @Test
    fun `test5 - 'Cercle' has method(s)`() {
        uml.methodNumber(0)
    }


}
