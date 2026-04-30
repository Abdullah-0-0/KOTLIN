package iut.collections

interface iPile : iCollection {


    /**
     * ajoute un element au sommet de la pile
     * @param element la valeur à ajouter
     */
    fun empiler(element : Int)

    /**
     * consulte le sommet de la pile
     * @return la valeur stockée au sommet de la pile
     * @throws NoSuchElementException s'il n'y a rien à consulter
     */
    fun consulter() : Int

    /**
     * dépile et retourne le sommet de la pile
     * @return la valeur stockée au sommet de la pile
     * @throws NoSuchElementException s'il n'y a rien à consulter
     */
    fun depiler() : Int

}