package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.Polygon;

public class PolygonEqualityTester implements NodeEqualityTester<Polygon> {

    @Override
    public void assertEqual(Polygon expected, Polygon found) {
        assertEquals("wrong points", expected.getPoints(), found.getPoints());
    }

}
