package io.github.greymagic27.jna_clone.WinDef;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class BOOL {

    private final int value;

    @Contract(pure = true)
    public BOOL(int value) {
        this.value = value;
    }

    @Contract(pure = true)
    public boolean booleanValue() {
        return value != 0;
    }

    @Contract(pure = true)
    public int intValue() {
        return value;
    }

    @Override
    public @NotNull String toString() {
        return String.valueOf(booleanValue());
    }
}