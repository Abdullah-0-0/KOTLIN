import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.IOutilsCarte
import iut.info1.flip7.exceptions.NombreJoueursInvalideException
import iut.info1.flip7.exceptions.ScoreFinPartieInvalideException

class TestConstructeurFlip7CasAdditionnels {

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
    fun `nbJoueurs egal a 0 leve NombreJoueursInvalideException`() {
        assertThrows(NombreJoueursInvalideException::class.java) {
            Flip7(0, creeJoueurs(0), piocheValide())
        }
    }

    @Test
    fun `nbJoueurs negatif leve NombreJoueursInvalideException`() {
        assertThrows(NombreJoueursInvalideException::class.java) {
            Flip7(-3, creeJoueurs(2), piocheValide())
        }
    }

    @Test
    fun `scoreFinPartie egal a 0 leve ScoreFinPartieInvalideException`() {
        assertThrows(ScoreFinPartieInvalideException::class.java) {
            Flip7(2, creeJoueurs(2), piocheValide(), scoreFinPartie = 0)
        }
    }

    @Test
    fun `scoreFinPartie negatif leve ScoreFinPartieInvalideException`() {
        assertThrows(ScoreFinPartieInvalideException::class.java) {
            Flip7(2, creeJoueurs(2), piocheValide(), scoreFinPartie = -10)
        }
    }

    @Test
    fun `scoreFinPartie egal a 50 exactement est accepte (borne basse incluse)`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide(), scoreFinPartie = 50)
        assertNotNull(flip7)
    }

    @Test
    fun `scoreFinPartie egal a 200 exactement est accepte (borne haute incluse)`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide(), scoreFinPartie = 200)
        assertNotNull(flip7)
    }

    @Test
    fun `scoreFinPartie a une valeur intermediaire non ronde est accepte`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide(), scoreFinPartie = 137)
        assertNotNull(flip7)
    }

    @Test
    fun `une liste de joueurs avec deux fois la meme instance IJoueur est acceptee`() {

        val joueurMock = mockk<IJoueur>()
        every { joueurMock.donneNom() } returns "Solo"
        val joueurs = listOf(joueurMock, joueurMock)
        val flip7 = Flip7(2, joueurs, piocheValide())
        assertNotNull(flip7)
    }

    @Test
    fun `un IOutilsCarte personnalise injecte au constructeur est reellement utilise`() {
        val outilsPersonnalise = object : IOutilsCarte {
            override fun verifiePiocheInitiale(pioche: List<Carte>) {
            }
            override fun verifieMainCorrecte(main: List<Carte>) {
            }
            override fun calculScore(main: List<Carte>): Int = 999
            override fun estFlip7(main: List<Carte>): Boolean = false
        }
        val pioche = mutableListOf<Carte>(CarteNum(3), CarteNum(2))
        pioche.addAll(piocheValide())
        val flip7 = Flip7(
            2,
            creeJoueurs(2),
            pioche,
            debug = true,
            outilsCarte = outilsPersonnalise
        )
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantDitStop()
        flip7.joueurCourantDitStop()

        val scores = flip7.scoreManche()

        assertEquals(999, scores[0], "scoreManche() doit utiliser le calculScore() de l'IOutilsCarte injecte")
    }

    @Test
    fun `debug=true avec une pioche complete de 94 cartes valides ne leve pas d exception`() {
        val flip7 = Flip7(2, creeJoueurs(2), piocheValide(), debug = true)
        assertEquals(94, flip7.taillePioche)
    }
}
