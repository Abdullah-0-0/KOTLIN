import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatPartie
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestEtatPartie {
    var tab : ArrayList<Carte> = arrayListOf(
        CarteNum(0), CarteNum(1),CarteNum(2),
        CarteNum(3),CarteNum(4),CarteNum(5),
        CarteNum(6),CarteNum(7),CarteNum(8),
        CarteNum(9),CarteNum(10),CarteNum(11),
        CarteNum(12),CarteNum(0), CarteNum(1),
        CarteNum(2),CarteNum(3),CarteNum(4),
        CarteNum(5),CarteNum(6),CarteNum(7),
        CarteNum(8),CarteNum(9),CarteNum(10),
        CarteNum(11),CarteNum(12))
    var deckPerso = ControleDeck(tab).donneDeckControle()
    var listeJoueur = listOf<IJoueur>(Joueur("Bob"), Joueur("Mari"), Joueur("Adrien"))
    var flip7 = Flip7(3, listeJoueur, deckPerso, true, 50)
 
    @Test
    fun TestEtatPartie() {
        for (i in 0..17){
            if (flip7.etatPartie!= EtatPartie.MANCHE_TERMINEE){
                val cartePioche = flip7.joueurCourantPiocheUneCarte()
                assertTrue(flip7.etatPartie== EtatPartie.ATTENTE_CHOIX_JOUEUR)
                if (cartePioche is CarteStop){
                    assertTrue(flip7.etatPartie== EtatPartie.ATTENTE_CIBLE_STOP)
                    flip7.joueurCourantCibleStop(cartePioche, flip7.joueurCourant+1)
                }
                if (cartePioche is Carte3aLaSuite){
                    assertTrue(flip7.etatPartie==EtatPartie.ATTENTE_CIBLE_3SUITE)
                    flip7.joueurCourantCibleStop(cartePioche, flip7.joueurCourant+1)
                }
            }
            else{
                break
            }
        }

        for (i in 0..listeJoueur.size-1){
            flip7.joueurCourantDitStop()

        }
        assertTrue(flip7.etatPartie==EtatPartie.MANCHE_TERMINEE)
        val score = flip7.scoreManche()
        assertTrue(flip7.etatPartie==EtatPartie.NOUVELLE_MANCHE)

    }
    @Test
    fun test (){
        for (i in 0..17){
            if (flip7.etatPartie!= EtatPartie.MANCHE_TERMINEE){
                val cartePioche = flip7.joueurCourantPiocheUneCarte()
            assertTrue(flip7.etatPartie== EtatPartie.ATTENTE_CHOIX_JOUEUR)
                if (cartePioche is CarteStop){
                    assertTrue(flip7.etatPartie== EtatPartie.ATTENTE_CIBLE_STOP)
                    flip7.joueurCourantCibleStop(cartePioche, flip7.joueurCourant+1)
                }
                if (cartePioche is Carte3aLaSuite){
                    assertTrue(flip7.etatPartie==EtatPartie.ATTENTE_CIBLE_3SUITE)
                    flip7.joueurCourantCibleStop(cartePioche, flip7.joueurCourant+1)
                }
            }
            else{
                break
            }
        }
        //dernier pioche gagnant
        flip7.joueurCourantPiocheUneCarte()
        assertTrue { flip7.etatPartie==EtatPartie.MANCHE_TERMINEE }
        flip7.scoreManche()
        assertTrue { flip7.etatPartie==EtatPartie.PARTIE_TERMINEE }
    }
}