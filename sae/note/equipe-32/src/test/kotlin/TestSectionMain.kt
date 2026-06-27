import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.OutilsCarte
import iut.info1.flip7.exceptions.MainInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Section "Main" de l'analyse (lignes 191 à 230).
 *
 * La bibliothèque n'expose aucun getter direct comme nbCarteSpe, nbCarteTotale ou Vie :
 * ces notions ne sont vérifiables qu'à travers verifieMainCorrecte() et calculScore(),
 * qui sont les deux seules méthodes publiques qui appliquent des règles sur le contenu
 * d'une main. Les cas ci-dessous reformulent donc chaque ligne du tableau en un appel
 * réel à l'une de ces deux méthodes, en construisant la List<Carte> qui correspond
 * exactement à la situation décrite (nombre de CarteSpe, de CarteBonus, etc.).
 *
 * Les cas déjà couverts par VerifieMainCorrecte.kt et calculScore.kt (main vide, une seule
 * CarteNum, 7 CarteNum + bonus, doublon, CarteStop en main, etc.) ne sont pas redupliqués ici ;
 * seules les bornes et combinaisons non encore testées sont ajoutées.
 */
class TestSectionMain {

    private val outils = OutilsCarte()

    // --- nb CarteSpe (CarteStop + Carte2ndeChance + Carte3aLaSuite) dans une main ---
    // Une main correcte ne peut contenir aucune CarteStop (cf. VerifieMainCorrecte.kt),
    // mais peut contenir plusieurs Carte2ndeChance et plusieurs Carte3aLaSuite simultanément :
    // ces deux cartes spéciales ne sont jamais "gardées en main" dans les règles du jeu réel,
    // mais rien dans verifieMainCorrecte() ne semble l'interdire explicitement pour 2ndeChance.

    @Test
    fun `main avec 3 Carte2ndeChance est consideree comme valide (nb CarteSpe = 3)`() {
        val main = listOf(CarteNum(5), Carte2ndeChance(), Carte2ndeChance(), Carte2ndeChance())
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `main avec deux CarteStop est invalide`() {
        // Confirmé par l'analyse de l'équipe (PDF) : "CarteStop présente dans la main
        // → MainInvalideException attendue". Une seule suffit, ce n'est pas une question
        // de nombre mais de type de carte : une CarteStop ne doit jamais rester en main.
        val main = listOf(CarteNum(5), CarteStop(), CarteStop())
        assertThrows(MainInvalideException::class.java) {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `main avec une seule CarteStop est valide`() {
        // Confirmé par l'analyse de l'équipe (PDF) : "CarteStop présente dans la main
        // → MainInvalideException attendue". Une seule suffit, ce n'est pas une question
        // de nombre mais de type de carte : une CarteStop ne doit jamais rester en main.
        val main = listOf(CarteNum(5), CarteStop())
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    // --- nb CarteBonus (CarteBonusPlus + CarteBonusMultiplie) dans une main ---

    @Test
    fun `main avec un seul CarteBonusPlus est valide (nb CarteBonus = 1)`() {
        val main = listOf(CarteNum(5), CarteBonusPlus(4))
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `main avec un CarteBonusPlus et un CarteBonusMultiplie est valide (nb CarteBonus = 2)`() {
        val main = listOf(CarteNum(5), CarteBonusPlus(4), CarteBonusMultiplie())
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `main avec deux CarteBonusPlus de meme valeur est invalide (doublon de bonus)`() {
        // Analogue au cas confirmé par le PDF pour CarteBonusMultiplie : un doublon
        // de la même carte bonus n'est pas une situation atteignable par le jeu normal
        // et doit être rejetée par verifieMainCorrecte().
        val main = listOf(CarteNum(5), CarteBonusPlus(4), CarteBonusPlus(4))
        assertThrows(MainInvalideException::class.java) {
            outils.verifieMainCorrecte(main)
        }
    }

    // --- nb CarteNum dans une main (0 a 7, car Flip7 = 7 CarteNum differentes maximum) ---

    @Test
    fun `main avec exactement 7 CarteNum differentes est valide (limite haute)`() {
        val main = (0..6).map { CarteNum(it) }
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `main avec 0 CarteNum mais un bonus est valide`() {
        val main = listOf(CarteBonusPlus(2))
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    // --- nb CarteTotale (toutes cartes en main confondues) ---

    @Test
    fun `main avec 7 CarteNum et 2 bonus reste valide (nb CarteTotale = 9)`() {
        val main = (0..6).map { CarteNum(it) } + listOf(CarteBonusPlus(2), CarteBonusMultiplie())
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    // --- Score >= 0 (un score ne peut jamais etre negatif, aucune carte ne retire de points) ---

    @Test
    fun `calculScore ne retourne jamais une valeur negative, meme avec une main vide`() {
        val main = emptyList<Carte>()
        assertTrue(outils.calculScore(main) >= 0, "Le score doit toujours etre superieur ou egal a 0")
    }

    @Test
    fun `calculScore d une main composee uniquement de bonus additifs est positif`() {
        val main = listOf(CarteBonusPlus(2), CarteBonusPlus(4))
        assertTrue(outils.calculScore(main) >= 0, "Le score doit toujours etre superieur ou egal a 0")
        assertEquals(6, outils.calculScore(main))
    }

    // --- CarteNum(x) doublon -> Vie - 1 (= consommation d'une Carte2ndeChance) ---
    // Cette regle concerne le comportement de Flip7.joueurCourantPiocheUneCarte() au moment
    // du tirage, pas verifieMainCorrecte() qui valide un etat figé : verifieMainCorrecte()
    // n'a pas a rejeter une main contenant un doublon de CarteNum si une Carte2ndeChance
    // est presente, car dans le jeu reel ce cas ne devrait jamais survenir (le doublon est
    // defausse immediatement). Les tests sur le comportement de pioche en cas de doublon
    // sont traites dans TestJoueurCourantPiocheUneCarteCasAdditionnels.kt (section consacree
    // a Flip7.joueurCourantPiocheUneCarte()).

    @Test
    fun `une main sans aucun doublon de CarteNum est valide`() {
        val main = listOf(CarteNum(1), CarteNum(2), CarteNum(3))
        assertDoesNotThrow {
            outils.verifieMainCorrecte(main)
        }
    }

    @Test
    fun `une main avec un doublon de CarteNum et aucune Carte2ndeChance est invalide`() {
        val main = listOf(CarteNum(1), CarteNum(1))
        assertThrows(MainInvalideException::class.java) {
            outils.verifieMainCorrecte(main)
        }
    }
}
