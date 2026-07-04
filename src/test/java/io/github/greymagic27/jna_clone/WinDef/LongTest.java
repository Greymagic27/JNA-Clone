package io.github.greymagic27.jna_clone.WinDef;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongTest {

    @Test
    void testConstructorStoresValue() {
        assertEquals(42, new LONG(42).longValue());
        assertEquals(0, new LONG(0).longValue());
        assertEquals(-123, new LONG(-123).longValue());
    }

    @Test
    void testLongValue() {
        assertEquals(7L, new LONG(7).longValue());
    }

    @Test
    void testToStringContainsValue() {
        assertTrue(new LONG(777).toString().contains("777"));
    }
}
