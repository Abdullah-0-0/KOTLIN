import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.etats.EtatPartie
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.exceptions.NombreJoueursInvalideException
import iut.info1.flip7.exceptions.ListeJoueursInvalideException
import iut.info1.flip7.exceptions.ScoreFinPartieInvalideException
import iut.info1.flip7.exceptions.PiocheInvalideException

class estFlip7constructeur {
    private fun creeJoueurs(nb: Int): List<IJoueur> {
        val joueurs = mutableListOf<IJoueur>()
        for (i in 1..nb) {
            val joueurMock = mockk<IJoueur>()
            every { joueurMock.donneNom() } returns "Joueur$i"
            joueurs.add(joueurMock)
        }
        return joueurs
    }

    private fun piocheValide(): List<Carte> {
        val cartes = mutableListOf<Carte>()
        cartes.add(CarteNum(0))
        for (v in 1..12) {
            for (n in 1..v) {
                cartes.add(CarteNum(v))
            }
        }
        cartes.add(CarteBonusPlus(2))
        cartes.add(CarteBonusPlus(4))
        cartes.add(CarteBonusPlus(6))
        cartes.add(CarteBonusPlus(8))
        cartes.add(CarteBonusPlus(10))
        cartes.add(CarteBonusMultiplie())
        for (n in 1..3) { cartes.add(CarteStop()) }
        for (n in 1..3) { cartes.add(Carte2ndeChance()) }
        for (n in 1..3) { cartes.add(Carte3aLaSuite()) }
        return cartes
    }

    @Test
    fun `2 joueurs pioche valide scoreFinPartie 200`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide(), scoreFinPartie = 200)
        assertNotNull(flip7)
    }

    @Test
    fun `4 joueurs pioche valide scoreFinPartie 200`() {
        val flip7 = Flip7(4, creeJoueurs(4), piocheValide(), scoreFinPartie = 200)
        assertNotNull(flip7)
    }

    @Test
    fun `3 joueurs pioche valide scoreFinPartie 50`() {
        val flip7 = Flip7(3, creeJoueurs(3), piocheValide(), scoreFinPartie = 50)
        assertNotNull(flip7)
    }

    @Test
    fun `1 joueur renvoie NombreJoueursInvalideException`() {
        assertThrows(NombreJoueursInvalideException::class.java) {
            Flip7(1, creeJoueurs(1), piocheValide())
        }
    }

    @Test
    fun `5 joueurs renvoie NombreJoueursInvalideException`() {
        assertThrows(NombreJoueursInvalideException::class.java) {
            Flip7(5, creeJoueurs(5), piocheValide())
        }
    }

    @Test
    fun `liste de joueurs vide renvoie ListeJoueursInvalideException`() {
        assertThrows(ListeJoueursInvalideException::class.java) {
            Flip7(2, emptyList(), piocheValide())
        }
    }

    @Test
    fun `nbJoueurs different de la taille de la liste renvoie ListeJoueursInvalideException`() {
        assertThrows(ListeJoueursInvalideException::class.java) {
            Flip7(3, creeJoueurs(2), piocheValide())
        }
    }

    @Test
    fun `scoreFinPartie 49 renvoie ScoreFinPartieInvalideException`() {
        assertThrows(ScoreFinPartieInvalideException::class.java) {
            Flip7(2, creeJoueurs(2), piocheValide(), scoreFinPartie = 49)
        }
    }

    @Test
    fun `scoreFinPartie 201 renvoie ScoreFinPartieInvalideException`() {
        assertThrows(ScoreFinPartieInvalideException::class.java) {
            Flip7(2, creeJoueurs(2), piocheValide(), scoreFinPartie = 201)
        }
    }

    @Test
    fun `pioche invalide hors debug leve PiocheInvalideException`() {
        val piocheIncomplete = piocheValide().drop(1) // 93 cartes
        assertThrows(PiocheInvalideException::class.java) {
            Flip7(2, creeJoueurs(2), piocheIncomplete, debug = false)
        }
    }

    @Test
    fun `etatPartie initial est ATTENTE_CHOIX_JOUEUR`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertEquals(EtatPartie.ATTENTE_CHOIX_JOUEUR, flip7.etatPartie)
    }

    @Test
    fun `etatJoueur initial est JOUE_ENCORE pour tous`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        flip7.etatJoueur.values.forEach { etat ->
            assertEquals(EtatJoueur.JOUE_ENCORE, etat)
        }
    }

    @Test
    fun `joueurCourant initial est 0`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertEquals(0, flip7.joueurCourant)
    }

    @Test
    fun `main de chaque joueur est initialement vide`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        flip7.main.values.forEach { mainJoueur ->
            assertTrue(mainJoueur.isEmpty())
        }
    }

    @Test
    fun `defausse initiale est vide`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertTrue(flip7.defausse.isEmpty())
    }

    @Test
    fun `taillePioche initiale est 94`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertEquals(94, flip7.taillePioche)
    }

    @Test
    fun `score initial est 0 pour le joueur 0`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertEquals(0, flip7.score.getOrDefault(0, 0))
    }

    @Test
    fun `score initial est 0 pour le joueur 1`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide())
        assertEquals(0, flip7.score.getOrDefault(1, 0))
    }
}