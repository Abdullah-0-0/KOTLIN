import iut.info1.flip7.cartes.Carte

class ControleDeck(listeCarte : ArrayList<Carte>) {
    private val liste : ArrayList<Carte>
    init {
        this.liste = listeCarte
    }
    open fun donneDeckControle() : ArrayList<Carte>{
        return liste
    }
}