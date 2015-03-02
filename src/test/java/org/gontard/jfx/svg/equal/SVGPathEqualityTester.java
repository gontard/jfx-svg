package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.SVGPath;

public class SVGPathEqualityTester extends ShapeEqualityTester<SVGPath> {

    @Override
    protected void assertShapeEqual(SVGPath expected, SVGPath found) {
        assertEquals("wrong content", expected.getContent(), found.getContent());
    }

}
