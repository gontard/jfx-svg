package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.XMLElementBuilder.xmlElement;
import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.shape.Circle;

import org.junit.Test;

public class CircleFactoryTest {

    private Circle createCircle(XmlElement el) {
        return new CircleFactory().create(el);
    }

    @Test
    public void should_read_the_center_and_the_radius() {
        // [Given]
        XmlElement el = xmlElement().withAttribute("cx", "12.2")
                .withAttribute("cy", "17.8")
                .withAttribute("r", "25.17")
                .build();

        // [When]
        Circle circle = createCircle(el);

        // [Then]
        assertDoubleEquals("wrong center x", 12.2, circle.getCenterX());
        assertDoubleEquals("wrong center y", 17.8, circle.getCenterY());
        assertDoubleEquals("wrong radius", 25.17, circle.getRadius());
    }
}
