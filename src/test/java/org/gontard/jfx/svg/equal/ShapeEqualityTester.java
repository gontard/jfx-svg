package org.gontard.jfx.svg.equal;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import static org.junit.Assert.assertEquals;
import javafx.scene.shape.Shape;

public abstract class ShapeEqualityTester<S extends Shape> implements NodeEqualityTester<S> {

    @Override
    public void assertEqual(S expected, S found) {
        assertEquals("wrong fill", expected.getFill(), found.getFill());
        assertEquals("wrong stroke", expected.getStroke(), found.getStroke());
        assertDoubleEquals("wrong stroke width", expected.getStrokeWidth(), found.getStrokeWidth());
        assertShapeEqual(expected, found);
    }

    protected abstract void assertShapeEqual(S expected, S found);

}
