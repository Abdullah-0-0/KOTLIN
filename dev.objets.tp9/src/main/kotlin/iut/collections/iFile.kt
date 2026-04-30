package iut.collections

interface iFile : iPile {

    /**
     * insère un élément au début de la file
     * @param element la valeur à insérer
     */
    fun insererEnTete(element : Int) {
        empiler(element)
    }

    /**
     * consulte le début de la file
     * @return la valeur stockée au début de la file
     * @throws NoSuchElementException s'il n'y a rien à consulter
     */
    fun consulterEnTete() = consulter()

    /**
     * supprime l'élément au sommet début de la file
     * @throws NoSuchElementException s'il n'y a rien à supprimer
     */
    fun supprimerEnTete() {
        depiler()
    }

    /**
     * insère un élément à la fin de la file
     * @param element la valeur à insérer
     */
    fun insererEnQueue(element : Int)

    /**
     * consulte la fin de la file
     * @return la valeur stockée à la fin de la file
     * @throws NoSuchElementException s'il n'y a rien à consulter
     */
    fun consulterEnQueue() : Int

    /**
     * supprime l'élément à la fin de la file
     * @throws NoSuchElementException s'il n'y a rien à supprimer
     */
    fun supprimerEnQueue()

}