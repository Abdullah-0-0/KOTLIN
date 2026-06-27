import iut.info1.flip7.Flip7
import iut.info1.flip7.IJoueur
import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonus
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.exceptions.CarteInvalideException
import iut.info1.flip7.exceptions.EtatPartieInvalideException
import iut.info1.flip7.exceptions.IndiceJoueurInvalideException
import iut.info1.flip7.exceptions.JoueurNonActifException
import iut.info1.flip7.exceptions.ListeJoueursInvalideException
import iut.info1.flip7.exceptions.NombreJoueursInvalideException
import iut.info1.flip7.exceptions.ScoreFinPartieInvalideException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class TestException {

    val deck = Deck()

    @Test
    fun ListeJoueursInvalideException01(){
        //Nombre de joueurs inférieur à celui indiqué
        assertThrows(ListeJoueursInvalideException::class.java) { Flip7(3, listOf<IJoueur>(Joueur("1"), Joueur("2")), listOf<Carte>(CarteNum(1)), true) }
    }

    @Test
    fun ListeJoueursInvalideException02(){
        //nombre de joueurs supérieur à celui indiqué
        assertThrows(ListeJoueursInvalideException::class.java) { Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2"), Joueur("3")), listOf<Carte>(CarteNum(1)), true) }
    }

    @Test
    fun NombreJoueursInvalideException01(){
        //Nombre de joueurs inférieur à la capacité
        assertThrows(NombreJoueursInvalideException::class.java) { Flip7(-1, listOf<IJoueur>(), listOf<Carte>(CarteNum(1)), true) }

    }

    @Test
    fun NombreJoueursInvalideException02(){
        //Nombre de joueurs supérieur à la capacité
        assertThrows(NombreJoueursInvalideException::class.java) { Flip7(5, listOf<IJoueur>(Joueur("1"), Joueur("2"), Joueur("3"), Joueur("4"), Joueur("5")), listOf<Carte>(CarteNum(1)), true) }

    }

    @Test
    fun ListeJoueursInvalideExceptionEtNombreJoueursInvalideExceptionBonnesValeurs01(){
        //Teste si il n'y a pas de problèmes avec les valeurs normales, marche pour les 2 exception listées dans le nom de la fonction
        assertDoesNotThrow { Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), listOf<Carte>(CarteNum(1)), true) }
        assertDoesNotThrow { Flip7(3, listOf<IJoueur>(Joueur("1"), Joueur("2"), Joueur("3")), listOf<Carte>(CarteNum(1)), true) }
        assertDoesNotThrow { Flip7(4, listOf<IJoueur>(Joueur("1"), Joueur("2"), Joueur("3"), Joueur("4")), listOf<Carte>(CarteNum(1)), true) }
    }

    @Test
    fun ScoreFinPartieInvalideException01(){
        //Score inférieur à la capacité
        assertThrows(ScoreFinPartieInvalideException::class.java) { Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), listOf<Carte>(CarteNum(1)), true, -1) }

    }

    @Test
    fun ScoreFinPartieInvalideException02(){
        //Score supérieur à la capacité
        assertThrows(ScoreFinPartieInvalideException::class.java) { Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), listOf<Carte>(CarteNum(1)), true, 2 shl 30) }

    }

    @Test
    fun ScoreFinPartieInvalideExceptionBonnesValeurs01(){
        //Teste si il n'y a pas de problèmes avec les valeurs normales
        assertDoesNotThrow { Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), listOf<Carte>(CarteNum(1)), true, 50) }
        assertDoesNotThrow { Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), listOf<Carte>(CarteNum(1)), true, 200) }
        assertDoesNotThrow { Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), listOf<Carte>(CarteNum(1)), true, 125) }
    }

    @Test
    fun CarteInvalideException01(){
        val deck = listOf<Carte>(CarteStop())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCibleStop(CarteStop(), 0)
        }
    }

    @Test
    fun CarteInvalideException02(){
        val deck = listOf<Carte>(CarteStop(), CarteStop())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCibleStop(deck[1], 0)
        }
    }

    @Test
    fun CarteInvalideException03(){
        val deck = listOf<Carte>(CarteStop())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCibleStop(Carte3aLaSuite(), 0)
        }
    }

    @Test
    fun CarteInvalideException04(){
        val deck = listOf<Carte>(CarteStop())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCibleStop(CarteNum(1), 0)
        }
    }

    @Test
    fun CarteInvalideException05(){
        val deck = listOf<Carte>(CarteStop())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCibleStop(Carte2ndeChance(), 0)
        }
    }

    @Test
    fun CarteInvalideException06(){
        val deck = listOf<Carte>(CarteStop())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCibleStop(CarteBonusPlus(6), 0)
        }
    }

    @Test
    fun CarteInvalideException07(){
        val deck = listOf<Carte>(CarteStop())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCibleStop(CarteBonusMultiplie(), 0)
        }
    }

    @Test
    fun CarteInvalideException08(){
        val deck = listOf<Carte>(CarteStop())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertDoesNotThrow {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCibleStop(deck[0], 0)
        }
    }

    @Test
    fun CarteInvalideException09(){
        val deck = listOf<Carte>(Carte3aLaSuite())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCible3aLaSuite(Carte3aLaSuite(), 0)
        }
    }

    @Test
    fun CarteInvalideException10(){
        val deck = listOf<Carte>(Carte3aLaSuite(), Carte3aLaSuite())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCible3aLaSuite(deck[1], 0)
        }
    }

    @Test
    fun CarteInvalideException11(){
        val deck = listOf<Carte>(Carte3aLaSuite())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCible3aLaSuite(CarteStop(), 0)
        }
    }

    @Test
    fun CarteInvalideException12(){
        val deck = listOf<Carte>(Carte3aLaSuite())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCible3aLaSuite(CarteNum(5), 0)
        }
    }

    @Test
    fun CarteInvalideException13(){
        val deck = listOf<Carte>(Carte3aLaSuite())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCible3aLaSuite(Carte2ndeChance(), 0)
        }
    }

    @Test
    fun CarteInvalideException14(){
        val deck = listOf<Carte>(Carte3aLaSuite())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCible3aLaSuite(CarteBonusPlus(2), 0)
        }
    }

    @Test
    fun CarteInvalideException15(){
        val deck = listOf<Carte>(Carte3aLaSuite())
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(CarteInvalideException::class.java) {
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCible3aLaSuite(CarteBonusMultiplie(), 0)
        }
    }

    @Test
    fun CarteInvalideException16(){
        val deck = listOf<Carte>(Carte3aLaSuite(), CarteNum(1), CarteNum(1))
        val carteinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertDoesNotThrow{
            carteinva.joueurCourantPiocheUneCarte()
            carteinva.joueurCourantCible3aLaSuite(deck[0], 0)
        }
    }


    @Test
    fun JoueurNonActifException01() {
        val deck = listOf<Carte>(CarteStop())
        val joueurnonactif = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(JoueurNonActifException::class.java) {
            joueurnonactif.joueurCourantDitStop()
            joueurnonactif.joueurCourantPiocheUneCarte()
            joueurnonactif.joueurCourantCibleStop(deck[0], 0)
        }
    }

    @Test
    fun IndiceJoueurInvalideException01() { //Ex JoueurNonActifException02
        val deck = listOf<Carte>(CarteStop())
        val joueurnonactif = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(IndiceJoueurInvalideException::class.java) {
            joueurnonactif.joueurCourantDitStop()
            joueurnonactif.joueurCourantPiocheUneCarte()
            joueurnonactif.joueurCourantCibleStop(deck[0], 2)
        }
    }

    @Test
    fun JoueurNonActifException03() {
        val deck = listOf<Carte>(Carte3aLaSuite())
        val joueurnonactif = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(JoueurNonActifException::class.java) {
            joueurnonactif.joueurCourantDitStop()
            joueurnonactif.joueurCourantPiocheUneCarte()
            joueurnonactif.joueurCourantCible3aLaSuite(deck[0], 0)
        }
    }

    @Test
    fun IndiceJoueurInvalideException02() { //Ex JoueurNonActifException04
        val deck = listOf<Carte>(Carte3aLaSuite())
        val joueurnonactif = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(IndiceJoueurInvalideException::class.java) {
            joueurnonactif.joueurCourantDitStop()
            joueurnonactif.joueurCourantPiocheUneCarte()
            joueurnonactif.joueurCourantCible3aLaSuite(deck[0], 2)
        }
    }

    @Test
    fun JoueurNonActifException05() {
        val deck = listOf<Carte>(Carte3aLaSuite(), CarteNum(1), CarteNum(1))
        val joueurnonactif = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertDoesNotThrow {
            joueurnonactif.joueurCourantPiocheUneCarte()
            joueurnonactif.joueurCourantCible3aLaSuite(deck[0], 1)
        }
    }

    @Test
    fun JoueurNonActifException06() {
        val deck = listOf<Carte>(CarteStop(), CarteNum(1), CarteNum(1))
        val joueurnonactif = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertDoesNotThrow {
            joueurnonactif.joueurCourantPiocheUneCarte()
            joueurnonactif.joueurCourantCibleStop(deck[0], 1)
        }
    }

    @Test
    fun JoueurNonActifException07() {
        val deck = listOf<Carte>(Carte3aLaSuite(), CarteNum(1), CarteNum(1))
        val joueurnonactif = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertDoesNotThrow {
            joueurnonactif.joueurCourantPiocheUneCarte()
            joueurnonactif.joueurCourantCible3aLaSuite(deck[0], joueurnonactif.joueurCourant)
        }
    }

    @Test
    fun JoueurNonActifException08() {
        assertDoesNotThrow {
            val liste_joueur = listOf<Joueur>(Joueur("0"), Joueur("1"))
            val testdeck = listOf<Carte>( CarteStop())
            val jeu = Flip7(2,liste_joueur, testdeck, true)
            jeu.joueurCourantPiocheUneCarte()
            jeu.joueurCourantCibleStop( testdeck[0], jeu.joueurCourant)
        }

    }

    @Test
    fun EtatPartieInvalideException01() { //Etat partie : MANCHE_TERMINEE
        val deck = listOf<Carte>(Carte3aLaSuite(), CarteNum(1), CarteNum(1))
        val etatinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        etatinva.joueurCourantDitStop()
        etatinva.joueurCourantDitStop()
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantDitStop()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantPiocheUneCarte()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCibleStop(CarteStop(), 0)
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCible3aLaSuite(deck[0], 0)
        }
        assertThrows (EtatPartieInvalideException::class.java){
            etatinva.nouvelleManche()
        }
        assertDoesNotThrow {
            etatinva.scoreManche()
        }

    }

    @Test
    fun EtatPartieInvalideException02() { //Etat partie : ATTENTE_CHOIX_JOUEUR
        val deck = listOf<Carte>(Carte3aLaSuite(), CarteNum(1), CarteNum(1))
        val etatinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCibleStop(CarteStop(), 0)
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCible3aLaSuite(deck[0], 1)
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.scoreManche()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.nouvelleManche()
        }
        assertDoesNotThrow{
            etatinva.joueurCourantDitStop()
        }
        assertDoesNotThrow {
            etatinva.joueurCourantPiocheUneCarte()
        }

    }

    @Test
    fun EtatPartieInvalideException03() { //Etat partie : ATTENTE_CIBLE_STOP
        val deck = listOf<Carte>(CarteStop(), CarteNum(1), CarteNum(1))
        val etatinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        etatinva.joueurCourantPiocheUneCarte()
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCible3aLaSuite(deck[0], 1)
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.scoreManche()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.nouvelleManche()
        }
        assertThrows(EtatPartieInvalideException::class.java){
            etatinva.joueurCourantDitStop()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantPiocheUneCarte()
        }
        assertDoesNotThrow {
            etatinva.joueurCourantCibleStop(deck[0], 0)
        }

    }

    @Test
    fun EtatPartieInvalideException04() { //Etat partie : ATTENTE_CIBLE_3SUITE
        val deck = listOf<Carte>(Carte3aLaSuite(), CarteNum(1), CarteNum(1))
        val etatinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        etatinva.joueurCourantPiocheUneCarte()
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCibleStop(CarteStop(), 0)
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.scoreManche()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.nouvelleManche()
        }
        assertThrows(EtatPartieInvalideException::class.java){
            etatinva.joueurCourantDitStop()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantPiocheUneCarte()
        }
        assertDoesNotThrow {
            etatinva.joueurCourantCible3aLaSuite(deck[0], 1)
        }

    }

    @Test
    fun EtatPartieInvalideException05() { //Etat partie : NOUVELLE_MANCHE
        val deck = listOf<Carte>(Carte3aLaSuite(), CarteNum(1), CarteNum(1))
        val etatinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 200)
        etatinva.joueurCourantDitStop()
        etatinva.joueurCourantDitStop()
        etatinva.scoreManche()
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCibleStop(CarteStop(), 0)
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.scoreManche()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCible3aLaSuite(deck[0], 1)
        }
        assertThrows(EtatPartieInvalideException::class.java){
            etatinva.joueurCourantDitStop()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantPiocheUneCarte()
        }
        assertDoesNotThrow {
            etatinva.nouvelleManche()
        }

    }

    @Test
    fun EtatPartieInvalideException06() { //Etat partie : PARTIE_TERMINEE
        val deck = listOf<Carte>(CarteBonusMultiplie(), CarteNum(12), CarteNum(11), CarteNum(10))
        val etatinva = Flip7(2, listOf<IJoueur>(Joueur("1"), Joueur("2")), deck, true, 50)
        etatinva.joueurCourantDitStop()
        etatinva.joueurCourantPiocheUneCarte()
        etatinva.joueurCourantPiocheUneCarte()
        etatinva.joueurCourantPiocheUneCarte()
        etatinva.joueurCourantPiocheUneCarte()
        etatinva.joueurCourantDitStop()
        etatinva.scoreManche()
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCibleStop(CarteStop(), 0)
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.scoreManche()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantCible3aLaSuite(deck[0], 1)
        }
        assertThrows(EtatPartieInvalideException::class.java){
            etatinva.joueurCourantDitStop()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.joueurCourantPiocheUneCarte()
        }
        assertThrows(EtatPartieInvalideException::class.java) {
            etatinva.nouvelleManche()
        }

    }


}