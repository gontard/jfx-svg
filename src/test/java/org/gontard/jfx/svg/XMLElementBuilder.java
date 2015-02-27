package org.gontard.jfx.svg;

import java.util.HashMap;
import java.util.Map;

import org.gontard.jfx.svg.factories.XmlElement;

public class XMLElementBuilder {
    public static XMLElementBuilder xmlElement() {
        return new XMLElementBuilder();
    }

    private final Map<String, String> attributes = new HashMap<String, String>();

    private XMLElementBuilder() {
    }

    public XMLElementBuilder withAttribute(String name, String value) {
        attributes.put(name, value);
        return this;
    }

    public XmlElement build() {
        return attributes::get;
    }
}
