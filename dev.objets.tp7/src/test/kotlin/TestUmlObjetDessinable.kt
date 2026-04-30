import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** Generated JUnit/UMLChecker Test Cases for ObjetDessinable *****/

class TestUmlObjetDessinable {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(ObjetDessinable::class)
    }

    @Test
    fun `test0 - class ObjetDessinable is abstract or not`() {
        uml.isAbstract(true)
    }

    @Test
    fun `test1 - class ObjetDessinable is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'ObjetDessinable' extends other classe(s)`() {
        uml.extend("ObjetGraphique", "IDessinable")
    }

    @Test
    fun `test3 - 'ObjetDessinable' has a constructor`() {
        uml.constructorCheck(paramsAndTypes = arrayOf(Pair("x", Int::class), Pair("y", Int::class)))
    }

    @Test
    fun `test4 - 'ObjetDessinable' has attribute(s)`() {
        uml.attributeNumber(4)
    }

    @Test
    fun `test5 - 'ObjetDessinable' has an attribute 'selectionne'`() {
        uml.attributeCheck("estSelectionne", Boolean::class)
    }

    @Test
    fun `test6 - 'ObjetDessinable' has an attribute 'x'`() {
        uml.attributeCheck("position", Point::class)
    }


    @Test
    fun `test8 - 'ObjetDessinable' has an attribute 'couleurPremierPlan'`() {
        uml.attributeCheck("couleurPremierPlan", String::class)
    }

    @Test
    fun `test9 - 'ObjetDessinable' has an attribute 'couleurArrierePlan'`() {
        uml.attributeCheck("couleurArrierePlan", String::class)
    }

    @Test
    fun `test10 - 'ObjetDessinable' has method(s)`() {
        uml.methodNumber(5)
    }

    @Test
    fun `test11 - 'ObjetDessinable' has a method 'selectionner'`() {
        uml.methodCheck(
            "selectionner",
            methParamAndTypes = arrayOf(Pair("ok", Boolean::class)),
            isOpenOrOverride = true
        )
    }

    @Test
    fun `test12 - 'ObjetDessinable' has a method 'colorierPremier'`() {
        uml.methodCheck(
            "colorierPremier",
            methParamAndTypes = arrayOf(Pair("nouvelleCouleur", String::class)),
            isOpenOrOverride = true
        )
    }

    @Test
    fun `test13 - 'ObjetDessinable' has a method 'colorierArriere'`() {
        uml.methodCheck(
            "colorierArriere",
            methParamAndTypes = arrayOf(Pair("nouvelleCouleur", String::class)),
            isOpenOrOverride = true
        )
    }

    @Test
    fun `test14 - 'ObjetDessinable' has a method 'deplacer'`() {
        uml.methodCheck(
            "deplacer",
            methParamAndTypes = arrayOf(Pair("deplacement", Vecteur::class)),
            isOpenOrOverride = true
        )
    }

    @Test
    fun `test15 - 'ObjetDessinable' has a method 'dessiner'`() {
        uml.methodCheck("dessiner", String::class, isOpenOrOverride = true)
    }


}
