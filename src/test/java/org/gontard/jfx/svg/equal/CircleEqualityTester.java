package org.gontard.jfx.svg.equal;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.shape.Circle;

public class CircleEqualityTester implements NodeEqualityTester<Circle> {

    @Override
    public void assertEqual(Circle expected, Circle found) {
        assertDoubleEquals("wrong center x", expected.getCenterX(), found.getCenterX());
        assertDoubleEquals("wrong center y", expected.getCenterY(), found.getCenterY());
        assertDoubleEquals("wrong radius", expected.getRadius(), found.getRadius());
    }

}
