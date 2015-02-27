package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.XMLElementBuilder.xmlElement;
import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.shape.Ellipse;

import org.junit.Test;

public class EllipseFactoryTest extends ShapeFactoryTest {

    @Override
    protected ShapeFactory createShapeFactory(PaintFactory paintFactory) {
        return new EllipseFactory(paintFactory);
    }

    protected Ellipse createEllipse(XmlElement el) {
        return (Ellipse) super.createShape(el);
    }

    @Test
    public void should_set_the_center_and_the_radius() {
        // [Given]
        XmlElement el = xmlElement().withAttribute("cx", "12.2")
                .withAttribute("cy", "17.8")
                .withAttribute("rx", "25.17")
                .withAttribute("ry", "25.17")
                .build();

        // [When]
        Ellipse ellipse = createEllipse(el);

        // [Then]
        assertDoubleEquals("wrong center x", 12.2, ellipse.getCenterX());
        assertDoubleEquals("wrong center y", 17.8, ellipse.getCenterY());
        assertDoubleEquals("wrong radius x", 25.17, ellipse.getRadiusX());
        assertDoubleEquals("wrong radius y", 25.17, ellipse.getRadiusY());
    }
}
