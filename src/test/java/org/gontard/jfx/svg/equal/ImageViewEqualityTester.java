package org.gontard.jfx.svg.equal;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;

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

    private void assertImageEquals(Image expectedImage, Image foundImage) {
        assertNotNull("expected image is null", expectedImage);
        assertNotNull("found image is null", foundImage);
        assertDoubleEquals("wrong image width", expectedImage.getWidth(), foundImage.getWidth());
        assertDoubleEquals("wrong image height", expectedImage.getHeight(), foundImage.getHeight());
        PixelReader expectedReader = expectedImage.getPixelReader();
        PixelReader foundReader = foundImage.getPixelReader();
        for (int x = 0; x < expectedImage.getWidth(); x++) {
            for (int y = 0; y < expectedImage.getHeight(); y++) {
                assertEquals("wrong pixel at " + x + ";" + y,
                        expectedReader.getArgb(x, y),
                        foundReader.getArgb(x, y));
            }
        }
    }
}
