import info.but1.collections.NutArray
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** Generated JUnit/UMLChecker Test Cases for Feuille *****/

class TestUmlFeuille {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Feuille::class)
    }

    @Test
    fun `test0 - class Feuille is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Feuille is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Feuille' extends other classe(s)`() {
        uml.extendNothing()
    }

    @Test
    fun `test3 - 'Feuille' has a constructor`() {
        uml.constructorCheck(paramsAndTypes = arrayOf(Pair("h", Int::class), Pair("l", Int::class)))
    }

    @Test
    fun `test4 - 'Feuille' has attribute(s)`() {
        uml.attributeNumber(4)
    }

    @Test
    fun `test5 - 'Feuille' has an attribute 'hauteur'`() {
        uml.attributeCheck("hauteur", Int::class)
    }

    @Test
    fun `test6 - 'Feuille' has an attribute 'largeur'`() {
        uml.attributeCheck("largeur", Int::class)
    }

    @Test
    fun `test7 - 'Feuille' has an attribute 'compteurObjets'`() {
        uml.attributeCheck("compteurObjets", Int::class, KVisibility.PUBLIC)
    }

    @Test
    fun `test8 - 'Feuille' has an attribute 'objets'`() {
        uml.attributeCheck("objets", NutArray::class)
    }

    @Test
    fun `test9 - 'Feuille' has method(s)`() {
        uml.methodNumber(1)
    }

    @Test
    fun `test10 - 'Feuille' has a method 'inserer'`() {
        uml.methodCheck("inserer", methParamAndTypes = arrayOf(Pair("nouvelObjet", ObjetGraphique::class)))
    }


}
