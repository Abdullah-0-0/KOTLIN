import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName

class Flip7MoteurTest {

    // mock de l'interface joueur
    private fun mockJoueur(nom: String): iut.info1.flip7.IJoueur {
        return object : iut.info1.flip7.IJoueur {
            override fun donneNom(): String = nom
        }
    }

    private fun creerJeu(pioche: List<Carte>, scoreFin: Int = 100): Flip7 {
        val joueurs = listOf(mockJoueur("J1"), mockJoueur("J2"), mockJoueur("J3"))
        val piocheSecu = if (pioche.isEmpty()) listOf(CarteNum(5)) else pioche
        val scoreFinSecu = if (scoreFin < 50) 50 else scoreFin
        return Flip7(nbJoueurs = 3, joueurs = joueurs, cartes = piocheSecu, debug = true, scoreFinPartie = scoreFinSecu)
    }

    @Test
    fun testChangementDeJoueurSimple() {
        val joueurs = listOf(mockJoueur("Alice"), mockJoueur("Bob"))
        val pioche = listOf(CarteNum(4), CarteNum(5))
        val jeu = Flip7(nbJoueurs = 2, joueurs = joueurs, cartes = pioche, debug = true)
        assertEquals(0, jeu.joueurCourant)
        jeu.joueurCourantDitStop() 
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche numero simple -> incremente l'index du joueur courant")
    fun testPiochePremierNumero_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteNum(5)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche de la carte zero -> le tour avance normalement")
    fun testPiocheNumeroZero_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteNum(0)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche de la carte 12 -> le tour avance sans probleme")
    fun testPiocheNumeroDouze_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteNum(12)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Deux actions de pioche -> fait avancer le tour de deux crans")
    fun testPiocheDeuxNumerosDifferents_DeuxChangementsDeJoueur() {
        val jeu = creerJeu(listOf(CarteNum(3), CarteNum(8)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(2, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Trois actions de pioche -> boucle complete sur les 3 joueurs")
    fun testPiocheTroisNumerosDifferents_BoucleSurLesJoueurs() {
        val jeu = creerJeu(listOf(CarteNum(1), CarteNum(2), CarteNum(3)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(2, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(0, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Un joueur dit stop puis le suivant pioche -> progression logiques des index")
    fun testJoueurDeuxPiocheNumero_PasseAJoueurTrois() {
        val jeu = creerJeu(listOf(CarteNum(1), CarteNum(2)))
        jeu.joueurCourantDitStop()
        assertEquals(1, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(2, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Apres deux stop successifs, une pioche maintient le tour sur l'index max")
    fun testJoueurTroisPiocheNumero_RevientAJoueurUn() {
        val jeu = creerJeu(listOf(CarteNum(1), CarteNum(2), CarteNum(3)))
        jeu.joueurCourantDitStop()
        jeu.joueurCourantDitStop()
        assertEquals(2, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(2, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Sequence de pioche puis stop -> incremente correctement l'index")
    fun testPiocheMultiple_OrdreDesJoueursRespecte() {
        val jeu = creerJeu(listOf(CarteNum(5), CarteNum(6), CarteNum(7)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
        jeu.joueurCourantDitStop()
        assertEquals(2, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche d'un doublon lors du tour -> passe l'index au joueur suivant")
    fun testPiocheDoublon_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteNum(5), CarteNum(5)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche d'un doublon de zero -> l'index passe aussi au suivant")
    fun testPiocheDoublonDeZero_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteNum(0), CarteNum(0)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Sequence de pioches menant a un doublon -> verification de chaque transition")
    fun testPiocheTroisiemeCarteDoublon_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteNum(2), CarteNum(7), CarteNum(2)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(2, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(0, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Verification du passage de tour suite a l'action de J2")
    fun testJoueurDeuxPioche_PasseAuJoueurTrois() {
        val jeu = creerJeu(listOf(CarteNum(1), CarteNum(1)))
        jeu.joueurCourantDitStop()
        assertEquals(1, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(2, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche d'un 12 en doublon de manche -> l'index passe bien au suivant")
    fun testPiocheGrosseValeur_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteNum(12), CarteNum(12)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche finale de manche -> bascule d'etat sans plantage du systeme")
    fun testDernierJoueurPioche_RevientAuPremier() {
        val jeu = creerJeu(listOf(CarteNum(4), CarteNum(4)))
        jeu.joueurCourantDitStop()
        jeu.joueurCourantDitStop()
        assertEquals(2, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertNotNull(jeu.etatPartie)
    }

    @Test
    @DisplayName("Les mains etant locales, posseder la meme carte que J1 fait pas sauter J2")
    fun testJoueurUnPioche_MainJoueurDeuxIndependante() {
        val jeu = creerJeu(listOf(CarteNum(3), CarteNum(3), CarteNum(3)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(2, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Enchainement de plusieurs pioches -> boucle coherente sur les id")
    fun testCascadeDePioches_BoucleSurLesJoueurs() {
        val jeu = creerJeu(listOf(CarteNum(1), CarteNum(1), CarteNum(2), CarteNum(2)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(2, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche d'une Seconde Chance -> l'index avance de la meme maniere")
    fun testPiocheSecondeChance_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(Carte2ndeChance()))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche d'une carte Stop -> modifie direct l'etat de la partie")
    fun testPiocheStop_DeclencheAttenteCiblage() {
        val jeu = creerJeu(listOf(CarteStop()))
        jeu.joueurCourantPiocheUneCarte()
        assertNotNull(jeu.etatPartie)
    }

    @Test
    @DisplayName("Pioche d'un Bonus Plus -> avance d'un joueur dans le cycle")
    fun testPiocheBonusPlus_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteBonusPlus(4)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche d'un Bonus Multiplicateur -> avance d'un joueur dans le cycle")
    fun testPiocheBonusMultiplicateur_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteBonusMultiplie()))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche d'un gros bonus (+10) -> traitement standard du jeton de tour")
    fun testPiocheBonusDix_PasseAuJoueurSuivant() {
        val jeu = creerJeu(listOf(CarteBonusPlus(10)))
        jeu.joueurCourantPiocheUneCarte()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Pioche d'une carte 3 a la Suite -> bascule vers l'etat attendu")
    fun testPiocheTroisALaSuite_DeclencheAttenteCiblage() {
        val jeu = creerJeu(listOf(Carte3aLaSuite()))
        jeu.joueurCourantPiocheUneCarte()
        assertNotNull(jeu.etatPartie)
    }

    @Test
    @DisplayName("Initialisation avec une liste vide -> leve bien l'exception du constructeur")
    fun testPiocheVide_GereException() {
        assertThrows(Exception::class.java) {
            val joueurs = listOf(mockJoueur("J1"), mockJoueur("J2"))
            Flip7(nbJoueurs = 2, joueurs = joueurs, cartes = emptyList(), debug = true)
        }
    }

    @Test
    @DisplayName("Tenter de piocher apres une action de Stop -> reste bloque sur l'index")
    fun testPiocheApresStop_LeveException() {
        val jeu = creerJeu(listOf(CarteNum(5), CarteNum(6)))
        jeu.joueurCourantDitStop()
        assertEquals(1, jeu.joueurCourant)
    }

    @Test
    @DisplayName("Simulation de pioche sur configuration a score limite -> etat instancie")
    fun testPiocheSurPartiePotentiellementTerminee() {
        val jeu = creerJeu(listOf(CarteNum(12), CarteNum(12), CarteNum(12)), scoreFin = 50)
        jeu.joueurCourantPiocheUneCarte()
        assertNotNull(jeu.etatPartie)
    }
}