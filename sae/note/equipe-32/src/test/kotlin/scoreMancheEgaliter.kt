import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.OutilsCarte
import io.mockk.every
import io.mockk.mockk
import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatPartie

/*Code demander par le prof sur mattermost
    TOM :
    Bonjour, il me semble qu'il y a une erreur dans le diagramme d'états,
    Il me semble qu'il faut également vérifier si deux joueurs on le même score et que le score des deux joueurs aux points égal est > 200 , alors il faut relancer une autre partie.

    ARNAUD LANOIX :
    oui, ce point de règle n'est pas traité ; es-tu capable de donner un cas de test produisant ce scénario ?
*/

class scoreMancheEgaliter {
    private val outils = OutilsCarte()

    fun creeJoueurs(nb: Int): List<IJoueur> {
        val joueurs = mutableListOf<IJoueur>()
        for (i in 1..nb) {
            val joueurMock = mockk<IJoueur>()
            every { joueurMock.donneNom() } returns "Joueur$i"
            joueurs.add(joueurMock)
        }
        return joueurs
    }

    fun piocheValide(): List<Carte> {
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
    fun `deux joueur avec le meme score au-dessus scoreFinPartie fait une nouvelle manche`() {
        val pioche: List<Carte> = listOf(
            CarteNum(12), CarteNum(12),
            CarteNum(10), CarteNum(10),
            CarteBonusMultiplie(), CarteBonusMultiplie(),
            CarteNum(9), CarteNum(9)
        )
        val partie = Flip7(
            nbJoueurs = 2,
            joueurs = creeJoueurs(2),
            cartes = pioche,
            debug = true,
            scoreFinPartie = 50
        )
        repeat(3) {
            partie.joueurCourantPiocheUneCarte()
            partie.joueurCourantPiocheUneCarte()
        }
        partie.joueurCourantDitStop()
        partie.joueurCourantDitStop()
        var score: Map<Int, Int> = emptyMap()
        assertDoesNotThrow {
            score = partie.scoreManche()
        }
        assertEquals(score[0], score[1], "Les deux joueurs doivent terminer avec le meme score")
        assertEquals(EtatPartie.NOUVELLE_MANCHE, partie.etatPartie)
    }


    @Test
    fun `deux joueurs avec le meme score au-dessus de scoreFinPartie termine pas la partie `() {
        val pioche: List<Carte> = listOf(
            CarteNum(12), CarteNum(12),
            CarteNum(10), CarteNum(10),
            CarteBonusMultiplie(), CarteBonusMultiplie(),
            CarteNum(9), CarteNum(9)
        )
        val partie = Flip7(
            nbJoueurs = 2,
            joueurs = creeJoueurs(2),
            cartes = pioche,
            debug = true,
            scoreFinPartie = 50
        )
        repeat(3) {
            partie.joueurCourantPiocheUneCarte()
            partie.joueurCourantPiocheUneCarte()
        }
        partie.joueurCourantDitStop()
        partie.joueurCourantDitStop()
        var score: Map<Int, Int> = emptyMap()
        assertDoesNotThrow {
            score = partie.scoreManche()
        }
        assertEquals(score[0], score[1], "Les deux joueurs doivent terminer avec le meme score")
        assertNotEquals("PARTIE_TERMINER", partie.etatPartie)
    }
}