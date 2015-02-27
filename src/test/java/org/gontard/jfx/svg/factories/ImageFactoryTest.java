package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import static org.gontard.jfx.svg.util.JfxAssert.assertImageEquals;
import static org.junit.Assert.assertTrue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.gontard.jfx.svg.XMLElementBuilder;
import org.gontard.jfx.svg.util.JavaFXThreadingRule;
import org.junit.Rule;
import org.junit.Test;

public class ImageFactoryTest {
    private static String IMG_URL = ImageFactoryTest.class.getResource("/jfx/image.gif").toExternalForm();

    @Rule
    public JavaFXThreadingRule runInJfxThread = new JavaFXThreadingRule();

    private ImageView createImage(XmlElement el) {
        return new ImageFactory().create(el);
    }

    @Test
    public void should_create_an_ImageView_with_default_settings() {
        // [Given]
        XmlElement el = elementWithImage().build();

        // [When]
        ImageView imageView = createImage(el);

        // [Then]
        assertImageEquals(new Image(IMG_URL), imageView.getImage());
        assertTrue("wrong preverve ratio", imageView.isPreserveRatio());
        assertDoubleEquals("wrong preverve ratio", 0, imageView.getX());
        assertDoubleEquals("wrong preverve ratio", 0, imageView.getY());
        assertDoubleEquals("wrong preverve ratio", 0, imageView.getFitWidth());
        assertDoubleEquals("wrong preverve ratio", 0, imageView.getFitHeight());
    }

    @Test
    public void should_create_an_ImageView_with_configured_settings() {
        // [Given]
        XmlElement el = elementWithImage().withAttribute("x", "0.23")
                .withAttribute("x", "0.23")
                .withAttribute("y", "11")
                .withAttribute("width", "12.23")
                .withAttribute("height", "42")
                .build();

        // [When]
        ImageView imageView = createImage(el);

        // [Then]
        assertTrue("wrong preverve ratio", imageView.isPreserveRatio());
        assertDoubleEquals("wrong preverve ratio", 0.23, imageView.getX());
        assertDoubleEquals("wrong preverve ratio", 11, imageView.getY());
        assertDoubleEquals("wrong preverve ratio", 12.23, imageView.getFitWidth());
        assertDoubleEquals("wrong preverve ratio", 42, imageView.getFitHeight());
    }

    private XMLElementBuilder elementWithImage() {
        return XMLElementBuilder.xmlElement().withAttribute("href", IMG_URL);
    }

}
