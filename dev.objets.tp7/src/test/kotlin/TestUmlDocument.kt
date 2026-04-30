import info.but1.collections.NutArray
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility


class TestUmlDocument {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Document::class)
    }

    @Test
    fun `test0 - class Document is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class Document is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Document' extends other classe(s)`() {
        uml.extend("IEditable")
    }

    @Test
    fun `test3 - 'Document' has a constructor`() {
        uml.constructorCheck(paramsAndTypes = arrayOf(Pair("nom", String::class)))
    }

    @Test
    fun `test4 - 'Document' has attribute(s)`() {
        uml.attributeNumber(3)
    }

    @Test
    fun `test5 - 'Document' has an attribute 'nomDeFichier'`() {
        uml.attributeCheck("nomDeFichier", String::class)
    }

    @Test
    fun `test6 - 'Document' has an attribute 'compteurFeuilles'`() {
        uml.attributeCheck("compteurFeuilles", Int::class, KVisibility.PUBLIC)
    }

    @Test
    fun `test7 - 'Document' has an attribute 'feuilles'`() {
        uml.attributeCheck("feuilles", NutArray::class)
    }

    @Test
    fun `test8 - 'Document' has method(s)`() {
        uml.methodNumber(3)
    }

    @Test
    fun `test9 - 'Document' has a method 'editer'`() {
        uml.methodCheck("editer", methParamAndTypes = arrayOf(Pair("nouveau", String::class)), isOpenOrOverride = true)
    }

    @Test
    fun `test10 - 'Document' has a method 'nouvelleFeuille'`() {
        uml.methodCheck(
            "nouvelleFeuille",
            Boolean::class,
            methParamAndTypes = arrayOf(Pair("hauteur", Int::class), Pair("largeur", Int::class))
        )
    }

    @Test
    fun `test11 - 'Document' has a method 'donneFeuille'`() {
        uml.methodCheck("donneFeuille", Feuille::class, methParamAndTypes = arrayOf(Pair("position", Int::class)))
    }


}
