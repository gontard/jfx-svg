package org.gontard.jfx.svg;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

import javax.xml.stream.XMLStreamException;

import org.junit.Test;

public class JfxSvgRendererOldTest {
    private static final String JFX_SVG = "/jfx/svg/";

    private static final String RECTANGLE = JFX_SVG + "rectangle.svg";
    private static final String CIRCLE = JFX_SVG + "circle.svg";
    private static final String ELLIPSE = JFX_SVG + "ellipse.svg";
    private static final String LINE = JFX_SVG + "line.svg";
    private static final String POLYLINE = JFX_SVG + "polyline.svg";
    private static final String POLYGON = JFX_SVG + "polygon.svg";
    private static final String PATH = JFX_SVG + "path.svg";
    private static final String GROUP = JFX_SVG + "group.svg";

    @Test
    public void should_load_a_rectangle() throws XMLStreamException {
        Node node = load(RECTANGLE);

        assertThat(node, instanceOf(Rectangle.class));
        Rectangle rectangle = (Rectangle) node;
        assertDoubleEquals("wrong x", 50, rectangle.getX());
        assertDoubleEquals("wrong y", 100, rectangle.getY());
        assertDoubleEquals("wrong w", 200, rectangle.getWidth());
        assertDoubleEquals("wrong h", 150, rectangle.getHeight());
        assertDoubleEquals("wrong arcWidth", 15, rectangle.getArcWidth());
        assertDoubleEquals("wrong arcHeight", 20, rectangle.getArcHeight());
    }

    @Test
    public void should_load_a_circle() throws XMLStreamException {
        Node node = load(CIRCLE);

        assertThat(node, instanceOf(Circle.class));
        Circle rectangle = (Circle) node;
        assertDoubleEquals("wrong cx", 60, rectangle.getCenterX());
        assertDoubleEquals("wrong cy", 75, rectangle.getCenterY());
        assertDoubleEquals("wrong radius", 50, rectangle.getRadius());
    }

    @Test
    public void should_load_an_ellipse() throws XMLStreamException {
        Node node = load(ELLIPSE);

        assertThat(node, instanceOf(Ellipse.class));
        Ellipse ellipse = (Ellipse) node;
        assertDoubleEquals("wrong cx", 45, ellipse.getCenterX());
        assertDoubleEquals("wrong cy", 60, ellipse.getCenterY());
        assertDoubleEquals("wrong radiusX", 50, ellipse.getRadiusX());
        assertDoubleEquals("wrong radiusY", 25, ellipse.getRadiusY());
    }

    @Test
    public void should_load_a_line() throws XMLStreamException {
        Node node = load(LINE);

        assertThat(node, instanceOf(Line.class));
        Line line = (Line) node;
        assertDoubleEquals("wrong start x", 20, line.getStartX());
        assertDoubleEquals("wrong start y", 100, line.getStartY());
        assertDoubleEquals("wrong end x", 120, line.getEndX());
        assertDoubleEquals("wrong end y", 150, line.getEndY());
    }

    @Test
    public void should_load_a_polyline() throws XMLStreamException {
        Node node = load(POLYLINE);

        assertThat(node, instanceOf(Polyline.class));
        Polyline polyline = (Polyline) node;
        assertEquals("wrong points", asList(-20d, 100d, 40d, 60d, 70d, 80d, 100d, 20d), polyline.getPoints());
    }

    @Test
    public void should_load_a_polygon() throws XMLStreamException {
        Node node = load(POLYGON);

        assertThat(node, instanceOf(Polygon.class));
        Polygon polygon = (Polygon) node;
        assertEquals("wrong points",
                asList(60d, 20d, 100d, 40d, 100d, 80d, 60d, 100d, 20d, 80d, 20d, 40d),
                polygon.getPoints());
    }

    @Test
    public void should_load_a_path() throws XMLStreamException {
        Node node = load(PATH);

        assertThat(node, instanceOf(SVGPath.class));
        SVGPath path = (SVGPath) node;
        assertEquals("wrong path content", "M 100 100 L 300 100 L 200 300 z", path.getContent());
    }

    @Test
    public void should_load_a_group() throws XMLStreamException {
        Node node = load(GROUP);

        assertThat(node, instanceOf(Group.class));
        Group group = (Group) node;
        ObservableList<Node> children = group.getChildren();
        assertEquals("wrong child count", 3, children.size());
        assertThat(children.get(0), instanceOf(Circle.class));
        assertThat(children.get(1), instanceOf(Rectangle.class));
        assertThat(children.get(2), instanceOf(Line.class));
    }

    private Node load(String resource) throws XMLStreamException {
        return new JfxSvgRenderer().load(getClass().getResourceAsStream(resource));
    }

    private void assertDoubleEquals(String msg, double expected, double found) {
        assertEquals(msg, expected, found, 0.001d);
    }

}
