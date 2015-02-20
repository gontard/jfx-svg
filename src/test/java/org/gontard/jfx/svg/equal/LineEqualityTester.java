package org.gontard.jfx.svg.equal;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.shape.Line;

public class LineEqualityTester implements NodeEqualityTester<Line> {

    @Override
    public void assertEqual(Line expected, Line found) {
        assertDoubleEquals("wrong start x", expected.getStartX(), found.getStartX());
        assertDoubleEquals("wrong start y", expected.getStartY(), found.getStartY());
        assertDoubleEquals("wrong end x", expected.getEndX(), found.getEndX());
        assertDoubleEquals("wrong end y", expected.getEndY(), found.getEndY());
    }

}
