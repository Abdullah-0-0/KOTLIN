import info.but1.collections.*

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import org.junit.jupiter.api.Assertions.*

class TestDecroissant {
    
    @ParameterizedTest(name = "{0} trié décroissant =? {1}")
    @MethodSource("getData")
    fun `tris décroissants`(src : NutArray<Int>, res : NutArray<Int>) {
        decroissant(src)
        for (i in 0 until src.size) {
            assertEquals(res[i], src[i])
        }
    }

    companion object {
        @JvmStatic
        fun getData(): List<Arguments> {
            return listOf<Arguments>(
                    Arguments.of(nutArrayOf(42),
                        nutArrayOf(42)),
                    Arguments.of(nutArrayOf(4, 2),
                        nutArrayOf(4, 2)),
                    Arguments.of(nutArrayOf(2, 4),
                        nutArrayOf(4, 2)),
                    Arguments.of(nutArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
                        nutArrayOf(8, 7, 6, 5, 4, 3, 2, 1)),
                    Arguments.of(nutArrayOf(6, 5, 3, 1, 8, 7, 2, 4),
                        nutArrayOf(8, 7, 6, 5, 4, 3, 2, 1)),
                    Arguments.of(nutArrayOf(8, 6, 5, 5, 4, 4, 3, 2, 2, 2, 1), //doublons
                        nutArrayOf(8, 6, 5, 5, 4, 4, 3, 2, 2, 2, 1)),
                    Arguments.of(nutArrayOf(2, 2, 8, 6, 4, 5, 1, 5, 4, 3, 2), // doublons
                        nutArrayOf(8, 6, 5, 5, 4, 4, 3, 2, 2, 2, 1))
             )
        }
    }
}