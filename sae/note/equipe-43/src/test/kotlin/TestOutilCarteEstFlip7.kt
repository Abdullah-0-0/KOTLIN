import iut.info1.flip7.cartes.Carte
import iut.info1.flip7.cartes.CarteBonusMultiplie
import iut.info1.flip7.cartes.CarteBonusPlus
import iut.info1.flip7.cartes.CarteNum
import iut.info1.flip7.cartes.Carte2ndeChance
import iut.info1.flip7.cartes.OutilsCarte
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TestOutilCarteEstFlip7{
    @ParameterizedTest
    @MethodSource("donneListes")
    fun testCalculScore1(listeCartes: List<Carte>, expected: Boolean){
        val outilCarte = OutilsCarte()
        var res : Boolean
            res = outilCarte.estFlip7(listeCartes)

        assertEquals(res, expected)
    }

    companion object {
        @JvmStatic
        fun donneListes() = listOf(
            Arguments.of(listOf<Carte>(),false),
            Arguments.of(listOf(CarteNum(1),CarteNum(2),CarteNum(3),CarteNum(4),CarteNum(5),CarteNum(6),CarteNum(7)),true),
            Arguments.of(listOf(CarteNum(1),CarteNum(2),CarteNum(3),CarteNum(4),CarteNum(5),CarteNum(6),CarteNum(7),CarteBonusMultiplie()),true),
            Arguments.of(listOf(CarteNum(1),CarteNum(2),CarteNum(3),CarteNum(4),CarteNum(5),CarteNum(6),CarteNum(7),CarteBonusPlus(2)),true),
            Arguments.of(listOf(CarteNum(1),CarteNum(2),CarteNum(3),CarteNum(4),CarteNum(5),CarteNum(6),CarteNum(7),Carte2ndeChance()),true),
            Arguments.of(listOf(CarteNum(1),CarteNum(2),CarteNum(3),CarteNum(4), CarteNum(5),CarteNum(6)),false)
        )
    }
}