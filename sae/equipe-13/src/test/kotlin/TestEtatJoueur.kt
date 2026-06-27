import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.etats.EtatPartie
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestEtatJoueur {
    var tab : ArrayList<Carte> = arrayListOf(CarteNum(0), CarteNum(1),CarteNum(2),
                                                CarteNum(3),CarteNum(4),CarteNum(5),
                                                CarteNum(6),CarteNum(7),CarteNum(8),
                                                CarteNum(9),CarteNum(10),CarteNum(11),
                                                CarteNum(12))
    var deckPerso = ControleDeck(tab).donneDeckControle()
    var listeJoueur = listOf< IJoueur>(Joueur("Bob"), Joueur("Mari"), Joueur("Adrien"))
    var flip7 = Flip7(3, listeJoueur, deckPerso, true, 200)
    @Test
    fun testEtatJoueur() {
        for (i in 0..tab.size -3  ){
            val cartePioche = flip7.joueurCourantPiocheUneCarte()
            if (cartePioche is CarteStop){
                assertTrue(flip7.etatPartie== EtatPartie.ATTENTE_CIBLE_STOP)
                flip7.joueurCourantCibleStop(cartePioche, flip7.joueurCourant+1)
            }
            if (cartePioche is Carte3aLaSuite){
                assertTrue(flip7.etatPartie==EtatPartie.ATTENTE_CIBLE_3SUITE)
                flip7.joueurCourantCibleStop(cartePioche, flip7.joueurCourant+1)
            }

        }
        assertTrue(flip7.etatJoueur[0] == EtatJoueur.JOUE_ENCORE)
        assertTrue(flip7.etatJoueur[0] != EtatJoueur.STOP)
        assertTrue(flip7.etatJoueur[0] != EtatJoueur.PERDU)
        assertTrue(flip7.etatJoueur[1] == EtatJoueur.JOUE_ENCORE)
        assertTrue(flip7.etatJoueur[1] != EtatJoueur.STOP)
        assertTrue(flip7.etatJoueur[1] != EtatJoueur.PERDU)
        assertTrue(flip7.etatJoueur[2] == EtatJoueur.JOUE_ENCORE)
        assertTrue(flip7.etatJoueur[2] != EtatJoueur.STOP)
        assertTrue(flip7.etatJoueur[2] != EtatJoueur.PERDU)
        for (i in 0..listeJoueur.size-1){
            flip7.joueurCourantDitStop()

        }
        assertTrue(flip7.etatJoueur[0] == EtatJoueur.STOP)
        assertTrue(flip7.etatJoueur[1] == EtatJoueur.STOP)
        assertTrue(flip7.etatJoueur[2] == EtatJoueur.STOP)
    }
    @Test
    fun testEtatJoueurPerdu(){
        tab = arrayListOf(CarteNum(0), CarteNum(1),CarteNum(2),
                            CarteNum(3),CarteNum(1),CarteNum(5),
                            CarteNum(6),CarteNum(7),CarteNum(8),
                            CarteNum(9),CarteNum(10),CarteNum(11)
                            ,CarteNum(12))
        deckPerso = ControleDeck(tab).donneDeckControle()
        flip7 = Flip7(listeJoueur.size,listeJoueur,deckPerso, true, 50)
        for (i in 0..10  ){
            flip7.joueurCourantPiocheUneCarte()
        }
        // le joueur 1 a obtenu un doublon carte n°1
        assertTrue(flip7.etatJoueur[1] == EtatJoueur.PERDU)
        println(flip7.main)

    }
}