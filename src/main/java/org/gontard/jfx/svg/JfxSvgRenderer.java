package org.gontard.jfx.svg;

import java.io.InputStream;
import java.util.Collection;
import java.util.StringTokenizer;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class JfxSvgRenderer {

    public Node load(InputStream svgStream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(svgStream);
        Node root = null;
        Node node = null;
        Group group = null;
        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String localName = reader.getLocalName();
                    switch (localName) {
                        case "g":
                            group = new Group();
                            node = group;
                            break;
                        case "rect":
                            Rectangle rectangle = new Rectangle();
                            rectangle.setX(doubleAttr(reader, "x"));
                            rectangle.setY(doubleAttr(reader, "y"));
                            rectangle.setWidth(doubleAttr(reader, "width"));
                            rectangle.setHeight(doubleAttr(reader, "height"));
                            rectangle.setArcWidth(doubleAttr(reader, "rx"));
                            rectangle.setArcHeight(doubleAttr(reader, "ry"));
                            node = rectangle;
                            break;
                        case "circle":
                            Circle circle = new Circle();
                            circle.setCenterX(doubleAttr(reader, "cx"));
                            circle.setCenterY(doubleAttr(reader, "cy"));
                            circle.setRadius(doubleAttr(reader, "r"));
                            node = circle;
                            break;
                        case "ellipse":
                            Ellipse ellipse = new Ellipse();
                            ellipse.setCenterX(doubleAttr(reader, "cx"));
                            ellipse.setCenterY(doubleAttr(reader, "cy"));
                            ellipse.setRadiusX(doubleAttr(reader, "rx"));
                            ellipse.setRadiusY(doubleAttr(reader, "ry"));
                            node = ellipse;
                            break;
                        case "line":
                            Line line = new Line();
                            line.setStartX(doubleAttr(reader, "x1"));
                            line.setStartY(doubleAttr(reader, "y1"));
                            line.setEndX(doubleAttr(reader, "x2"));
                            line.setEndY(doubleAttr(reader, "y2"));
                            node = line;
                            break;
                        case "polyline":
                            Polyline polyline = new Polyline();
                            readPoints(reader, polyline.getPoints());
                            node = polyline;
                            break;
                        case "polygon":
                            Polygon polygon = new Polygon();
                            readPoints(reader, polygon.getPoints());
                            node = polygon;
                            break;
                        case "path":
                            SVGPath path = new SVGPath();
                            path.setContent(attr(reader, "d"));
                            node = path;
                            break;

                        default:
                            break;
                    }
                    if (node != null && group != null && group != node) {
                        group.getChildren().add(node);
                    }
                    if (root == null) {
                        root = node;
                    }
            }
        }
        return root;
    }

    private void readPoints(XMLStreamReader reader, Collection<Double> points) {
        String pointsValue = attr(reader, "points");
        parsePoints(pointsValue, points);
    }

    private void parsePoints(String pointsValue, Collection<Double> points) {
        StringTokenizer tokenizer = new StringTokenizer(pointsValue);
        while (tokenizer.hasMoreTokens()) {
            String[] coordinates = tokenizer.nextToken().split(",");
            points.add(Double.valueOf(coordinates[0]));
            points.add(Double.valueOf(coordinates[1]));
        }
    }

    private double doubleAttr(XMLStreamReader reader, String name) {
        return doubleAttr(reader, name, 0);
    }

    private double doubleAttr(XMLStreamReader reader, String name, double def) {
        String attributeValue = attr(reader, name);
        return attributeValue == null ? def : Double.valueOf(attributeValue);
    }

    private String attr(XMLStreamReader reader, String name) {
        return reader.getAttributeValue(null, name);
    }

}
