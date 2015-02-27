package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.XMLElementBuilder.xmlElement;
import static org.junit.Assert.assertEquals;
import javafx.scene.shape.SVGPath;

import org.junit.Test;

public class PathFactoryTest extends ShapeFactoryTest {

    @Override
    protected ShapeFactory createShapeFactory(PaintFactory paintFactory) {
        return new PathFactory(paintFactory);
    }

    private SVGPath createPath(XmlElement el) {
        return (SVGPath) createShape(el);
    }

    @Test
    public void should_set_the_start_and_the_end_points() {
        // [Given]
        String content = "M 100 100 L 300 100 L 200 300 z";
        XmlElement el = xmlElement().withAttribute("d", content).build();

        // [When]
        SVGPath path = createPath(el);

        // [Then]
        assertEquals("wrong content", content, path.getContent());
    }

}
