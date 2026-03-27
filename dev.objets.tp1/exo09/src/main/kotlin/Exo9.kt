import info.but1.collections.*

/**
La fonction somme doit calculer la somme des nombres contenus dans un tableau
d'entiers puis retourner cette somme. Il faudra bien penser à se demander ce
qu'est la somme d'un tableau vide.

@author init.dev (L.Jezequel)

@param tab : un tableau d'entiers complètement remplis

@return un entier correspondant à la somme des éléments de tab

*/

fun somme(tab : NutArray<Int>) : Int {
    var res : Int = 0
    for (i in 0 until tab.size step 1){
        res = res +i
    }
    return res
}

/**
La fonction recherche doit indiquer la premiere position d'une valeur v dans un tableau
tab. Si la valeur v n'est pas présente, elle indique -1

@author init.dev (L.Jezequel)

@param tab : le tableau dans lequel chercher
@param v : la valeur à chercher

@return  la position de v dans tab (si elle existe)

*/

fun recherche(tab : NutArray<Int>, v :Int) : Int {
    TODO()
}

/**
Un ensemble d'entier est un paquet de plusieurs entiers, sans doublons.
La fonction estEnsemble doit indiquer si en tableau d'entiers correspond à un
ensemble ou non.

@author init.dev (L.Jezequel)

@param E : un tableau d'entiers

@return un booléen indiquant si E est bien un ensemble ou non 
*/

fun estEnsemble(tab : NutArray<Int>) : Boolean {
    TODO()
}

/**
Étant donné un tableau, la fonction envers doit l'inverser (en place), c'est à
dire que le premier élément du tableau doit devenir le dernier, le deuxième
élément doit devenir l'avant dernier, etc jusqu'au dernier élément qui doit
devenir le premier.

@param tab : le tableau à inverser

La fonction modifie le tableau d'entrée et n'a donc pas de sorties.
 */
fun envers(tab : NutArray<Int>) {
    TODO()
}

/**
On considère des ensembles de nombres représentés par des tableaux : on considère
que ces tableaux ne contiennent qu'une seule fois chaque nombre (puisqu'ils
représentent des ensembles) et les nombres ne sont pas nécessairement dans
l'ordre dans les tableaux.

On veut savoir si deux ensembles sont égaux ou pas, c'est-a-dire savoir si deux
tableaux contiennent les mêmes nombres ou pas. C'est à cette question que doit
répondre la fonction egalite.

@author init.dev (L.Jezequel)

@param tab1 un tableau d'entiers (sans doublons) représentant un ensemble
@param tab2  un tableau d'entiers (sans doublons) représentant un ensemble

@return un booléen qui vaut true si tab1 et tab2 représentent le même ensemble et
          qui vaut false sinon
*/
fun egalite(tab1 : NutArray<Int>, tab2 : NutArray<Int>) : Boolean {
    TODO()
}

/**
La fonction décroissant doit trier un tableau d'entiers du plus grand
au plus petit.

@author init.dev (L.Jezequel) 

@param tab : le tableau à trier

La fonction modifie le tableau d'entrée et n'a donc pas de sorties.
*/
fun decroissant(tab : NutArray<Int>) {
    // TODO : en utilisant le tri par insertion
    // https://fr.wikipedia.org/wiki/Tri_par_insertion
    TODO()
}


/**
La fonction alphabetique trie un tableau de chaînes de caractères dans l'ordre
alphabétique.

@author init.dev (L.Jezequel)

@param dico : le tableau de chaînes de caractères à trier

La fonction modifie le tableau d'entrée et n'a donc pas de sorties.
*/
fun alphabetique(dico : Array<String>) {
    //TODO : en utilisant le tri à bulles
    //https://fr.wikipedia.org/wiki/Tri_%C3%A0_bulles
    // NB : Les opérateurs de comparaison fonctionnent sur les chaines de caractères
    TODO()
}

