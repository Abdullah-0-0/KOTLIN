import info.but1.collections.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TestSomme {
    
    @Test
    fun somme0() {
        val tab = nutArrayOf(0)
        assertEquals(0, somme(tab))
    }

    @Test
    fun somme1() {
        val tab =nutArrayOf(1)
        assertEquals(1, somme(tab))
    }

    @Test
    fun somme10() {
        val tab =nutArrayOf(2, 3, 1, 4)
        assertEquals(10, somme(tab))
    }

    @Test
    fun somme42() {
        val tab =nutArrayOf(1, 2, 3, 4, 5, 5, 4, 6, 8, 2, 2)
        assertEquals(42, somme(tab))
    }
}