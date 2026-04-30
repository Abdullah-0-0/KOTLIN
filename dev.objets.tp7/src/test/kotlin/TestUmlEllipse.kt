import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for Ellipse *****/

class TestUmlEllipse {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Ellipse::class)
    }

    @Test
    fun `test0 - class Ellipse is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Ellipse is open or not`() {
        uml.isOpen(true)
    }

    @Test
    fun `test2 - 'Ellipse' extends other classe(s)`() {
        uml.extend("FormeGeo")
    }

    @Test
    fun `test3 - 'Ellipse' has a constructor`() {
        uml.constructorCheck(
            paramsAndTypes = arrayOf(
                Pair("x", Int::class),
                Pair("y", Int::class),
                Pair("axeVertical", Int::class),
                Pair("axeHorizontal", Int::class)
            )
        )
    }

    @Test
    fun `test4 - 'Ellipse' has attribute(s)`() {
        uml.attributeNumber(0)
    }

    @Test
    fun `test5 - 'Ellipse' has method(s)`() {
        uml.methodNumber(3)
    }

    @Test
    fun `test6 - 'Ellipse' has a method 'perimetre'`() {
        uml.methodCheck("perimetre", Double::class, isOpenOrOverride = true)
    }

    @Test
    fun `test7 - 'Ellipse' has a method 'surface'`() {
        uml.methodCheck("surface", Double::class, isOpenOrOverride = true)
    }

    @Test
    fun `test8 - 'Ellipse' has a method 'dessiner'`() {
        uml.methodCheck("dessiner", String::class, isOpenOrOverride = true)
    }


}
