package io.github.greymagic27.jna_clone;

import io.github.greymagic27.jna_clone.WinDef.BOOL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoolTest {

    @Test
    void testBOOLValue_Zero() {
        assertFalse(new BOOL(0).booleanValue());
    }

    @Test
    void testBOOLValue_One() {
        assertTrue(new BOOL(1).booleanValue());
    }

    @Test
    void testBOOLValue_NonZeroNonOneAreTrue() {
        assertTrue(new BOOL(-1).booleanValue());
        assertTrue(new BOOL(42).booleanValue());
    }

    @Test
    void testIntValueRoundTrips() {
        assertEquals(7, new BOOL(7).intValue());
    }

    @Test
    void testToString() {
        assertEquals("true", new BOOL(1).toString());
        assertEquals("true", new BOOL(999).toString());
        assertEquals("false", new BOOL(0).toString());
    }
}
