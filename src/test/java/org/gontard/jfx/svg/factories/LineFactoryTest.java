package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.XMLElementBuilder.xmlElement;
import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.shape.Line;

import org.junit.Test;

public class LineFactoryTest extends ShapeFactoryTest {

    @Override
    protected ShapeFactory createShapeFactory(PaintFactory paintFactory) {
        return new LineFactory(paintFactory);
    }

    private Line createLine(XmlElement el) {
        return (Line) createShape(el);
    }

    @Test
    public void should_set_the_start_and_the_end_points() {
        // [Given]
        XmlElement el = xmlElement().withAttribute("x1", "12.2")
                .withAttribute("y1", "17.8")
                .withAttribute("x2", "25.17")
                .withAttribute("y2", "25.17")
                .build();

        // [When]
        Line line = createLine(el);

        // [Then]
        assertDoubleEquals("wrong start x", 12.2, line.getStartX());
        assertDoubleEquals("wrong start y", 17.8, line.getStartY());
        assertDoubleEquals("wrong end x", 25.17, line.getEndX());
        assertDoubleEquals("wrong end y", 25.17, line.getEndY());
    }

}
