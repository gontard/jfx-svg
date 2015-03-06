package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Transform;

public class PolygonEqualityTester extends ShapeEqualityTester<Polygon> {

    public PolygonEqualityTester(EqualityTester<Transform> transformTester) {
        super(transformTester);
    }

    @Override
    protected void assertShapeEqual(Polygon expected, Polygon found) {
        assertEquals("wrong points", expected.getPoints(), found.getPoints());
    }

}
