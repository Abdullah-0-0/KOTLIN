import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** Generated JUnit/UMLChecker Test Cases for Feuille *****/

class TestUmlPoint {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Point::class)
    }

    @Test
    fun `test0 - class Point is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Point is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Point' extends other classe(s)`() {
        uml.extendNothing()
    }

    @Test
    fun `test3 - 'Point' has a constructor`() {
        uml.constructorCheck(paramsAndTypes = arrayOf(Pair("x", Int::class), Pair("y", Int::class)))
    }

    @Test
    fun `test4 - 'Feuille' has attribute(s)`() {
        uml.attributeNumber(2)
    }

    @Test
    fun `test5 - 'Feuille' has an attribute 'x'`() {
        uml.attributeCheck("x", Int::class, KVisibility.PUBLIC)
    }

    @Test
    fun `test6 - 'Feuille' has an attribute 'y'`() {
        uml.attributeCheck("y", Int::class, KVisibility.PUBLIC)
    }

    @Test
    fun `test9 - 'Feuille' has method(s)`() {
        uml.methodNumber(0)
    }

}
