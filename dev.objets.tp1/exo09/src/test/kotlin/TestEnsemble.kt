import info.but1.collections.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class TestEnsemble {
    
    @Test
    fun estEnsemble1() {
        val tab =nutArrayOf(1)
        assertTrue(estEnsemble(tab))
    }
    @Test
    fun estEnsemble2() {
        val tab =nutArrayOf(7, 5)
        assertTrue(estEnsemble(tab))
    }
    @Test
    fun estEnsemble7() {
        val tab =nutArrayOf(1, 2, 3, 4, 5, 6, 8)
        assertTrue(estEnsemble(tab))
    }
    @Test
    fun pasEnsemble2() {
        val tab =nutArrayOf(5, 5)
        assertFalse(estEnsemble(tab))
    }
    @Test
    fun pasEnsemble3() {
        val tab =nutArrayOf(3, 2, 3, 4, 5, 6, 8)
        assertFalse(estEnsemble(tab))
    }
    @Test
    fun pasEnsemble8() {
        val tab =nutArrayOf(1, 2, 8, 4, 5, 6, 8)
        assertFalse(estEnsemble(tab))
    }
    @Test
    fun pasEnsemble1() {
        val tab =nutArrayOf(1, 1, 3, 4, 5, 6, 8)
        assertFalse(estEnsemble(tab))
    }
    @Test
    fun pasEnsemble6() {
        val tab =nutArrayOf(1, 2, 8, 4, 5, 6, 6)
        assertFalse(estEnsemble(tab))
    }
}