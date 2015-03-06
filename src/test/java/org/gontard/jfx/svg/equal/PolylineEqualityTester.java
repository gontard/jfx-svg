package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Transform;

public class PolylineEqualityTester extends ShapeEqualityTester<Polyline> {

    public PolylineEqualityTester(EqualityTester<Transform> transformTester) {
        super(transformTester);
    }

    @Override
    protected void assertShapeEqual(Polyline expected, Polyline found) {
        assertEquals("wrong points", expected.getPoints(), found.getPoints());
    }

}
