package iut.collections

interface iListe : iFile {

    /**
     * donne la première position à laquelle se trouve l'élément considéré
     * @param l'élément recherché
     * @return la position où se trouve l'élément, ; -1 si l'élément est absent de la liste
     */
    fun index(element : Int) : Int

    /**
     * consulte l'élément d'indice donné
     * @param index la position à consulter dans la liste
     * @return l'élément se trouvant à la position indiquée
     * @throws IndexOutOfBoundsException ; si la position indiquée ne correspond à rien dans la liste
     */
    fun consulter(index : Int) : Int

    /**
     * insère une valeur dans la liste à la position indiquée
     * @param index la position d'insertion dans la liste
     * @param element  la valeur à insérer
     * @throws IndexOutOfBoundsException ; si la position indiquée ne correspond à rien dans la liste
     */
    fun inserer(index : Int, element : Int)


    /**
     * supprime la valeur dans la liste à la position indiquée
     * @param index la position de suppression dans la liste
     * @throws IndexOutOfBoundsException ; si la position indiquée ne correspond à rien dans la liste
     */
    fun supprimer(index : Int)
}
