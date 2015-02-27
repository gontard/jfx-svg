package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.XMLElementBuilder.xmlElement;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class ShapeFactoryTest {
    @Mock
    private PaintFactory paintFactory;

    protected abstract ShapeFactory createShapeFactory(PaintFactory paintFactory);

    private ShapeFactory createShapeFactory() {
        return createShapeFactory(paintFactory);
    }

    protected Shape createShape(XmlElement el) {
        return createShapeFactory().create(el);
    }

    @Before
    public void prepareMocks() {
        when(paintFactory.create("red")).thenReturn(Color.RED);
        when(paintFactory.create("gray")).thenReturn(Color.GRAY);
    }

    @Test
    public void should_set_the_parsed_fill() {
        // [Given]
        XmlElement el = xmlElement().withAttribute("fill", "red").build();

        // [When]
        Shape shape = createShape(el);

        // [Then]
        assertEquals(Color.RED, shape.getFill());
    }

    @Test
    public void should_set_the_parsed_stroke() {
        // [Given]
        XmlElement el = xmlElement().withAttribute("stroke", "gray").build();

        // [When]
        Shape shape = createShape(el);

        // [Then]
        assertEquals(Color.GRAY, shape.getStroke());
    }
}
