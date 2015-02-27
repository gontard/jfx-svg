package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.XMLElementBuilder.xmlElement;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javafx.scene.shape.Polyline;

import org.junit.Test;


public class PolylineFactoryTest extends ShapeFactoryTest {

    @Override
    protected ShapeFactory createShapeFactory(PaintFactory paintFactory) {
        return new PolylineFactory(paintFactory);
    }

    private Polyline createPolyline(XmlElement el) {
        return (Polyline) createShape(el);
    }

    @Test
    public void should_set_the_points() {
        // [Given]
        String content = "100,80 60.31,-20.3 100,40";
        XmlElement el = xmlElement().withAttribute("points", content).build();

        // [When]
        Polyline polygon = createPolyline(el);

        // [Then]
        assertEquals("wrong points", Arrays.asList(100d, 80d, 60.31, -20.3, 100d, 40d), polygon.getPoints());
    }

}
