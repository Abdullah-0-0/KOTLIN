import info.but1.collections.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class TestEnvers {
    
    @Test
    fun envers11() {
        val tab = nutArrayOf(1, 2, 3, 4, 5, 5, 4, 6, 8, 2, 2)
        val res = nutArrayOf(2, 2, 8, 6, 4, 5, 5, 4, 3, 2, 1)
        envers(tab)
        for (i in 0 until tab.size) {
            assertEquals(res[i], tab[i])
        }
    }

    @Test
    fun envers10() {
        val tab = nutArrayOf(1, 2, 3, 4, 5, 5, 4, 6, 8, 2)
        val res = nutArrayOf(2, 8, 6, 4, 5, 5, 4, 3, 2, 1)
        envers(tab)
        for (i in 0 until tab.size) {
            assertEquals(res[i], tab[i])
        }

    }
    @Test
    fun envers3() {
        val tab = nutArrayOf(6, 8, 2)
        val res = nutArrayOf(2, 8, 6)
        envers(tab)
        for (i in 0 until tab.size) {
            assertEquals(res[i], tab[i])
        }
    }
    @Test
    fun envers2() {
        val tab = nutArrayOf(2, 4)
        val res = nutArrayOf(4, 2)
        envers(tab)
        for (i in 0 until tab.size) {
            assertEquals(res[i], tab[i])
        }
    }
    @Test
    fun envers1() {
        val tab = nutArrayOf(42)
        val res = nutArrayOf(42)
        envers(tab)
        for (i in 0 until tab.size) {
            assertEquals(res[i], tab[i])
        }
    }
}