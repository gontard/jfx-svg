package org.gontard.jfx.svg.equal;

import javafx.scene.Node;

public interface NodeEqualityTester<N extends Node> {
    void assertEqual(N expected, N found);
}
