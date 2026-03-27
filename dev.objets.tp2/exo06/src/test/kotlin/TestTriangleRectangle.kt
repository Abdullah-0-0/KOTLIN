import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
//import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource

import org.junit.jupiter.api.Assertions.*

class TestTriangleRectangle {
    
    @Test
    fun `estRectangle1`() {
        assertTrue(estRectangle(5.0,4.0,3.0))
    }

    @Test
    fun `estRectangle2`() {
        assertFalse(estRectangle(4.0,5.0,3.0))
    }

    @Test
    fun `estRectangle3`() {
        assertFalse(estRectangle(4.0,3.0,5.0))
    }

    @Test
    fun `estRectangle4`() {
        assertFalse(estRectangle(3.0,5.0,4.0))
    }

    @Test
    fun `estRectangle5`() {
        assertFalse(estRectangle(8.0,10.0,6.0))
    }

    @Test
    fun `estRectangle51`() {
        assertTrue(estRectangle(10.0,8.0,6.0))
    }

    @Test
    fun `estRectangle6`() {
        assertTrue(estRectangle(15.0,9.0,12.0))
    }

    @Test
    fun `estRectangle61`() {
        assertFalse(estRectangle(9.0,15.0,12.0))
    }

    @Test
    fun `estRectangle7`() {
        assertFalse(estRectangle(4059.0,4060.0,5741.0))
    }

    @Test
    fun `estRectangle71`() {
        assertTrue(estRectangle(5741.0,4059.0,4060.0))
    }

}