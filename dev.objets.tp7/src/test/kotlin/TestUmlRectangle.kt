import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for Rectangle *****/

class TestUmlRectangle {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Rectangle::class)
    }

    @Test
    fun `test0 - class Rectangle is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Rectangle is open or not`() {
        uml.isOpen(true)
    }

    @Test
    fun `test2 - 'Rectangle' extends other classe(s)`() {
        uml.extend("FormeGeo")
    }

    @Test
    fun `test3 - 'Rectangle' has a constructor`() {
        uml.constructorCheck(
            paramsAndTypes = arrayOf(
                Pair("x", Int::class),
                Pair("y", Int::class),
                Pair("hauteur", Int::class),
                Pair("largeur", Int::class)
            )
        )
    }

    @Test
    fun `test4 - 'Rectangle' has attribute(s)`() {
        uml.attributeNumber(0)
    }

    @Test
    fun `test5 - 'Rectangle' has method(s)`() {
        uml.methodNumber(3)
    }

    @Test
    fun `test6 - 'Rectangle' has a method 'perimetre'`() {
        uml.methodCheck("perimetre", Double::class, isOpenOrOverride = true)
    }

    @Test
    fun `test7 - 'Rectangle' has a method 'surface'`() {
        uml.methodCheck("surface", Double::class, isOpenOrOverride = true)
    }

    @Test
    fun `test8 - 'Rectangle' has a method 'dessiner'`() {
        uml.methodCheck("dessiner", String::class, isOpenOrOverride = true)
    }


}
