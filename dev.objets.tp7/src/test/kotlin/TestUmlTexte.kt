import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for Texte *****/

class TestUmlTexte {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Texte::class)
    }

    @Test
    fun `test0 - class Texte is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Texte is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Texte' extends other classe(s)`() {
        uml.extend("ObjetDessinable", "IEditable")
    }

    @Test
    fun `test3 - 'Texte' has a constructor`() {
        uml.constructorCheck(
            paramsAndTypes = arrayOf(
                Pair("x", Int::class),
                Pair("y", Int::class),
                Pair("valeur", String::class)
            )
        )
    }

    @Test
    fun `test4 - 'Texte' has attribute(s)`() {
        uml.attributeNumber(3)
    }

    @Test
    fun `test5 - 'Texte' has an attribute 'valeur'`() {
        uml.attributeCheck("valeur", String::class)
    }

    @Test
    fun `test6 - 'Texte' has an attribute 'police'`() {
        uml.attributeCheck("police", String::class)
    }

    @Test
    fun `test7 - 'Texte' has an attribute 'taille'`() {
        uml.attributeCheck("taille", Int::class)
    }

    @Test
    fun `test8 - 'Texte' has method(s)`() {
        uml.methodNumber(2)
    }

    @Test
    fun `test9 - 'Texte' has a method 'editer'`() {
        uml.methodCheck("editer", methParamAndTypes = arrayOf(Pair("nouveau", String::class)), isOpenOrOverride = true)
    }

    @Test
    fun `test10 - 'Texte' has a method 'dessiner'`() {
        uml.methodCheck("dessiner", String::class, isOpenOrOverride = true)
    }


}
