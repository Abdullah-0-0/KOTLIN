package exo3.pile

/**
 * classe définissant une pile
 * @param taille la taille de la pile
 * @author Arnaud Lanoix Brauer
 */

class Pile(taille : Int = 10) {

    private lateinit var valeurs : Array<Int?>
    private var sommet : Int

    init {
        try {
            valeurs = arrayOfNulls(taille)
        }
        catch (e : java.lang.NegativeArraySizeException){
            println("pile de taille negatif-> impossible ")
            throw e
        }
        sommet = -1
    }

    /**
     * indique si la pile est vide
     * @return 'true' si la pile est vide
     */
     fun estVide() : Boolean {
        if (valeurs.size == 0){
            return true
        }
        return  false
    }

    /**
     * indique si la pile est pleine
     * @return 'true' si la pile est pleine
     */
     fun estPleine() : Boolean{
         for (i in 0 until valeurs.size){
             if (valeurs[i]==null){
                return false
             }
         }
        return true
    }

    /**
     * empile un élément dans la pile
     * @param elt l'élément à empiler
     */
    fun  empiler(elt : Int) {
        try {
            valeurs[++sommet] = elt
        }
        catch (e : java.lang.ArrayIndexOutOfBoundsException){
            println("pile pleine")
        }
        catch (e : kotlin.UninitializedPropertyAccessException){
            print("erreur :kotlin.UninitializedPropertyAccessException")
        }

    }

    /**
     * dépile le dernier élément empilé
     * @return le dernier élément empilé
     */
    fun depiler() : Int {
        try {
            return valeurs[sommet--]!!
        }
        catch (e :java.lang.ArrayIndexOutOfBoundsException){
            print("on peut plus depile : y a plus rien")
        }
        return -1

    }

    /**
     * donne une chaine de caractères représentant la pile
     * @return une chaine de caractères représentant la pile
     */
    override fun toString(): String {
        var str = "["
        try {
            for (i in 0 .. sommet)
                str += "> ${valeurs[i]} "
            return str + "]"
        }
        catch (e : java.lang.ArrayIndexOutOfBoundsException){
            return "impossible d'affiche pile complete"
        }

    }
}