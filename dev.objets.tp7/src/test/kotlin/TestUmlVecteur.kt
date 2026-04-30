import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** Generated JUnit/UMLChecker Test Cases for Feuille *****/

class TestUmlVecteur {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Vecteur::class)
    }

    @Test
    fun `test0 - class Vecteur is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Vecteur is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Vecteur' extends other classe(s)`() {
        uml.extendNothing()
    }

    @Test
    fun `test3 - 'Vecteur' has a constructor`() {
        uml.constructorCheck(paramsAndTypes = arrayOf(Pair("origine", Point::class), Pair("destination", Point::class)))
    }

    @Test
    fun `test4 - 'Vecteur' has attribute(s)`() {
        uml.attributeNumber(2)
    }

    @Test
    fun `test5 - 'Vecteur' has an attribute 'origine'`() {
        uml.attributeCheck("origine", Point::class, KVisibility.PRIVATE)
    }

    @Test
    fun `test6 - 'Vecteur' has an attribute 'destination'`() {
        uml.attributeCheck("destination", Point::class, KVisibility.PRIVATE)
    }

    @Test
    fun `test9 - 'Vecteur' has method(s)`() {
        uml.methodNumber(1)
    }

    @Test
    fun `test5 - 'Vecteur' has a method 'appliquer'`() {
        uml.methodCheck(
            "appliquer", methParamAndTypes = arrayOf(Pair("point", Point::class)),
            isAbstract = false, isOpenOrOverride = false, returnType = Point::class
        )
    }


}
