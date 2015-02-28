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
    
	protected Color getDefaultFill() {
		return Color.BLACK;
	}
    
	protected Color getDefaultStroke() {
		return null;
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
    public void should_set_a_null_fill_if_the_attribute_is_not_present() {
        // [Given]
        XmlElement el = xmlElement().build();

        // [When]
        Shape shape = createShape(el);

        // [Then]
        assertEquals(getDefaultFill(), shape.getFill());
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

    @Test
    public void should_set_a_null_stroke_if_the_attribute_is_not_present() {
        // [Given]
        XmlElement el = xmlElement().build();

        // [When]
        Shape shape = createShape(el);

        // [Then]
        assertEquals(getDefaultStroke(), shape.getStroke());
    }
}
