package iut.math

/**
 * Fabrique un rationnel à partir de deux entiers passés en paramètre ;
 * @param numerator le numérateur
 * @param denominator le dénominateur
 * @throws IllegalArgumentException si le denominateur vaut 0
 */
class Rational(numerator : Int,denominator : Int= 1)  {

    private var numerator : Int
    private var denominator : Int
    init {
        this.denominator= denominator
        this.numerator = numerator
    }
    /**
     * renvoie une chaine de caractères représentant le rationnel
     * @return cette chaine
     */
    override fun toString(): String {
        return "$numerator/$denominator"
    }

    /**
     * realise la somme d'un rationnel avec le rationnel courant
     * @param rational le rationnel à ajouter
     * @return la somme
     */
    operator fun plus(rational : Rational) : Rational {

        var new_num = (numerator * rational.denominator ) + (denominator * rational.numerator)
        var new_den = denominator * rational.denominator
        return Rational(new_num,new_den)
    }
    operator  fun plus(nb : Int): Rational {
        var new_num : Int = (numerator * 1 ) + (denominator * nb)
        var new_den : Int = denominator
        return Rational(new_num,new_den)
    }

    operator fun minus (rational : Rational) : Rational {

        var new_num = (numerator * rational.denominator ) - (denominator * rational.numerator)
        var new_den = denominator * rational.denominator
        return Rational(new_num,new_den)
    }
    operator  fun minus (nb : Int): Rational {
        var new_num : Int = (numerator * 1 ) - (denominator * nb)
        var new_den : Int = denominator
        return Rational(new_num,new_den)
    }
    fun opposite() : Rational{
        return Rational((-numerator), denominator)
    }

    fun reduce(): Rational {
        var new_num : Int = numerator / pgcd(numerator,denominator)
        var new_den : Int = denominator /pgcd(numerator,denominator)
        return Rational(new_num,new_den)
    }
    operator fun times(rational: Rational): Rational{
        var new_num : Int = (numerator * rational.numerator )
        var new_den : Int = denominator*rational.denominator
        return Rational(new_num,new_den)

    }
    operator fun times(nb : Int): Rational{
        var new_num : Int = (numerator * nb )
        var new_den : Int = denominator
        return Rational(new_num,new_den)


    }
    operator  fun div(rational: Rational): Rational{
        var new_num : Int = (numerator * rational.denominator )
        var new_den : Int = denominator * rational.numerator
        return Rational(new_num,new_den)

    }
    operator  fun div(nb : Int): Rational{
        var new_num : Int = (numerator  )
        var new_den : Int = denominator * nb
        return Rational(new_num,new_den)

    }



}

/**
 * A IMPLEMENTER QUESTION 8
 *
 * Fonction donnant une approximation rationnelle d'un flottant à une approximation près
 * @param x le flottant à convertir
 * @param n l'approximation à considérer
 * @return un Rationnel approximant x
 */
fun toRational(x : Double, n : Int) : Rational {
    TODO("retourne un rationnel approximant la valeur réelle")
}
