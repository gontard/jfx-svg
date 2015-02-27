package org.gontard.jfx.svg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class JfxAssert {

    public static void assertDoubleEquals(String msg, double expected, double found) {
        assertEquals(msg, expected, found, 0.001d);
    }

    public static void assertImageEquals(Image expectedImage, Image foundImage) {
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

    private JfxAssert() {}

}
