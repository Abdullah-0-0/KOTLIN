package iut.info1.flip7.prototype.modele

sealed class CarteUI(val texteAffiche: String, val couleurFond: String) {
    class Num(val valeur: Int) : CarteUI(valeur.toString(), "#fdf6e3")
    class BonusPlus(val valeur: Int) : CarteUI("+$valeur", "#cde8d5")
    class BonusMultiplie : CarteUI("x2", "#cde8d5")
    class Stop : CarteUI("STOP", "#e8a3a3")
    class SecondeChance : CarteUI("2e\nChance", "#a3c9e8")
    class TroisALaSuite : CarteUI("3 À LA\nSUITE", "#e8d3a3")
}