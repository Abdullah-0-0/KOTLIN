import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class TestCalcul {

    @Test
    fun sum() {
        val attendu = 42
        assertEquals(attendu, sum(40,2))
    }

    @Test
    fun product() {
        val attendu = 42
        assertEquals(attendu, product(21, 2))
    }
}