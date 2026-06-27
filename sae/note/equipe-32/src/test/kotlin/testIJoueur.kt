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


class testIJoueur {

    private fun creeJoueurs(nb: Int): List<IJoueur> {
        val joueurs = mutableListOf<IJoueur>()
        for (i in 1..nb) {
            val joueurMock = mockk<IJoueur>()
            every { joueurMock.donneNom() } returns "Joueur$i"
            joueurs.add(joueurMock)
        }
        return joueurs
    }

        @Test
        fun `donneNom() renvoie bien le nom fourni au joueur`(){
            var liste = creeJoueurs(2)
            var actual = liste[0].donneNom()
            assertEquals("Joueur1", actual)
            actual = liste[1].donneNom()
            assertEquals("Joueur2", actual)
        }

    private fun creememeJoueurs(nb: Int): List<IJoueur> {
        val joueurs = mutableListOf<IJoueur>()
        for (i in 1..nb) {
            val joueurMock = mockk<IJoueur>()
            every { joueurMock.donneNom() } returns "Joueur1"
            joueurs.add(joueurMock)
        }
        return joueurs
    }

        @Test
        fun `deux IJoueur avec le même nom sont acceptés`(){
            var liste = creememeJoueurs(2)
            var actual = liste[0].donneNom()
            assertEquals("Joueur1", actual)
            actual = liste[1].donneNom()
            assertEquals("Joueur1", actual)
        }
}