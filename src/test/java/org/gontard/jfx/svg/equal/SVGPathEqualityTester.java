package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Transform;

public class SVGPathEqualityTester extends ShapeEqualityTester<SVGPath> {

    public SVGPathEqualityTester(EqualityTester<Transform> transformTester) {
        super(transformTester);
    }

    @Override
    protected void assertShapeEqual(SVGPath expected, SVGPath found) {
        assertEquals("wrong content", expected.getContent(), found.getContent());
    }

}
