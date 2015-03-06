package org.gontard.jfx.svg;

import java.io.InputStream;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.gontard.jfx.svg.factories.CircleFactory;
import org.gontard.jfx.svg.factories.EllipseFactory;
import org.gontard.jfx.svg.factories.Factory;
import org.gontard.jfx.svg.factories.ImageFactory;
import org.gontard.jfx.svg.factories.LineFactory;
import org.gontard.jfx.svg.factories.PaintFactory;
import org.gontard.jfx.svg.factories.PathFactory;
import org.gontard.jfx.svg.factories.PolygonFactory;
import org.gontard.jfx.svg.factories.PolylineFactory;
import org.gontard.jfx.svg.factories.RectangleFactory;
import org.gontard.jfx.svg.factories.XmlElement;

public class JfxSvgRenderer {
    private final Map<String, Factory> factoryRegistry = new HashMap<String, Factory>();
    private final PaintFactory paintFactory = new PaintFactory();

    public JfxSvgRenderer() {
        factoryRegistry.put("circle", new CircleFactory());
        factoryRegistry.put("ellipse", new EllipseFactory());
        factoryRegistry.put("image", new ImageFactory());
        factoryRegistry.put("line", new LineFactory());
        factoryRegistry.put("polyline", new PolylineFactory());
        factoryRegistry.put("polygon", new PolygonFactory());
        factoryRegistry.put("path", new PathFactory());
        factoryRegistry.put("rect", new RectangleFactory());
    }

    private Factory getFactory(String name) {
        return factoryRegistry.getOrDefault(name, (el) -> {
            throw new NotSupportedException(name);
        });
    }

    public Node load(InputStream svgStream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(svgStream);
        Node root = null;
        Deque<Group> groups = new LinkedList<>();
        Style style = createRooStyle();
        while (reader.hasNext()) {
            int event = reader.next();
            String localName;
            switch (event) {
                case XMLStreamConstants.END_ELEMENT:
                    localName = reader.getLocalName();
                    if (isGroup(localName)) {
                        groups.pop();
                    }
                    style = style.parent();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    localName = reader.getLocalName();
                    XmlElement el = new StaXmlElement(reader);
                    Node node = null;
                    Group newGroup = null;
                    if (isGroup(localName)) {
                        newGroup = new Group();
                        node = newGroup;
                    } else {
                        node = getFactory(localName).create(el);
                    }
                    if (root == null) {
                        root = node;
                    }
                    if (!groups.isEmpty()) {
                        groups.peek().getChildren().add(node);
                    }
                    if (newGroup != null) {
                        groups.push(newGroup);
                    }

                    style = createStyle(style, el);
                    style.applyStyle(node);
            }
        }
        return root;
    }

    private boolean isGroup(String localName) {
        return "g".equals(localName) || "svg".equals(localName);
    }

    private Style createRooStyle() {
        return new Style();
    }

    private Style createStyle(Style style, XmlElement el) {
        double strokeWidth = el.getDouble("stroke-width", -1);
        return new Style(style,
                paintFactory.create(el.getString("fill")),
                paintFactory.create(el.getString("stroke")),
                strokeWidth == -1 ? null : strokeWidth);
    }

    public static class NotSupportedException extends RuntimeException {
        private NotSupportedException(String element) {
            super(element + " is not supported");
        }
    }

    private static class StaXmlElement implements XmlElement {
        private final XMLStreamReader reader;

        private StaXmlElement(XMLStreamReader reader) {
            this.reader = reader;
        }

        @Override
        public String getString(String name) {
            return reader.getAttributeValue(null, name);
        }
    }

    private static class Style {
        private final Style parent;
        private final Paint fill;
        private final Paint stroke;
        private final Double strokeWidth;

        Style() {
            this(null, null, null, null);
        }

        Style(Style parentStyle, Paint fill, Paint stroke, Double strokeWidth) {
            this.parent = parentStyle;
            this.fill = fill;
            this.stroke = stroke;
            this.strokeWidth = strokeWidth;
        }

        private <T> Optional<T> resolveProperty(Function<Style, T> getter) {
            Optional<T> thisValue = Optional.ofNullable(getter.apply(this));
            if (!thisValue.isPresent() && parent != null) {
                thisValue = parent.resolveProperty(getter);
            }
            return thisValue;
        }

        private Paint getFill() {
            return fill;
        }

        private Paint getStroke() {
            return stroke;
        }

        private Double getStrokeWidth() {
            return strokeWidth;
        }

        private Optional<Paint> fill() {
            return resolveProperty(Style::getFill);
        }

        private Optional<Paint> stroke() {
            return resolveProperty(Style::getStroke);
        }

        private Optional<Double> strokeWidth() {
            return resolveProperty(Style::getStrokeWidth);
        }

        void applyStyle(Node node) {
            if (node instanceof Shape) {
                Shape shape = (Shape) node;
                fill().ifPresent(shape::setFill);
                stroke().ifPresent(shape::setStroke);
                strokeWidth().ifPresent(shape::setStrokeWidth);
            }
        }

        Style parent() {
            return parent;
        }
    }

}
