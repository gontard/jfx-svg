package org.gontard.jfx.svg.equal;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Transform;

public class CircleEqualityTester extends ShapeEqualityTester<Circle> {

    public CircleEqualityTester(EqualityTester<Transform> transformTester) {
        super(transformTester);
    }

    @Override
    protected void assertShapeEqual(Circle expected, Circle found) {
        assertDoubleEquals("wrong center x", expected.getCenterX(), found.getCenterX());
        assertDoubleEquals("wrong center y", expected.getCenterY(), found.getCenterY());
        assertDoubleEquals("wrong radius", expected.getRadius(), found.getRadius());
    }

}
