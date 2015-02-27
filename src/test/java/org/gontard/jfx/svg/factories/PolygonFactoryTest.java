package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.XMLElementBuilder.xmlElement;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javafx.scene.shape.Polygon;

import org.junit.Test;

public class PolygonFactoryTest extends ShapeFactoryTest {

    @Override
    protected ShapeFactory createShapeFactory(PaintFactory paintFactory) {
        return new PolygonFactory(paintFactory);
    }

    private Polygon createPolygon(XmlElement el) {
        return (Polygon) createShape(el);
    }

    @Test
    public void should_set_the_points() {
        // [Given]
        String content = "60.31,-20.3 100,40 100,80";
        XmlElement el = xmlElement().withAttribute("points", content).build();

        // [When]
        Polygon polygon = createPolygon(el);

        // [Then]
        assertEquals("wrong points", Arrays.asList(60.31, -20.3, 100d, 40d, 100d, 80d), polygon.getPoints());
    }

}
