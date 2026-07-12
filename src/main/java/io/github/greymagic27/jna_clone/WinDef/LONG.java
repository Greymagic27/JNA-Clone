package io.github.greymagic27.jna_clone.WinDef;

import org.jspecify.annotations.NonNull;

public class LONG {

    private final int value;

    public LONG(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LONG other)) return false;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public @NonNull String toString() {
        return String.valueOf(intValue());
    }
}
