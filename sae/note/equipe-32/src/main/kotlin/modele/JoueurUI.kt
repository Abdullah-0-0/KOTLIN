package iut.info1.flip7.prototype.modele

import iut.info1.flip7.etats.EtatJoueur

class JoueurUI(
    val pseudo: String,
    val main: MutableList<CarteUI> = mutableListOf(),
    var etat: EtatJoueur = EtatJoueur.JOUE_ENCORE,
    var estJoueurActif: Boolean = false,
    var score: Int = 0
) {
    val peutJouer: Boolean get() = etat == EtatJoueur.JOUE_ENCORE
    val aArrete: Boolean get() = etat == EtatJoueur.STOP
    val aPerdu: Boolean get() = etat == EtatJoueur.PERDU
}