package com.mlj.practicesrep.blockcanary;

import android.os.Trace;

public class TestAsmPlugin {
    public static void main(String[] args) {
        Trace.beginSection("main");
        Trace.endSection();
    }
}
