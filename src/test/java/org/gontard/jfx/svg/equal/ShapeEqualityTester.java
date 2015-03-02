package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.Shape;

public abstract class ShapeEqualityTester<S extends Shape> implements NodeEqualityTester<S> {

    @Override
    public void assertEqual(S expected, S found) {
        assertEquals("wrong paint", expected.getStroke(), found.getStroke());
        assertEquals("wrong fill", expected.getFill(), found.getFill());
        assertShapeEqual(expected, found);
    }

    protected abstract void assertShapeEqual(S expected, S found);

}
