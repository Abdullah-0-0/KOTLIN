import info.but1.collections.NutArray
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** Generated JUnit/UMLChecker Test Cases for Groupe *****/

class TestUmlGroupe {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Groupe::class)
    }

    @Test
    fun `test0 - class Groupe is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Groupe is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Groupe' extends other classe(s)`() {
        uml.extend("ObjetGraphique")
    }

    @Test
    fun `test3 - 'Groupe' has a constructor`() {
        uml.constructorCheck(
            paramsAndTypes = arrayOf(
                Pair("objet1", ObjetGraphique::class),
                Pair("objet2", ObjetGraphique::class)
            )
        )
    }

    @Test
    fun `test4 - 'Groupe' has attribute(s)`() {
        uml.attributeNumber(2)
    }

    @Test
    fun `test5 - 'Groupe' has an attribute 'nbObjetsRegroupes'`() {
        uml.attributeCheck("nbObjetsRegroupes", Int::class, KVisibility.PUBLIC)
    }

    @Test
    fun `test6 - 'Groupe' has an attribute 'ObjetGraphiqueobjetsRegroupes'`() {
        uml.attributeCheck("objetsRegroupes", NutArray::class)
    }

    @Test
    fun `test7 - 'Groupe' has method(s)`() {
        uml.methodNumber(3)
    }

    @Test
    fun `test8 - 'Groupe' has a method 'regrouper'`() {
        uml.methodCheck(
            "regrouper",
            Boolean::class,
            methParamAndTypes = arrayOf(Pair("objetAjoute", ObjetGraphique::class))
        )
    }

    @Test
    fun `test9 - 'Groupe' has a method 'selectionner'`() {
        uml.methodCheck(
            "selectionner",
            methParamAndTypes = arrayOf(Pair("ok", Boolean::class)),
            isOpenOrOverride = true
        )
    }

    @Test
    fun `test9 - 'Groupe' has a method 'deplacer'`() {
        uml.methodCheck(
            "deplacer",
            methParamAndTypes = arrayOf(Pair("deplacement", Vecteur::class)),
            isOpenOrOverride = true
        )
    }


}
