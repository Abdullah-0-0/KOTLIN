import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** Generated JUnit/UMLChecker Test Cases for FormeGeo *****/

class TestUmlFormeGeo {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(FormeGeo::class)
    }

    @Test
    fun `test0 - class FormeGeo is abstract or not`() {
        uml.isAbstract(true)
    }

    @Test
    fun `test1 - class FormeGeo is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'FormeGeo' extends other classe(s)`() {
        uml.extend("ObjetDessinable")
    }

    @Test
    fun `test3 - 'FormeGeo' has a constructor`() {
        uml.constructorCheck(
            paramsAndTypes = arrayOf(
                Pair("x", Int::class),
                Pair("y", Int::class),
                Pair("dim1", Int::class),
                Pair("dim2", Int::class)
            )
        )
    }

    @Test
    fun `test4 - 'FormeGeo' has attribute(s)`() {
        uml.attributeNumber(2)
    }

    @Test
    fun `test5 - 'FormeGeo' has an attribute 'dimension1'`() {
        uml.attributeCheck("dimension1", Int::class, KVisibility.PROTECTED)
    }

    @Test
    fun `test6 - 'FormeGeo' has an attribute 'dimension2'`() {
        uml.attributeCheck("dimension2", Int::class, KVisibility.PROTECTED)
    }

    @Test
    fun `test7 - 'FormeGeo' has method(s)`() {
        uml.methodNumber(2)
    }

    @Test
    fun `test8 - 'FormeGeo' has a method 'perimetre'`() {
        uml.methodCheck("perimetre", Double::class, isAbstract = true)
    }

    @Test
    fun `test9 - 'FormeGeo' has a method 'surface'`() {
        uml.methodCheck("surface", Double::class, isAbstract = true)
    }


}
