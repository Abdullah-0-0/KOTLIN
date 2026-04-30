package iut.collections

import net.sourceforge.plantuml.bpm.Cell

class Cellule(valeur : Int , suivant : Cellule? = null) {
    private var valeur : Int
    private var suivant : Cellule ? = null
    init {
        this.valeur =valeur
        this.suivant = suivant
    }
    fun valeur() : Int {
        return valeur
    }
    fun suivant() : Cellule ? {
        return suivant
    }
    fun modifieSuivant(nouveauSuivant : Cellule ?) {
        suivant = nouveauSuivant
    }
}