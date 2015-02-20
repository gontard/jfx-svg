package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertEquals;
import javafx.scene.shape.SVGPath;

public class SVGPathEqualityTester implements NodeEqualityTester<SVGPath> {

    @Override
    public void assertEqual(SVGPath expected, SVGPath found) {
        assertEquals("wrong content", expected.getContent(), found.getContent());
    }

}
