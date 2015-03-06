package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.transform.Transform;

public abstract class NodeEqualityTester<N extends Node> implements EqualityTester<N> {
    private final EqualityTester<Transform> transformTester;

    public NodeEqualityTester(EqualityTester<Transform> transformTester) {
        this.transformTester = transformTester;
    }

    @Override
    public void assertEqual(N expected, N found) {
        assertEqual(expected.getTransforms(), found.getTransforms());

        assertNodeEqual(expected, found);
    }

    private void assertEqual(ObservableList<Transform> expected, ObservableList<Transform> found) {
        assertEquals("wrong amout transformations", expected.size(), found
                .size());
        for (int i = 0; i < expected.size(); i++) {
            transformTester.assertEqual(expected.get(i), found.get(i));
        }
    }

    protected abstract void assertNodeEqual(N expected, N found);

}
