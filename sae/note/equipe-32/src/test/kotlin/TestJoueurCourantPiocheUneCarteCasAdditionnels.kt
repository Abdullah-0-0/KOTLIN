import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.*
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.exceptions.EtatPartieInvalideException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestJoueurCourantPiocheUneCarteCasAdditionnels {

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

    // Crée une partie en mode debug avec une pioche arbitraire (tout type de Carte).
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
    fun `appel en etat NOUVELLE_MANCHE leve EtatPartieInvalideException`() {
        val partie = partieAvecPiocheValide(nbJoueurs = 2, scoreFinPartie = 200)
        // Round-robin : J1 pioche, tour passe à J2, J2 pioche, tour passe à J1
        partie.joueurCourantPiocheUneCarte() // J1
        partie.joueurCourantPiocheUneCarte() // J2
        partie.joueurCourantDitStop()        // J2 (c'est lui le courant après sa pioche)
        partie.joueurCourantDitStop()        // J1
        assertEquals(EtatPartie.MANCHE_TERMINEE, partie.etatPartie)
        partie.scoreManche()
        assertEquals(EtatPartie.NOUVELLE_MANCHE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantPiocheUneCarte()
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
                CarteNum(8),  CarteNum(5)
            )
        )
        // 5 tours de round-robin : chaque repeat pioche J1 puis J2
        repeat(5) {
            partie.joueurCourantPiocheUneCarte() // J1
            partie.joueurCourantPiocheUneCarte() // J2
        }
        // Après le dernier tour, c'est J1 qui est courant
        partie.joueurCourantDitStop() // J1
        partie.joueurCourantDitStop() // J2
        assertEquals(EtatPartie.MANCHE_TERMINEE, partie.etatPartie)
        partie.scoreManche()
        assertEquals(EtatPartie.PARTIE_TERMINEE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantPiocheUneCarte()
        }
    }

    @Test
    fun `la carte retournee par joueurCourantPiocheUneCarte est la meme reference que celle ajoutee en main`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(CarteNum(5)))
        val carteRetournee = partie.joueurCourantPiocheUneCarte()
        val carteEnMain = partie.main[0]?.lastOrNull()
        assertSame(carteRetournee, carteEnMain, "La carte retournée doit être la même référence que celle stockée en main")
    }

    @Test
    fun `pioche reduite a sa derniere carte permet encore une pioche`() {
        val partie = partieDebug(nbJoueurs = 2, cartesEnTete = arrayOf(CarteNum(5)))
        assertTrue(partie.taillePioche >= 1)
        assertDoesNotThrow {
            partie.joueurCourantPiocheUneCarte()
        }
    }

    @Test
    fun `doublon avec deux Carte2ndeChance en main ne consomme qu une seule des deux`() {
        val partie = partieDebug(
            nbJoueurs = 2,
            cartesEnTete = arrayOf(Carte2ndeChance(), Carte2ndeChance(), CarteNum(5), CarteNum(5))
        )
        // Tout va à J1 : 4 pioches consécutives (le joueur courant reste J1 tant qu'il n'a pas dit stop)
        partie.joueurCourantPiocheUneCarte() // Carte2ndeChance → J1, tour passe J2...
        // Attention : après une pioche normale le tour passe. Il faut vérifier qui est courant.
        // Les 4 cartes sont pour J1 uniquement si on s'assure que le tour ne change pas.
        // Avec le round-robin, les pioches alternent. On remet les cartes en alternance :
        // pos 0=J1, 1=J2(inutile), 2=J1, 3=J2(inutile), etc.
        // → On utilise une pioche intercalée, et on ne teste que la main de J1.
        val partie2 = partieDebug(
            nbJoueurs = 2,
            cartesEnTete = arrayOf(
                Carte2ndeChance(), CarteNum(1), // J1 pioche 2ndeChance, J2 pioche 1
                Carte2ndeChance(), CarteNum(2), // J1 pioche 2ndeChance, J2 pioche 2
                CarteNum(5),       CarteNum(3), // J1 pioche 5, J2 pioche 3
                CarteNum(5),       CarteNum(4)  // J1 pioche doublon 5, J2 pioche 4
            )
        )
        repeat(4) {
            partie2.joueurCourantPiocheUneCarte() // J1
            partie2.joueurCourantPiocheUneCarte() // J2
        }

        val main0 = partie2.main[0].orEmpty()
        val nbSecondeChanceRestantes = main0.count { it.estCarte2ndeChance() }
        assertEquals(1, nbSecondeChanceRestantes, "Une seule des deux Carte2ndeChance doit avoir été consommée")
        assertEquals(EtatJoueur.JOUE_ENCORE, partie2.etatJoueur[0], "Le joueur doit rester actif grâce à la Carte2ndeChance restante")
    }

    @Test
    fun `doublon sauve par Carte2ndeChance puis nouveau doublon sans 2ndeChance restante met le joueur a PERDU`() {
        // Pioche intercalée pour que J1 reçoive les bonnes cartes en round-robin
        val partie = partieDebug(
            nbJoueurs = 2,
            cartesEnTete = arrayOf(
                Carte2ndeChance(), CarteNum(1), // J1: 2ndeChance,  J2: 1
                CarteNum(5),       CarteNum(2), // J1: 5,           J2: 2
                CarteNum(5),       CarteNum(3), // J1: doublon 5 → sauvé, J2: 3
                CarteNum(7),       CarteNum(4), // J1: 7,           J2: 4
                CarteNum(7),       CarteNum(6)  // J1: doublon 7 → PERDU, J2: 6
            )
        )
        repeat(3) {
            partie.joueurCourantPiocheUneCarte() // J1
            partie.joueurCourantPiocheUneCarte() // J2
        }
        assertEquals(EtatJoueur.JOUE_ENCORE, partie.etatJoueur[0])

        partie.joueurCourantPiocheUneCarte() // J1: CarteNum(7)
        partie.joueurCourantPiocheUneCarte() // J2: CarteNum(4)
        partie.joueurCourantPiocheUneCarte() // J1: doublon 7 → PERDU
        assertEquals(EtatJoueur.PERDU, partie.etatJoueur[0])
    }

    @Test
    fun `flip7 se declenche des la 7e CarteNum, avant qu une CarteBonusMultiplie suivante ne puisse etre piochee`() {
            val partie = partieDebug(
            nbJoueurs = 2,
            cartesEnTete = arrayOf(
                CarteNum(0), CarteNum(9),
                CarteNum(1), CarteNum(1),
                CarteNum(2), CarteNum(8),
                CarteNum(3), CarteNum(4),
                CarteNum(4), CarteNum(2),
                CarteNum(5), CarteNum(3),
                CarteNum(6),
                CarteBonusMultiplie()
            )
        )
        repeat(6) {
            partie.joueurCourantPiocheUneCarte() // J1
            partie.joueurCourantPiocheUneCarte() // J2
        }
        partie.joueurCourantPiocheUneCarte() // J1 : CarteNum(6) → Flip7
        assertEquals(EtatPartie.MANCHE_TERMINEE, partie.etatPartie)

        assertThrows(EtatPartieInvalideException::class.java) {
            partie.joueurCourantPiocheUneCarte()
        }
    }
}