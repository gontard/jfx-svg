package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.Polygon;

public class PolygonEqualityTester extends ShapeEqualityTester<Polygon> {

    @Override
    protected void assertShapeEqual(Polygon expected, Polygon found) {
        assertEquals("wrong points", expected.getPoints(), found.getPoints());
    }

}
