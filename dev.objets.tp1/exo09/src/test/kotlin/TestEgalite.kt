import info.but1.collections.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class TestEgalite {
    
    @Test
    fun sontEgaux1() {
        val tab1 = nutArrayOf(42)
        val tab2 = nutArrayOf(42)
        assertTrue(egalite(tab1, tab2))
    }

    @Test
    fun sontEgaux2() {
        val tab1 = nutArrayOf(4, 2)
        val tab2 = nutArrayOf(2, 4)
        assertTrue(egalite(tab1, tab2))
    }

    @Test
    fun sontEgaux5() {
        val tab1 = nutArrayOf(3, 2, 1, 5, 10)
        val tab2 = nutArrayOf(2, 10, 1, 5, 3)
        assertTrue(egalite(tab1, tab2))
    }

    @Test
    fun sontEgaux7() {
        val tab1 = nutArrayOf(1, 2, 3, 4, 5, 6, 8)
        val tab2 = nutArrayOf(8, 6, 5, 4, 3, 2, 1)
        assertTrue(egalite(tab1, tab2))
    }

    @Test
    fun sontPasEgaux_plusPetit() {
        val tab1 = nutArrayOf(1, 2, 3, 4, 5, 6, 8)
        val tab2 = nutArrayOf(8, 6, 5, 4, 3)
        assertFalse(egalite(tab1, tab2))
    }

    @Test
    fun sontPasEgaux_plusGrand() {
        val tab1 = nutArrayOf(8, 6, 5, 4, 3)
        val tab2 = nutArrayOf(1, 2, 3, 4, 5, 6, 8)
        assertFalse(egalite(tab1, tab2))
    }

    @Test
    fun sontPasEgaux_Different1() {
        val tab1 = nutArrayOf(1, 2, 3, 4, 5, 6, 8)
        val tab2 = nutArrayOf(10, 2, 3, 4, 5, 6, 8)
        assertFalse(egalite(tab1, tab2))
    }

        @Test
    fun sontPasEgaux_Different2() {
        val tab1 = nutArrayOf(1, 2, 3, 4, 5, 6, 8)
        val tab2 = nutArrayOf(8, 6, 5, 4, 3, 2, 10)
        assertFalse(egalite(tab1, tab2))
    }
}