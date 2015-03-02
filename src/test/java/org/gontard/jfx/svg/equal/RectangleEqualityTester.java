package org.gontard.jfx.svg.equal;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.shape.Rectangle;

class RectangleEqualityTester extends ShapeEqualityTester<Rectangle> {

    @Override
    protected void assertShapeEqual(Rectangle expected, Rectangle found) {
        assertDoubleEquals("wrong x", expected.getX(), found.getX());
        assertDoubleEquals("wrong y", expected.getY(), found.getY());
        assertDoubleEquals("wrong w", expected.getWidth(), found.getWidth());
        assertDoubleEquals("wrong h", expected.getHeight(), found.getHeight());
        assertDoubleEquals("wrong arcWidth", expected.getArcWidth(), found.getArcWidth());
        assertDoubleEquals("wrong arcHeight", expected.getArcHeight(), found.getArcHeight());
    }

}
