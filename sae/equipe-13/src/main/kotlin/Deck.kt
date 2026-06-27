import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.Carte3aLaSuite
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.CarteStop
import iut.info1.flip7.cartes.OutilsCarte
import kotlin.random.Random

class Deck {

    val outil = OutilsCarte()
    val liste_carte = arrayListOf<Carte>()
    val liste_num = arrayListOf<Int>(0,1,2,3,4,5,6,7,8,9,10,11,12)
    fun donneDeck(): List<Carte>{
        liste_carte.add(CarteNum(0))
        for (i in 0 until liste_num.size){
            for (j in 0 until liste_num[i]){
                liste_carte.add(CarteNum(liste_num[i]))
            }
        }
        for (i in 2..10 step 2) {
            liste_carte.add(CarteBonusPlus(i))
        }
        liste_carte.add(CarteBonusMultiplie())
        for(i in 1 until 4){
            liste_carte.add(CarteStop())
            liste_carte.add(Carte2ndeChance())
            liste_carte.add(Carte3aLaSuite())
        }
        return liste_carte
    }
//    fun donneDeckAléatoire() : ArrayList<Carte>{
//        var liste_carte = arrayListOf<Carte>()
//        var numCarteAle = Random.nextInt(12)
//        var nb_carteAle = Random.nextInt(94)
//        var ramCarteBonus = Random.nextInt(12,2)
//        var nbCarteSpécial = Random.nextInt(3)
//        liste_carte.add(CarteNum(0))
//        for (i in 0..nb_carteAle){
//            numCarteAle = Random.nextInt(12)
//            liste_carte.add(CarteNum(numCarteAle))
//        }
//        for(i in 0..ramCarteBonus){
//            liste_carte.add(CarteBonusPlus(i))
//        }
//        for (i in 0..nbCarteSpécial){
//            liste_carte.add(CarteStop())
//            liste_carte.add(Carte2ndeChance())
//            liste_carte.add(Carte3aLaSuite())
//        }
//        liste_carte.add(CarteBonusMultiplie())
//        return liste_carte
//    }

}