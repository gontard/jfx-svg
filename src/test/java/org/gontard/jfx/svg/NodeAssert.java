/*
 * Created on 18 févr. 2015
 * @author intactile
 * Owner : Intactile Design
 * All rights reserved
 */
package org.gontard.jfx.svg;

import static org.junit.Assert.assertSame;
import javafx.scene.Node;

public class NodeAssert {
    // Constants .............................................................................................

    // Class fields ..........................................................................................

    // Class methods .........................................................................................
    public static void assertNodeEquals(Node expected, Node found) {
        assertSame("wrong class", expected, found);
    }

    // Instance fields .......................................................................................

    // Constructors ..........................................................................................

    // Getters / Setters .....................................................................................

    // Interface implementations .............................................................................

    // Abstract methods implementations ......................................................................

    // Methods ...............................................................................................

    // Inner classes .........................................................................................
}
