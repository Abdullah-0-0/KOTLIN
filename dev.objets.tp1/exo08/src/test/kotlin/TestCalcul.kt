import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

import org.junit.jupiter.api.Assertions.*

class TestCalcul {
    
    @Test
    fun calcul0() {
        assertEquals(5, calcul(0))
    }

    @Test
    fun calcul10() {
        assertEquals(35, calcul(10))
    }

    @Test
    fun calculMin5() {
        assertEquals(-10, calcul(-5))
    }
}