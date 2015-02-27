package org.gontard.jfx.svg.equal;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import static org.gontard.jfx.svg.util.JfxAssert.assertImageEquals;
import static org.junit.Assert.assertSame;
import javafx.scene.image.ImageView;

public class ImageViewEqualityTester implements NodeEqualityTester<ImageView> {

    @Override
    public void assertEqual(ImageView expected, ImageView found) {
        assertSame("wrong preserve ratio",
                expected.preserveRatioProperty().get(),
                found.preserveRatioProperty().get());
        assertDoubleEquals("wrong x", expected.getX(), found.getX());
        assertDoubleEquals("wrong y", expected.getY(), found.getY());
        assertDoubleEquals("wrong fit width", expected.getFitWidth(), found.getFitWidth());
        assertDoubleEquals("wrong fit height", expected.getFitHeight(), found.getFitHeight());
        assertImageEquals(expected.getImage(), found.getImage());
    }
}
