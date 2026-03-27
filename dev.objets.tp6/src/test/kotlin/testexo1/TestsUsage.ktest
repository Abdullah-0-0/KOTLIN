package testexo1

import exo1.*
import info.but1.collections.NutArray
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.CsvSource


class TestsUsage {

    @Test
    fun equip_2str_bourse() {
        val equip = Equipement("Bourse")
        assertEquals("Bourse", equip.toString())
    }

    @Test
    fun equip_2str2_switch() {
        val equip = Equipement("Nintendo switch")
        assertEquals("Nintendo switch", equip.toString())
    }

    @Test
    fun arm_2str_epee() {
        val arme = Arme("Epée", 5, 4)
        assertEquals("Epée (5,4)", arme.toString())
    }

    @Test
    fun arm_2str_cobra() {
        val arme = Arme("Cobra'rayon Delta", 100, 1)
        assertEquals("Cobra'rayon Delta (100,1)", arme.toString())
    }


    @ParameterizedTest
    @CsvSource(
        "Accio,Attirer un objet vers soi",
        "Alohomora,Ouvrir une porte, une fenêtre verrouillée"
    )
    fun `sort_2str`(nom : String, desc: String) {
        val sort = Sort(nom, desc)
        assertEquals(nom+ " : " + desc, sort.toString())
    }

    @ParameterizedTest(name = "{index} => sort {0}")
    @CsvFileSource(files = ["resources/sortsHP.csv"], delimiterString = ";")
    fun `sort_2str2`(nom : String, desc: String) {
        val sort = Sort(nom, desc)
        assertEquals(nom+ " : " + desc, sort.toString())
    }

    @Test
    fun perso_listeCarac() {
        val perso = Personnage("Bidule", 4,5,6)
        assertEquals("FOR=4,AGI=5,INT=6", perso.listeCaracteristiques())
    }

    @Test
    fun perso_listeCarac_Conan() {
        val perso = Personnage("Conan", 9,5,1)
        assertEquals("FOR=9,AGI=5,INT=1", perso.listeCaracteristiques())
    }

    @Test
    fun mago_listeCarac_rincevent() {
        val perso = Magicien("Rincevent", 2, 8, 5, 1,
            Sort("In_ocatov", "provoque fin du monde"))
        assertEquals("FOR=2,AGI=8,INT=5,MAG=1", perso.listeCaracteristiques())
    }

    @Test
    fun perso_2str() {
        val perso = Personnage("Bidule", 4,5,6)
        assertEquals("Bidule <FOR=4,AGI=5,INT=6>", perso.toString())
    }

    @Test
    fun perso_2str_Conan() {
        val perso = Personnage("Conan", 9,5,1)
        assertEquals("Conan <FOR=9,AGI=5,INT=1>", perso.toString())
    }

    @Test
    fun mago_2str_rincevent() {
        val perso = Magicien("Rincevent", 2, 8, 5, 1,
            Sort("In Octavo", "provoque fin du monde"))
        assertEquals("Rincevent <FOR=2,AGI=8,INT=5,MAG=1>", perso.toString())
    }

    @Test
    fun perso_prend_epee() {
        val perso = Personnage("Conan", 9,5,1)
        assertEquals(0, perso.nbEquipementsPossedes)
        assertTrue(perso.prend(Arme("Epée", 5, 4)))
        assertEquals(1, perso.nbEquipementsPossedes)
    }

    @Test
    fun perso_prend_plusieursObjets() {
        val perso = Personnage("Conan", 9,5,1)
        assertEquals(0, perso.nbEquipementsPossedes)
        assertTrue(perso.prend(Arme("Epée", 5, 4)))
        assertTrue(perso.prend(Equipement("Bourse")))
        assertTrue(perso.prend(Equipement("Corde")))
        assertTrue(perso.prend(Arme("Bouclier", 2,4)))
        assertTrue(perso.prend(Arme("Dague", 3,3)))
        assertTrue(perso.prend(Equipement("Tente")))
        assertEquals(6, perso.nbEquipementsPossedes)
    }

    @Test
    fun mago_prend_plusieursObjets() {
        val perso = Magicien("Rincevent", 2, 7, 5, 1,
            Sort("In Octavo", "provoque fin du monde"))
        assertEquals(1, perso.nbEquipementsPossedes)
        assertEquals(1, perso.nbSortsConnus)
        assertTrue(perso.prend(Arme("Epée", 5, 4)))
        assertTrue(perso.prend(Equipement("Bourse")))
        assertTrue(perso.prend(Equipement("Corde")))
        assertTrue(perso.prend(Arme("Bouclier", 2,4)))
        assertTrue(perso.prend(Arme("Dague", 3,3)))
        assertEquals(6, perso.nbEquipementsPossedes)
    }

    @Test
    fun perso_prend_plusieursObjets_trop() {
        val perso = Personnage("Conan", 9,5,1)
        assertEquals(0, perso.nbEquipementsPossedes)
        assertTrue(perso.prend(Arme("Epée", 5, 4)))
        assertTrue(perso.prend(Equipement("Bourse")))
        assertTrue(perso.prend(Equipement("Corde")))
        assertTrue(perso.prend(Arme("Bouclier", 2,4)))
        assertTrue(perso.prend(Arme("Dague", 3,3)))
        assertTrue(perso.prend(Equipement("Tente")))
        assertFalse(perso.prend(Arme("Hache de guerre", 10,4)))
        assertEquals(6, perso.nbEquipementsPossedes)
    }

    @Test
    fun perso_prend_memeObjet() {
        val perso = Personnage("Conan", 9,5,1)
        assertEquals(0, perso.nbEquipementsPossedes)
        val corde = Equipement("Corde")
        assertTrue(perso.prend(Arme("Epée", 5, 4)))
        assertTrue(perso.prend(Equipement("Bourse")))
        assertTrue(perso.prend(corde))
        assertTrue(perso.prend(Arme("Bouclier", 2,4)))
        assertTrue(perso.prend(Arme("Dague", 3,3)))
        assertFalse(perso.prend(corde))
        assertTrue(perso.prend(Equipement("Tente")))
        assertEquals(6, perso.nbEquipementsPossedes)
    }


    @Test
    fun perso_attaque_epee() {
        val perso = Personnage("Conan", 9,5,1)
        val epee = Arme("Epée", 5, 4)
        val bouclier = Arme("Bouclier", 2,4)
        val dague =  Arme("Dague", 3,3)
        perso.prend(epee)
        perso.prend(Equipement("Bourse"))
        perso.prend(Equipement("Corde"))
        perso.prend(bouclier)
        perso.prend(dague)
        val victime = Personnage("Bidule", 4,5,6)
        assertTrue(perso.attaque(epee, victime))
    }

    @Test
    fun perso_attaque_bouclier() {
        val perso = Personnage("Conan", 9,5,1)
        val epee = Arme("Epée", 5, 4)
        val bouclier = Arme("Bouclier", 2,4)
        val dague =  Arme("Dague", 3,3)
        perso.prend(epee)
        perso.prend(Equipement("Bourse"))
        perso.prend(Equipement("Corde"))
        perso.prend(bouclier)
        perso.prend(dague)
        val victime = Personnage("Bidule", 4,5,6)
        assertTrue(perso.attaque(bouclier, victime))
    }

    @Test
    fun perso_attaque_dague() {
        val perso = Personnage("Conan", 9,5,1)
        val epee = Arme("Epée", 5, 4)
        val bouclier = Arme("Bouclier", 2,4)
        val dague =  Arme("Dague", 3,3)
        perso.prend(epee)
        perso.prend(Equipement("Bourse"))
        perso.prend(Equipement("Corde"))
        perso.prend(bouclier)
        perso.prend(dague)
        val victime = Personnage("Bidule", 4,5,6)
        assertTrue(perso.attaque(dague, victime))
    }

    @Test
    fun perso_attaque_hache() {
        val perso = Personnage("Conan", 9,5,1)
        val epee = Arme("Epée", 5, 4)
        val bouclier = Arme("Bouclier", 2,4)
        val dague =  Arme("Dague", 3,3)
        val hache = Arme("Hache de guerre", 10,5)
        perso.prend(epee)
        perso.prend(Equipement("Bourse"))
        perso.prend(Equipement("Corde"))
        perso.prend(bouclier)
        perso.prend(dague)
        val victime = Personnage("Bidule", 4,5,6)
        assertFalse(perso.attaque(hache, victime))
    }

    @Test
    fun perso_listeInventaire_vide() {
        val perso = Personnage("Conan", 9,5,1)
        assertEquals(0, perso.nbEquipementsPossedes)
        assertEquals("", perso.listeInventaire())
    }

    @Test
    fun perso_listeInventaire_epee() {
        val perso = Personnage("Conan", 9,5,1)
        assertEquals(0, perso.nbEquipementsPossedes)
        val epee = Arme("Epée", 5, 4)
        assertTrue(perso.prend(epee))
        assertEquals(1, perso.nbEquipementsPossedes)
        assertTrue(perso.lEquipementContient(epee))
        assertEquals("Epée (5,4) - ", perso.listeInventaire())
    }

    @Test
    fun perso_listeInventaire_plusieurs() {
        val perso = Personnage("Conan", 9,5,1)
        assertEquals(0, perso.nbEquipementsPossedes)

        val epee = Arme("Epée", 5, 4)
        val bourse = Equipement("Bourse")
        val corde = Equipement("Corde")
        val bouclier = Arme("Bouclier", 2,4)
        val dague =  Arme("Dague", 3,3)
        val tente  = Equipement("Tente")
        assertTrue(perso.prend(epee))
        assertTrue(perso.prend(bourse))
        assertTrue(perso.prend(corde))
        assertTrue(perso.prend(bouclier))
        assertTrue(perso.prend(dague))
        assertTrue(perso.prend(tente))
        assertEquals(6, perso.nbEquipementsPossedes)
        assertTrue(perso.lEquipementContient(epee))
        assertTrue(perso.lEquipementContient(bourse))
        assertTrue(perso.lEquipementContient(corde))
        assertTrue(perso.lEquipementContient(bouclier))
        assertTrue(perso.lEquipementContient(dague))
        assertEquals("Epée (5,4) - Bourse - Corde - Bouclier (2,4) - Dague (3,3) - Tente - ", perso.listeInventaire())
    }

    @Test
    fun mago_listeInventaireInit_contientGrimoire() {
        val sortInitial = Sort("In Octavo", "provoque fin du monde")
        val perso = Magicien("Rincevent", 2, 8, 5, 1,
            sortInitial)
        assertEquals(1, perso.nbEquipementsPossedes)
        assertEquals("Grimoire - ", perso.listeInventaire())
    }

    @Test
    fun magoInit_grimoireSortInit() {
        val sortInitial = Sort("In Octavo", "provoque fin du monde")
        val perso = Magicien("Rincevent", 2, 8, 5, 1,
            sortInitial)
        assertTrue(perso.leGrimoireContient(sortInitial))
    }

    @Test
    fun mago_listeInventaire_epee() {
        val perso = Magicien("Rincevent", 2, 7, 5, 1,
            Sort("In Octavo", "provoque fin du monde"))
        assertEquals(1, perso.nbEquipementsPossedes)
        val arme = Arme("Epée", 5, 4)
        assertTrue(perso.prend(arme))
        assertEquals(2, perso.nbEquipementsPossedes)
        assertEquals("Grimoire - Epée (5,4) - ", perso.listeInventaire())
        assertTrue(perso.lEquipementContient(arme))
    }

    @Test
    fun mago_listeInventaire_plusieurs() {
        val perso = Magicien("Rincevent", 2, 7, 5, 1,
            Sort("In Octavo", "provoque fin du monde"))
        assertEquals(1, perso.nbEquipementsPossedes)
        val epee = Arme("Epée", 5, 4)
        val bourse = Equipement("Bourse")
        val corde = Equipement("Corde")
        val bouclier = Arme("Bouclier", 2,4)
        val dague =  Arme("Dague", 3,3)
        val tente  = Equipement("tente")
        assertTrue(perso.prend(epee))
        assertTrue(perso.prend(bourse))
        assertTrue(perso.prend(corde))
        assertTrue(perso.prend(bouclier))
        assertTrue(perso.prend(dague))
        assertFalse(perso.prend(tente))
        assertEquals(6, perso.nbEquipementsPossedes)
        assertTrue(perso.lEquipementContient(epee))
        assertTrue(perso.lEquipementContient(bourse))
        assertTrue(perso.lEquipementContient(corde))
        assertTrue(perso.lEquipementContient(bouclier))
        assertTrue(perso.lEquipementContient(dague))
        assertFalse(perso.lEquipementContient(tente))
    }


    @ParameterizedTest
    @CsvSource(
        "Enervatum, Réanimer quelqu’un",
        "Silencio, Faire taire quelqu’un",
        "Accio, Attirer un objet vers soi",
        "Expecto patronum, Faire apparaître un Patronus",
        "Stupéfix, Endormir quelqu’un",
        "Wingardium leviosa, Lever un objet",
        "Expelliarmus, Désarmer un adversaire",
        "Crac badaboum, Faire craquer coutures et liens",
    )
    fun `lance_sortInconnu`(nom : String, desc: String) {
        val sort = Sort(nom, desc)
        val luna = Magicien(
            "Luna Lovegood", 4, 4, 7, 5,
            Sort("Lumos", "Illuminer le bout de la baguette")
        )
        assertFalse(luna.leGrimoireContient(sort))
        assertFalse(luna.lance(sort))
    }

    @Test
    fun luna_apprend() {
        val luna = Magicien(
            "Luna Lovegood", 4, 4, 7, 5,
            Sort("Lumos", "Illuminer le bout de la baguette")
        )
        assertEquals(1, luna.nbSortsConnus)
        assertTrue(luna.apprend(Sort("Enervatum", "Réanimer quelqu’un")))
        assertTrue(luna.apprend(Sort("Silencio", "Faire taire quelqu’un")))
        assertTrue(luna.apprend(Sort("Accio", "Attirer un objet vers soi")))
        assertTrue(luna.apprend(Sort("Expecto patronum", "Faire apparaître un Patronus")))
        assertTrue(luna.apprend(Sort("Stupéfix", "Endormir quelqu’un")))
        assertTrue(luna.apprend(Sort("Wingardium leviosa", "Lever un objet, sortilège de lévitation")))
        assertTrue(luna.apprend(Sort("Expelliarmus", "Désarmer un adversaire")))
        assertTrue(luna.apprend(Sort("Crac badaboum", "Faire craquer coutures et liens")))
        assertTrue(luna.apprend(Sort("Oubliettes", "Faire tout oublier à quelqu’un")))
        assertEquals(10, luna.nbSortsConnus)
    }

    @ParameterizedTest
    @CsvSource(
        "Lumos, Illuminer le bout de la baguette",
        "Enervatum, Réanimer quelqu’un",
        "Silencio, Faire taire quelqu’un",
        "Accio, Attirer un objet vers soi",
        "Expecto patronum, Faire apparaître un Patronus",
        "Stupéfix, Endormir quelqu’un",
        "Wingardium leviosa, Lever un objet",
        "Expelliarmus, Désarmer un adversaire",
        "Crac badaboum, Faire craquer coutures et liens",
    )
    fun luna_apprend_2fois(nom : String, desc: String) {
        val luna = Magicien(
            "Luna Lovegood", 4, 4, 7, 5,
            Sort("Lumos", "Illuminer le bout de la baguette")
        )
        val sort = Sort(nom, desc)
        assertEquals(1, luna.nbSortsConnus)
        assertTrue(luna.apprend(Sort("Enervatum", "Réanimer quelqu’un")))
        assertTrue(luna.apprend(Sort("Silencio", "Faire taire quelqu’un")))
        assertTrue(luna.apprend(Sort("Accio", "Attirer un objet vers soi")))
        assertTrue(luna.apprend(Sort("Expecto patronum", "Faire apparaître un Patronus")))
        assertTrue(luna.apprend(Sort("Stupéfix", "Endormir quelqu’un")))
        assertTrue(luna.apprend(Sort("Wingardium leviosa", "Lever un objet")))
        assertTrue(luna.apprend(Sort("Expelliarmus", "Désarmer un adversaire")))
        assertTrue(luna.apprend(Sort("Crac badaboum", "Faire craquer coutures et liens")))
        assertFalse(luna.apprend(sort))
        assertEquals(9, luna.nbSortsConnus)
    }

    @ParameterizedTest
    @CsvSource(
        "Lumos, Illuminer le bout de la baguette",
        "Enervatum, Réanimer quelqu’un",
        "Silencio, Faire taire quelqu’un",
        "Accio, Attirer un objet vers soi",
        "Expecto patronum, Faire apparaître un Patronus",
        "Stupéfix, Endormir quelqu’un",
        "Wingardium leviosa, Lever un objet",
        "Expelliarmus, Désarmer un adversaire",
        "Crac badaboum, Faire craquer coutures et liens",
    )
    fun luna_lance(nom : String, desc: String) {
        val luna = Magicien(
            "Luna Lovegood", 4, 4, 7, 5,
            Sort("Lumos", "Illuminer le bout de la baguette")
        )
        val sort = Sort(nom, desc)
        assertEquals(1, luna.nbSortsConnus)
        assertTrue(luna.apprend(Sort("Enervatum", "Réanimer quelqu’un")))
        assertTrue(luna.apprend(Sort("Silencio", "Faire taire quelqu’un")))
        assertTrue(luna.apprend(Sort("Accio", "Attirer un objet vers soi")))
        assertTrue(luna.apprend(Sort("Expecto patronum", "Faire apparaître un Patronus")))
        assertTrue(luna.apprend(Sort("Stupéfix", "Endormir quelqu’un")))
        assertTrue(luna.apprend(Sort("Wingardium leviosa", "Lever un objet")))
        assertTrue(luna.apprend(Sort("Expelliarmus", "Désarmer un adversaire")))
        assertTrue(luna.apprend(Sort("Crac badaboum", "Faire craquer coutures et liens")))
        assertTrue(luna.lance(sort))
        assertEquals(9, luna.nbSortsConnus)
    }

    @Test
    fun luna_lance_avada_kedevara() {
        val luna = Magicien(
            "Luna Lovegood", 4, 4, 7, 5,
            Sort("Lumos", "Illuminer le bout de la baguette")
        )
        assertEquals(1, luna.nbSortsConnus)
        assertTrue(luna.apprend(Sort("Enervatum", "Réanimer quelqu’un")))
        assertTrue(luna.apprend(Sort("Silencio", "Faire taire quelqu’un")))
        assertTrue(luna.apprend(Sort("Accio", "Attirer un objet vers soi")))
        assertTrue(luna.apprend(Sort("Expecto patronum", "Faire apparaître un Patronus")))
        assertTrue(luna.apprend(Sort("Stupéfix", "Endormir quelqu’un")))
        assertTrue(luna.apprend(Sort("Wingardium leviosa", "Lever un objet")))
        assertTrue(luna.apprend(Sort("Expelliarmus", "Désarmer un adversaire")))
        assertTrue(luna.apprend(Sort("Crac badaboum", "Faire craquer coutures et liens")))
        assertFalse(luna.lance(Sort("Avada kedavra", "Tuer quelqu’un (sortilège impardonnable")))
        assertEquals(9, luna.nbSortsConnus)
    }

    @Test
    fun luna_listeDesSorts1() {
        val luna = Magicien(
            "Luna Lovegood", 4, 4, 7, 5,
            Sort("Lumos", "Illuminer le bout de la baguette")
        )
        val listeAttendue = arrayOf(Sort("Lumos", "Illuminer le bout de la baguette"))
        val listeResultat = luna.listeDesSorts()
        assertEquals(1, listeResultat.size)
        assertIterableEquals(listeAttendue.asList(), listeResultat.asList())
    }

    @Test
    fun luna_listeDesSorts5() {
        val luna = Magicien(
            "Luna Lovegood", 4, 4, 7, 5,
            Sort("Lumos", "Illuminer le bout de la baguette")
        )
        assertTrue(luna.apprend(Sort("Enervatum", "Réanimer quelqu’un")))
        assertTrue(luna.apprend(Sort("Silencio", "Faire taire quelqu’un")))
        assertTrue(luna.apprend(Sort("Accio", "Attirer un objet vers soi")))
        assertTrue(luna.apprend(Sort("Expecto patronum", "Faire apparaître un Patronus")))

        val listeAttendue = arrayOf(
            Sort("Lumos", "Illuminer le bout de la baguette"),
            Sort("Enervatum", "Réanimer quelqu’un"),
            Sort("Silencio", "Faire taire quelqu’un"),
            Sort("Accio", "Attirer un objet vers soi"),
            Sort("Expecto patronum", "Faire apparaître un Patronus")
        )
        val listeResultat = luna.listeDesSorts()
        assertEquals(5, listeResultat.size)
        assertIterableEquals(listeAttendue.asList(), listeResultat.asList())
    }
}

fun <N> NutArray<N>.asList() : List<N> {
    val res = mutableListOf<N>()
    for (i in 0 until size) {
        res.add(get(i))
    }
    return res
}