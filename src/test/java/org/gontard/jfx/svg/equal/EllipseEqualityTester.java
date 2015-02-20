package org.gontard.jfx.svg.equal;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.shape.Ellipse;

public class EllipseEqualityTester implements NodeEqualityTester<Ellipse> {

    @Override
    public void assertEqual(Ellipse expected, Ellipse found) {
        assertDoubleEquals("wrong center x", expected.getCenterX(), found.getCenterX());
        assertDoubleEquals("wrong center y", expected.getCenterY(), found.getCenterY());
        assertDoubleEquals("wrong radius x", expected.getRadiusX(), found.getRadiusX());
        assertDoubleEquals("wrong radius y", expected.getRadiusY(), found.getRadiusY());
    }

}
