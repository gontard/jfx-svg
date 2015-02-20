package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.Polyline;

public class PolylineEqualityTester implements NodeEqualityTester<Polyline> {

    @Override
    public void assertEqual(Polyline expected, Polyline found) {
        assertEquals("wrong points", expected.getPoints(), found.getPoints());
    }

}
