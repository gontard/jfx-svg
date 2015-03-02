package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.Polyline;

public class PolylineEqualityTester extends ShapeEqualityTester<Polyline> {

    @Override
    protected void assertShapeEqual(Polyline expected, Polyline found) {
        assertEquals("wrong points", expected.getPoints(), found.getPoints());
    }

}
