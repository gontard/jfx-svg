package org.gontard.jfx.svg.util;

import static org.junit.Assert.assertEquals;

public class JfxAssert {
	
    public static void assertDoubleEquals(String msg, double expected, double found) {
        assertEquals(msg, expected, found, 0.001d);
    }

    private JfxAssert() {}

}
