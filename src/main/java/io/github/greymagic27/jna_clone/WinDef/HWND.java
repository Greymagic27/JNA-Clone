package io.github.greymagic27.jna_clone.WinDef;

import io.github.greymagic27.jna_clone.Handle;
import java.lang.foreign.MemorySegment;

public class HWND extends Handle {

    public HWND(MemorySegment segment) {
        super(segment);
    }
}
