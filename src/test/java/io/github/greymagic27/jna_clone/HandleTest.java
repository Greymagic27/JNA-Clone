package io.github.greymagic27.jna_clone;

import io.github.greymagic27.jna_clone.WinDef.HDC;
import io.github.greymagic27.jna_clone.WinDef.HWND;
import java.lang.foreign.MemorySegment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandleTest {

    @Test
    void testHandleToStringIncludesSubclassName() {
        HWND hwnd = new HWND(MemorySegment.ofAddress(0xABCD));
        HDC hdc = new HDC(MemorySegment.ofAddress(0xAF));
        HANDLE handle = new HANDLE(MemorySegment.ofAddress(0xAE));
        assertEquals("HWND@0xabcd", hwnd.toString());
        assertEquals("HDC@0xaf", hdc.toString());
        assertEquals("HANDLE@0xae", handle.toString());
    }

    @Test
    void testSegmentAccess() {
        MemorySegment segment = MemorySegment.ofAddress(0x42);
        HANDLE handle = new HANDLE(segment);
        assertEquals(segment, handle.segment());
    }

    @Test
    void testLongConstructor_MatchesMemorySegmentConstructor() {
        HANDLE fromLong = new HANDLE(0x1234);
        HANDLE fromSegment = new HANDLE(MemorySegment.ofAddress(0x1234));
        assertEquals(fromSegment.segment().address(), fromLong.segment().address());
    }

    @Test
    void testLongConstructor_Zero() {
        HANDLE handle = new HANDLE(0);
        assertTrue(handle.isNull());
        assertEquals(0, handle.segment().address());
    }

    @Test
    void testHandleNullIsNull() {
        assertTrue(HANDLE.NULL.isNull());
        assertEquals(0, HANDLE.NULL.segment().address());
    }

    @Test
    void testHandleNullToString() {
        assertEquals("HANDLE@0x0", HANDLE.NULL.toString());
    }

    @Test
    void testNullNonZeroAddresses() {
        assertFalse(new HANDLE(0x1).isNull());
        assertTrue(new HANDLE(0).isNull());
    }
}
