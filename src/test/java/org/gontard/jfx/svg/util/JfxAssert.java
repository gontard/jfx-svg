/*
 * Created on 13 févr. 2015
 * @author intactile
 * Owner : Intactile Design
 * All rights reserved
 */
package org.gontard.jfx.svg.util;

import static org.junit.Assert.assertEquals;

public class JfxAssert {
    // Constants .............................................................................................

    // Class fields ..........................................................................................

    // Class methods .........................................................................................
    public static void assertDoubleEquals(String msg, double expected, double found) {
        assertEquals(msg, expected, found, 0.001d);
    }

    // Instance fields .......................................................................................

    // Constructors ..........................................................................................
    private JfxAssert() {}

    // Getters / Setters .....................................................................................

    // Interface implementations .............................................................................

    // Abstract methods implementations ......................................................................

    // Methods ...............................................................................................

    // Inner classes .........................................................................................
}
