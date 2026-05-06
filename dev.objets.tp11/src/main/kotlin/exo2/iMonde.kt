package exo2

interface iMonde {
    /** Retourne le nombre de pays dans le monde */
    fun taille(): Int

    /**
     * Ajoute le pays passé en paramètre s'il n'est pas déjà présent
     *
     * @param p le pays à ajouter
     * @return true si le pays a bien été ajouté
     */
    fun ajouter(p: Pays): Boolean

    override fun toString(): String

    /**
     * remplit le monde à partir d'un fichier .csv
     *
     * voir dans README.md
     *
     * @param nomFichier chemin/nom du fichier .csv
     */
    fun remplir(nomFichier: String)

    /**
     * Retourne le Pays de nom passé en paramètre. Méthode utilisant une boucle
     * pour effectuer une recherche séquentielle dans la liste de pays.
     *
     * @param nom du pays à obtenir
     * @result le pays trouvé ou null si le pays indiqué n'est pas présent.
     */
    fun getPays(nom: String): Pays?

    /**
     * Retourne le Pays de nom passé en paramètre. Méthode utilisant indexOf()
     * et get() de List. indexOf() utilise equals... qui a été redéfinie
     * Pensez à créer un faux pays, à partir du nom recherché
     *
     * @param nom du pays à obtenir
     * @result le pays trouvé ou null si le pays indiqué n'est pas présent.
     */
    fun getPays2(nom: String): Pays?

    /**
     * Retourne une List des Pays plus peuplés que le pays passé en paramètre.
     *
     * @param ref le pays reference
     */
    fun plusPeuple(ref: Pays): MutableList<Pays>

    /**
     * Retourne une List des Pays dont la population est strictement supérieure
     * à la limite
     */
    fun populationSuperieureA(limite: Int): MutableList<Pays>

    /** Retourne (une copie de) la liste des Pays du monde */
    fun monde(): MutableList<Pays>

    /**
     * Retourne (une copie de) la liste des Pays du monde triée de manière
     * croissante selon l'ordre naturel.
     */
    fun mondeTrie(): MutableList<Pays>

    /**
     * Retourne un tableau trié de manière croissante des Pays du monde selon
     * l'ordre naturel.
     */
    fun mondeTrieTab(): Array<Pays>

    /**
     * Retourne (une copie de) la liste des Pays du monde trié selon la
     * superficie des pays
     *
     * voir dans README.md
     *
     */
    fun mondeTrieSelonSuperficie(): MutableList<Pays>
}