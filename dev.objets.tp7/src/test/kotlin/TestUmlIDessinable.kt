import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for Coloriable *****/

class TestUmlIDessinable {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(IDessinable::class)
    }

    @Test
    fun `test0 - Dessinable is abstract or not`() {
        uml.isAbstract(true)
    }

    @Test
    fun `test1 - Dessinable is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Dessinable' extends other classe(s)`() {
        uml.extendNothing()
    }

    @Test
    fun `test3 - 'Dessinable' has attribute(s)`() {
        uml.attributeNumber(0)
    }

    @Test
    fun `test4 - 'Dessinable' has method(s)`() {
        uml.methodNumber(3)
    }

    @Test
    fun `test5 - 'Dessinable' has a method 'colorierPremier'`() {
        uml.methodCheck(
            "colorierPremier",
            methParamAndTypes = arrayOf(Pair("nouvelleCouleur", String::class)),
            isAbstract = true
        )
    }

    @Test
    fun `test6 - 'Dessinable' has a method 'colorierArriere'`() {
        uml.methodCheck(
            "colorierArriere",
            methParamAndTypes = arrayOf(Pair("nouvelleCouleur", String::class)),
            isAbstract = true
        )
    }

    @Test
    fun `test6 - 'Dessinable' has a method 'dessiner'`() {
        uml.methodCheck("dessiner", String::class, isAbstract = true)
    }

}
