package io.github.greymagic27.jna_clone;

import java.lang.foreign.MemorySegment;

public class Handle {

    private final MemorySegment segment;

    public Handle(MemorySegment segment) {
        this.segment = segment;
    }

    public MemorySegment segment() {
        return segment;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@0x" + Long.toHexString(segment.address());
    }
}
