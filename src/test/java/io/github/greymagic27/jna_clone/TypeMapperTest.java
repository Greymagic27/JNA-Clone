package io.github.greymagic27.jna_clone;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TypeMapperTest {

    private static @NotNull Stream<Arguments> provideLayoutMappings() {
        return Stream.of(Arguments.of(int.class, ValueLayout.JAVA_INT), Arguments.of(long.class, ValueLayout.JAVA_LONG), Arguments.of(short.class, ValueLayout.JAVA_SHORT), Arguments.of(byte.class, ValueLayout.JAVA_BYTE), Arguments.of(boolean.class, ValueLayout.JAVA_INT), Arguments.of(double.class, ValueLayout.JAVA_DOUBLE), Arguments.of(float.class, ValueLayout.JAVA_FLOAT), Arguments.of(Integer.class, ValueLayout.JAVA_INT), Arguments.of(String.class, ValueLayout.ADDRESS), Arguments.of(Pointer.class, ValueLayout.ADDRESS), Arguments.of(void.class, null));
    }

    @ParameterizedTest
    @MethodSource("provideLayoutMappings")
    void testLayoutFor(Class<?> type, MemoryLayout expected) {
        assertEquals(expected, TypeMapper.layoutFor(type));
    }

    @Test
    void testLayoutForInvalid() {
        assertThrows(IllegalArgumentException.class, () -> TypeMapper.layoutFor(Object.class));
    }

    @Test
    void testToNative_String() {
        try (Arena arena = Arena.ofConfined()) {
            Object result = TypeMapper.toNative("test string", String.class, arena);
            assertInstanceOf(MemorySegment.class, result);
            assertNotEquals(MemorySegment.NULL, result);
        }
    }

    @Test
    void testToNative_Pointer() {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment segment = arena.allocate(4);
            Pointer ptr = new Pointer(segment);
            Object result = TypeMapper.toNative(ptr, Pointer.class, arena);
            assertEquals(segment, result);
        }
    }

    @Test
    void testToNative_Boolean() {
        try (Arena arena = Arena.ofConfined()) {
            assertEquals(1, TypeMapper.toNative(true, boolean.class, arena));
            assertEquals(0, TypeMapper.toNative(false, Boolean.class, arena));
        }
    }

    @Test
    void testToNative_Null() {
        try (Arena arena = Arena.ofConfined()) {
            assertEquals(MemorySegment.NULL, TypeMapper.toNative(null, String.class, arena));
            assertEquals(0, TypeMapper.toNative(null, Integer.class, arena));
        }
    }

    @Test
    void testFromNative_Pointer() {
        MemorySegment segment = MemorySegment.NULL;
        Object result = TypeMapper.fromNative(segment, Pointer.class);
        assertInstanceOf(Pointer.class, result);
        assertTrue(((Pointer) result).isNull());
    }

    @Test
    void testFromNative_Boolean() {
        assertEquals(true, TypeMapper.fromNative(1, boolean.class));
        assertEquals(false, TypeMapper.fromNative(0, Boolean.class));
    }

    @Test
    void testFromNative_Void() {
        assertNull(TypeMapper.fromNative(null, void.class));
    }

    @Test
    void testFromNative_Identity() {
        String test = "test";
        assertEquals(test, TypeMapper.fromNative(test, String.class));
    }
}