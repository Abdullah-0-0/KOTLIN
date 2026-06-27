import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.OutilsCarte
import iut.info1.flip7.etats.EtatJoueur
import iut.info1.flip7.exceptions.EtatPartieInvalideException
import iut.info1.flip7.exceptions.JoueurNonActifException
import iut.info1.flip7.exceptions.ListeJoueursInvalideException
import iut.info1.flip7.exceptions.NombreJoueursInvalideException
import iut.info1.flip7.exceptions.PiocheInvalideException
import iut.info1.flip7.exceptions.ScoreFinPartieInvalideException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import org.junit.jupiter.api.assertThrows

class TestFlip7 {

    var flip7 = Flip7(3, listOf< IJoueur>(Joueur("1"), Joueur("2"), Joueur("3")), listOf<Carte>(CarteNum(1)), true, 200)

    @Test
    fun test1() {
        assertTrue(true)
    }
    //test Flip7
    //creation deck
    @Test
    fun test_deck(){
        var deck = Deck()
        var liste = deck.donneDeck()
        val outil = OutilsCarte()
        outil.verifiePiocheInitiale(liste)
    }
    @Test
    fun testJoueur(){
        var joueur = Joueur("Bob")
        assertEquals("Bob", joueur.toString())
    }
    @Test
    fun testPioche(){
        var Deck = Deck()
        var liste_Joueur : ArrayList<IJoueur> = arrayListOf<IJoueur>(Joueur("1"), Joueur("2"), Joueur("3"))
        val liste_carteTotal = Deck().donneDeck()
        assertThrows(PiocheInvalideException::class.java){
            val flip7 = Flip7(liste_Joueur.size,liste_Joueur,liste_carteTotal.subList(0,45))
        }
        //val flip7 = Flip7(liste_Joueur.size,liste_Joueur,liste_carteTotal.subList(0,45))

    }
    //test création Flip7
    @ParameterizedTest
    @MethodSource("parametreFlip7")
    fun TestFlip7(typeTest: String, nb_joueur: Int,maxListeJoueur: Int,nbCarteMax : Int, debug: Boolean,scoreFinal : Int,res : Any) {
        val listeJoueur = arrayListOf<Joueur>()
        val cartes = Deck().donneDeck()
        for (i in 1..maxListeJoueur) {
            listeJoueur.add(Joueur("joueur $i"))
        }
        if (typeTest=="ListeJoueursInvalideException"){
            if (res == false){
                assertThrows(ListeJoueursInvalideException::class.java) {
                    val flip7 = Flip7(nb_joueur,listeJoueur,cartes.subList(0,nbCarteMax),debug)
                }
            }
            if (res == true){
                assertDoesNotThrow {
                    val flip7 = Flip7(nb_joueur, listeJoueur,cartes.subList(0,nbCarteMax),debug)
                }
            }
        }
        if(typeTest== "ListeJoueursInvalideException"){
            if (res == false){
                assertThrows(ListeJoueursInvalideException::class.java){
                    val flip7 = Flip7(nb_joueur, listeJoueur,cartes.subList(0,nbCarteMax),debug)
                }
            }
            if (res == true){
                assertDoesNotThrow { val flip7 = Flip7(nb_joueur, listeJoueur,cartes.subList(0,nbCarteMax),debug) }
            }
        }
        if (typeTest=="ScoreFinPartieInvalideException"){
            if (res == false){
                assertThrows(ScoreFinPartieInvalideException::class.java){
                    val flip7 = Flip7(nb_joueur, listeJoueur,cartes.subList(0,nbCarteMax),debug,scoreFinal)
                }
            }
            if (res == true){
                assertDoesNotThrow {val flip7 = Flip7(nb_joueur, listeJoueur,cartes.subList(0,nbCarteMax),debug,scoreFinal)  }
            }
        }
        if (typeTest == "PiocheInvalideException"){
            if (res == false){
                assertThrows(PiocheInvalideException::class.java){
                    val flip7 = Flip7(nb_joueur,listeJoueur,cartes.subList(0,nbCarteMax),debug,scoreFinal)
                }
            }
            if (res == true){
                assertDoesNotThrow {
                    val flip7 = Flip7(nb_joueur, listeJoueur,cartes.subList(0,nbCarteMax),debug,scoreFinal)
                }
            }
        }
    }
    companion object{
        @JvmStatic
        fun parametreFlip7(): Stream<Arguments> {
            return Stream.of(
                of("ListeJoueursInvalideException",3,1,94,true, 200,false),
                of("ListeJoueursInvalideException",2,4,94,true, 200,false),
                of("ListeJoueursInvalideException",4,-1,94,true,200, false),
                of("ListeJoueursInvalideException",4,4,94,true, 200,true),
                of("ListeJoueursInvalideException",2,2,94,true, 200,true),
                of("ListeJoueursInvalideException",3,3,94,true, 200,true),
                of("NombreJoueursInvalideException",-2 shl 32-1,4,94,true, 200,false),
                of("NombreJoueursInvalideException",-12,4,94,true, 200,false),
                of("NombreJoueursInvalideException",0,4,94,true, 50,false),
                of("NombreJoueursInvalideException",4,4,94,true, 200,true),
                of("NombreJoueursInvalideException",3,3,94,true, 100,true),
                of("NombreJoueursInvalideException",2,2,94,true, 158,true),
                of("ScoreFinPartieInvalideException",4,4,94,true,49, false),
                of("ScoreFinPartieInvalideException",4,4,94,true,50, true),
                of("ScoreFinPartieInvalideException",4,4,94,true,150, true),
                of("ScoreFinPartieInvalideException",4,4,94,true,98, true),
                of("ScoreFinPartieInvalideException",4,4,94,true,201, false),
                of("ScoreFinPartieInvalideException",4,4,94,true,-25, false),
                of("ScoreFinPartieInvalideException",4,4,94,true,0, false),
                of("ScoreFinPartieInvalideException",4,4,94,true,500, false),
                of("PiocheInvalideException",4,4,94,false,200,true),
                of("PiocheInvalideException",4,4,50,false,200,false),
                of("PiocheInvalideException",4,4,1,false,200,false),
                of("PiocheInvalideException",4,4,0,false,200,false),
            )
        }
    }


    // test joueurCourantPiocheUneCarte()
    @Test
    fun courantPioche(){
        val MainAvant = flip7.joueurCourant
        val tailleAvant = flip7.main[MainAvant]?.size

        val cartePioche = assertDoesNotThrow {
            flip7.joueurCourantPiocheUneCarte()
        }

        val mainApres = flip7.main[MainAvant]
        assertTrue(mainApres?.size != tailleAvant)
        assertEquals(tailleAvant!! + 1, mainApres!!.size)
        assertTrue(cartePioche  in mainApres)
    }
    @Test
    fun CourantCibleStop1(){
        val listeCarte : ArrayList<Carte>  = arrayListOf()
        listeCarte.addAll(listOf(
            CarteNum(2),CarteNum(8), CarteNum(0), CarteStop(),CarteNum(4),
            CarteNum(11)
        )
        )
        val deckContro = ControleDeck(listeCarte).donneDeckControle()
        var listeJoueur = listOf< IJoueur>(Joueur("Bob"), Joueur("Mari"), Joueur("Adrien"))
        var flip7 = Flip7(3, listeJoueur, deckContro, true, 200)
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantPiocheUneCarte()
        flip7.joueurCourantPiocheUneCarte()
        val carteStop = flip7.joueurCourantPiocheUneCarte()
        assertDoesNotThrow { flip7.joueurCourantCibleStop(carteStop,flip7.joueurCourant +2 ) }

    }

    @Test
    fun CourantCibleStop2(){
        val listeCarte : ArrayList<Carte>  = arrayListOf()
        listeCarte.addAll(listOf(
            CarteNum(2),CarteNum(8), CarteNum(0), CarteStop(),CarteNum(4),
            CarteNum(11)
        )
        )
        val deckContro = ControleDeck(listeCarte).donneDeckControle()
        var listeJoueur = listOf< IJoueur>(Joueur("Bob"), Joueur("Mari"), Joueur("Adrien"))
        var flip7 = Flip7(3, listeJoueur, deckContro, true, 200)

        for (i in 0..listeCarte.size-1){
            val cartePioche = flip7.joueurCourantPiocheUneCarte()
            if (cartePioche is CarteStop){
                assertThrows(EtatPartieInvalideException::class.java){
                    flip7.joueurCourantPiocheUneCarte()
                }
                assertDoesNotThrow {
                    flip7.joueurCourantCibleStop(cartePioche,flip7.joueurCourant + 1)
                }
            }
            assertThrows(EtatPartieInvalideException::class.java){
                flip7.joueurCourantCibleStop(cartePioche, flip7.joueurCourant + 1)
            }
        }
    }
    @Test
    fun CourantCible3Cartes(){
        val listeCarte : ArrayList<Carte>  = arrayListOf()
        listeCarte.addAll(listOf(
            CarteNum(2),CarteNum(8), CarteNum(0), Carte3aLaSuite(),CarteNum(4),
            CarteNum(11), CarteNum(3), CarteNum(3),CarteNum(7), CarteNum(10),
        )
        )
        val deckContro = ControleDeck(listeCarte).donneDeckControle()
        var listeJoueur = listOf< IJoueur>(Joueur("Bob"), Joueur("Mari"), Joueur("Adrien"))
        var flip7 = Flip7(3, listeJoueur, deckContro, true, 200)
        for (i in 0..listeCarte.size ){
            val cartePioche = flip7.joueurCourantPiocheUneCarte()
            if (cartePioche is Carte3aLaSuite){
                assertThrows(EtatPartieInvalideException::class.java){
                    flip7.joueurCourantPiocheUneCarte()
                }
                val nbCarteJoueurSuivant = flip7.main[flip7.joueurCourant+1]!!.size
                assertDoesNotThrow {
                    flip7.joueurCourantCible3aLaSuite(cartePioche,flip7.joueurCourant + 1)
                }
                //comportement anormal
                assertEquals(nbCarteJoueurSuivant + 4 , flip7.main[flip7.joueurCourant]!!.size)
                //Aide IA a mieux comprendre le comportement du IF.
                break
            }
        }
    }

    @Test
    fun JoueurCourantDitStop(){
        //initialisation
        val joueurs = listOf< IJoueur>(Joueur("1"), Joueur("2"))
        val deck=Deck()
        var lcarte=deck.donneDeck()
        val tflip = Flip7(2,joueurs,lcarte,false,200)
        tflip.joueurCourantPiocheUneCarte()
        tflip.joueurCourantPiocheUneCarte()
        //on verifie qu'il n'a bien qu'une carte
        assertEquals(1,tflip.main[0]!!.size)
        tflip.joueurCourantDitStop()
        //on verifie qu'il ne joue plus
        assertEquals(1,tflip.joueurCourant)
        //on verifie que sa main na pas bouger
        assertEquals(1,tflip.main[0]!!.size)
        tflip.joueurCourantPiocheUneCarte()
        //on verifie qu'il n'a pas recu de carte
        assertEquals(1,tflip.main[0]!!.size)
        //on verifie qu'il ne rejoue pas
        assertEquals(1,tflip.joueurCourant)

        //on verifie qu'il est bien stop
        assertEquals(EtatJoueur.STOP,tflip.etatJoueur[0])

    }
    @Test
    fun scoreManche(){
        var tab : ArrayList<Carte> = arrayListOf(CarteNum(0), CarteNum(1),CarteNum(2),CarteNum(3),CarteNum(4),CarteNum(5),CarteNum(6),CarteNum(7),CarteNum(8),CarteNum(9),CarteNum(10),CarteNum(11),CarteNum(12))
        val deckPerso = ControleDeck(tab).donneDeckControle()
        var listeJoueur = listOf< IJoueur>(Joueur("Bob"), Joueur("Mari"), Joueur("Adrien"))
        var flip7 = Flip7(3, listeJoueur, deckPerso, true, 200)
        for (i in 0..tab.size -3  ){
            val cartePioche = flip7.joueurCourantPiocheUneCarte()
            if (cartePioche is Carte3aLaSuite){

            }
        }
        assertThrows(EtatPartieInvalideException::class.java){
            // si les joueurs n'ont pas fini de jouer cela doit leve une exception
            flip7.scoreManche()
        }
        for (i in 0..listeJoueur.size-1){
            flip7.joueurCourantDitStop()
        }
        val score = flip7.scoreManche()
        val listeCarteJoueur1 = flip7.main[0]
        var compteCarteJoueur1  = 0
        val listeCarteCarteJoueur2 = flip7.main[1]
        var compteCarteJoueur2  = 0
        val listeCarteCarteJoueur3 = flip7.main[2]
        var compteCarteJoueur3  = 0
        for ( i in 0..listeCarteJoueur1!!.size-1){
            compteCarteJoueur1+=listeCarteJoueur1[i].valeur
        }
        for ( i in 0..listeCarteCarteJoueur2!!.size-1){
            compteCarteJoueur2+=listeCarteCarteJoueur2[i].valeur
        }
        for ( i in 0..listeCarteCarteJoueur3!!.size-1){
            compteCarteJoueur3+=listeCarteCarteJoueur3[i].valeur
        }
        assertEquals(compteCarteJoueur1,score[0])
        assertEquals(compteCarteJoueur2,score[1])
        assertEquals(compteCarteJoueur3,score[2])
    }
    @Test
    fun testNouvelleManche(){
        var tab : ArrayList<Carte> = arrayListOf(CarteNum(0), CarteNum(1),CarteNum(2),CarteNum(3),CarteNum(4),CarteNum(5),CarteNum(6),CarteNum(7),CarteNum(8),CarteNum(9),CarteNum(10),CarteNum(11),CarteNum(12))
        val deckPerso = ControleDeck(tab).donneDeckControle()
        var listeJoueur = listOf< IJoueur>(Joueur("Bob"), Joueur("Mari"), Joueur("Adrien"))
        var flip7 = Flip7(3, listeJoueur, deckPerso, true, 200)
        for (i in 0..tab.size -3  ){
            val cartePioche = flip7.joueurCourantPiocheUneCarte()
            if (cartePioche is Carte3aLaSuite){
            }
        }
        for (i in 0..listeJoueur.size-1){
            flip7.joueurCourantDitStop()
        }
        assertThrows(EtatPartieInvalideException::class.java){
            // le score n'a pas encore était faite, on ne peut pas commencer une nouvelle partie
            flip7.nouvelleManche()
        }
        assertDoesNotThrow { val score = flip7.scoreManche() }

        assertDoesNotThrow { val nouvelleManche = flip7.nouvelleManche() }
    }
}