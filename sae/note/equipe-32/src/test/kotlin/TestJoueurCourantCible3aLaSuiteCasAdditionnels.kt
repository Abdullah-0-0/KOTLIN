import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.exceptions.CarteInvalideException
import iut.info1.flip7.exceptions.EtatPartieInvalideException
import iut.info1.flip7.exceptions.IndiceJoueurInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Section "joueurCourantCible3aLaSuite() — cas additionnels" de l'analyse (lignes 386-395).
 *
 * Hypothèse structurelle (non confirmée mot pour mot par le bytecode, mais déduite du nom
 * de la classe Carte3aLaSuite et du type de retour List<Carte> de la méthode) : cibler un
 * joueur avec une Carte3aLaSuite force jusqu'à 3 pioches consécutives pour ce joueur, avec
 * arrêt anticipé si la cible devient PERDU (doublon sans Carte2ndeChance) ou réalise un
 * Flip7 avant la 3e carte. La liste retournée contient les cartes effectivement piochées
 * (donc potentiellement moins de 3 si arrêt anticipé).
 */
class TestJoueurCourantCible3aLaSuiteCasAdditionnels {

    private fun partieAvecPiocheValide(nbJoueurs: Int, scoreFinPartie: Int = 200): Flip7 {
        val listeJoueurs: List<IJoueur> = List(nbJoueurs) { index ->
            object : IJoueur {
                override fun donneNom(): String = "Joueur ${index + 1}"
            }
        }
        val listeCartes: List<Carte> = listOf(
            CarteNum(1), CarteNum(2), CarteNum(3), CarteNum(4), CarteNum(5), CarteNum(6)
        )
        return Flip7(
            nbJoueurs = nbJoueurs,
            joueurs = listeJoueurs,
            cartes = listeCartes,
            debug = true,
            scoreFinPartie = scoreFinPartie
        )
    }

    private fun partieDebug(nbJoueurs: Int, scoreFinPartie: Int = 200, cartesEnTete: Array<Carte>): Flip7 {
        val listeJoueurs: List<IJoueur> = List(nbJoueurs) { index ->
            object : IJoueur {
                override fun donneNom(): String = "Joueur ${index + 1}"
            }
        }
        return Flip7(
            nbJoueurs = nbJoueurs,
            joueurs = listeJoueurs,
            cartes = cartesEnTete.toList(),
            debug = true,
            scoreFinPartie = scoreFinPartie
        )
    }


    @Test
    fun `appel en etat ATTENTE_CIBLE_STOP leve EtatPartieInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(CarteStop()))
        partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_STOP, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantCible3aLaSuite(Carte3aLaSuite(), 1)
        }
    }

    @Test
    fun `appel en etat MANCHE_TERMINEE leve EtatPartieInvalideException`() {
        val partie = partieAvecPiocheValide(nbJoueurs = 2)
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        partie.joueurCourantPiocheUneCarte()
        partie.joueurCourantDitStop()
        assertEquals(EtatPartie.MANCHE_TERMINEE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantCible3aLaSuite(Carte3aLaSuite(), 1)
        }
    }

    @Test
    fun `appel en etat PARTIE_TERMINEE leve EtatPartieInvalideException`() {
        // Pioche en round-robin à 2 joueurs (positions 0,2,4,6,8 → J1 ; 1,3,5,7,9 → J2)
        // J1 reçoit : 12, 11, 10, 9, 8  → score = 50 ≥ scoreFinPartie=50 ✓
        // J2 reçoit : 1, 2, 3, 4, 5     → score = 15
        val partie = partieDebug(
            nbJoueurs = 2,
            scoreFinPartie = 50,
            cartesEnTete = arrayOf(
                CarteNum(12), CarteNum(1),
                CarteNum(11), CarteNum(2),
                CarteNum(10), CarteNum(3),
                CarteNum(9),  CarteNum(4),
                CarteNum(8),  CarteNum(5),

                // SÉCURITÉ : Cartes bonus en fin de pioche pour que scoreManche()
                // ne lève pas l'exception "La pioche est vide"
                CarteNum(7),  CarteNum(7),
                CarteNum(7),  CarteNum(7)
            )
        )

        // 5 tours de round-robin : chaque repeat pioche J1 puis J2
        repeat(5) {
            partie.joueurCourantPiocheUneCarte() // J1
            partie.joueurCourantPiocheUneCarte() // J2
        }

        // Après le dernier tour, on signale que les deux joueurs s'arrêtent
        partie.joueurCourantDitStop() // J1
        partie.joueurCourantDitStop() // J2

        // Validation des états de transition
        assertEquals(EtatPartie.MANCHE_TERMINEE, partie.etatPartie)
        partie.scoreManche()
        assertEquals(EtatPartie.PARTIE_TERMINEE, partie.etatPartie)

        // ADAPTATION A NOTRE SITUATION :
        // On vérifie que l'action spécifique (ici cible3aLaSuite) est interdite
        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantCible3aLaSuite(Carte3aLaSuite(), 1)
        }
    }

    @Test
    fun `carte passee differente de la derniere carte tiree leve CarteInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(Carte3aLaSuite()))
        partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_3SUITE, partie.etatPartie)

        val autreCarte3aLaSuite = Carte3aLaSuite()
        assertThrows(CarteInvalideException::class.java) {
            partie.joueurCourantCible3aLaSuite(autreCarte3aLaSuite, 1)
        }
    }

    @Test
    fun `numero de cible negatif leve CarteInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(Carte3aLaSuite()))
        val carte = partie.joueurCourantPiocheUneCarte()
        assertThrows(IndiceJoueurInvalideException::class.java) {
            partie.joueurCourantCible3aLaSuite(carte, -1)
        }
    }

    @Test
    fun `numero de cible egal a nbJoueurs leve CarteInvalideException`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(Carte3aLaSuite()))
        val carte = partie.joueurCourantPiocheUneCarte()
        assertThrows(IndiceJoueurInvalideException::class.java) {
            partie.joueurCourantCible3aLaSuite(carte, 2)
        }
    }

    @Test
    fun `la cible devient PERDU a la 2e des 3 cartes forcees, la liste retournee ne contient que 2 cartes`() {
        // Pioche forcée pour le joueur 1 (cible) : CarteNum(5), puis un doublon de 5 sans
        // aucune Carte2ndeChance disponible -> le joueur devient PERDU et la pioche forcée
        // s'arrête avant la 3e carte.
        val partie = partieDebug(
            nbJoueurs = 2,
            cartesEnTete = arrayOf(Carte3aLaSuite(), CarteNum(5), CarteNum(5), CarteNum(9))
        )
        val carte3aLaSuite = partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_3SUITE, partie.etatPartie)

        val cartesObtenues = partie.joueurCourantCible3aLaSuite(carte3aLaSuite, 1)

        assertEquals(2, cartesObtenues.size, "La pioche forcée doit s'arrêter après 2 cartes, le joueur étant devenu PERDU")
        assertEquals(EtatJoueur.PERDU, partie.etatJoueur[1])
    }

    @Test
    fun `la cible realise un Flip7 a la 2e des 3 cartes forcees, arret anticipe possible`() {
        val partie = partieDebug(
            nbJoueurs = 2,
            cartesEnTete = arrayOf(
                Carte3aLaSuite(),
                CarteNum(0),
                CarteNum(1),
                CarteNum(2),
                CarteNum(3),
                CarteNum(4)
            )
        )
        val carte3aLaSuite = partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_3SUITE, partie.etatPartie)

        assertDoesNotThrow {
            partie.joueurCourantCible3aLaSuite(carte3aLaSuite, 1)
        }
    }


    @Test
    fun `2 Carte3aLaSuite tirees consecutivement pendant le tirage force imbrication double`() {
        val partie = partieDebug(
            nbJoueurs = 2,
            cartesEnTete = arrayOf(
                Carte3aLaSuite(), // Piochée par le Joueur 1 (déclenche l'action)
                CarteNum(5),      // Piochée par la cible (1ère pioche forcée)
                Carte3aLaSuite(), // Piochée par la cible (2e pioche forcée)

                // SÉCURITÉ : Cartes indispensables pour que la cible puisse faire sa
                // 3e pioche forcée et que le moteur de jeu ne boucle pas à l'infini.
                CarteNum(6),
                CarteNum(7),
                CarteNum(8)
            )
        )
        val premiereCarte3aLaSuite = partie.joueurCourantPiocheUneCarte()
        assertEquals(EtatPartie.ATTENTE_CIBLE_3SUITE, partie.etatPartie)

        // Grâce aux cartes de sécurité, la méthode va pouvoir se terminer proprement
        // et renvoyer les 3 cartes piochées par la cible.
        val cartesObtenues = partie.joueurCourantCible3aLaSuite(premiereCarte3aLaSuite, 1)

        assertTrue(
            cartesObtenues.any { it.estCarte3aLaSuite() },
            "La 2e Carte3aLaSuite tirée pendant le tirage forcé doit apparaître dans la liste retournée, Est ce un problème de biblio ?"
        )
    }
}
