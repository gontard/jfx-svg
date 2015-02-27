package org.gontard.jfx.svg;

import java.io.InputStream;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;

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
    public JfxSvgRenderer() {
        PaintFactory paintFactory = new PaintFactory();
        factoryRegistry.put("circle", new CircleFactory(paintFactory));
        factoryRegistry.put("ellipse", new EllipseFactory());
        factoryRegistry.put("image", new ImageFactory());
        factoryRegistry.put("line", new LineFactory());
        factoryRegistry.put("polyline", new PolylineFactory());
        factoryRegistry.put("polygon", new PolygonFactory());
        factoryRegistry.put("path", new PathFactory());
        factoryRegistry.put("rect", new RectangleFactory());
    }

    private Factory getFactory(String name) {
        return factoryRegistry.getOrDefault(name, (el) -> { throw new NotSupportedException(name); });
    }

    public Node load(InputStream svgStream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(svgStream);
        Node root = null;
        Deque<Group> groups = new LinkedList<>();
        while (reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamConstants.END_ELEMENT:
                    if ("g".equals(reader.getLocalName())) {
                        groups.pop();
                    }
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    Node node = null;
                    Group newGroup = null;
                    String localName = reader.getLocalName();
                    if ("g".equals(localName)) {
                        newGroup = new Group();
                        node = newGroup;
                    }
                    else if (!"svg".equals(localName)){
                        node = getFactory(localName).create(new StaXmlElement(reader));
                    }
                    if (node != null) {
                        if (root == null) {
                            root = node;
                        }
                        if (!groups.isEmpty()) {
                            groups.peek().getChildren().add(node);
                        }
                        if (newGroup != null) {
                            groups.push(newGroup);
                        }
                    }
            }
        }
        return root;
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

}
