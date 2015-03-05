package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.XMLElementBuilder.xmlElement;
import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.shape.Rectangle;

import org.junit.Test;

public class RectangleFactoryTest {

    private Rectangle createRectangle(XmlElement el) {
        return new RectangleFactory().create(el);
    }

    @Test
    public void should_set_the_bouds() {
        // [Given]
        XmlElement el = xmlElement().withAttribute("x", "0.75")
                .withAttribute("y", "17.3")
                .withAttribute("width", "12.23")
                .withAttribute("height", "13.78")
                .withAttribute("rx", "1.1")
                .withAttribute("ry", "1.2")
                .build();

        // [When]
        Rectangle rectangle = createRectangle(el);

        // [Then]
        assertDoubleEquals("wrong x", 0.75, rectangle.getX());
        assertDoubleEquals("wrong y", 17.3, rectangle.getY());
        assertDoubleEquals("wrong width", 12.23, rectangle.getWidth());
        assertDoubleEquals("wrong height", 13.78, rectangle.getHeight());
        assertDoubleEquals("wrong arc width", 1.1, rectangle.getArcWidth());
        assertDoubleEquals("wrong arc height", 1.2, rectangle.getArcHeight());
    }
}
