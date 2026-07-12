package io.github.greymagic27.jna_clone.WinDef;

import org.jspecify.annotations.NonNull;

public class WPARAM {

    private final long value;

    public WPARAM(long value) {
        this.value = value;
    }

    public long longValue() {
        return value;
    }

    @Override
    public @NonNull String toString() {
        return String.valueOf(longValue());
    }
}
