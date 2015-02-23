package org.gontard.jfx.svg;

import javax.xml.stream.XMLStreamReader;

import org.gontard.jfx.svg.factories.XmlElement;

public class StaXmlElement implements XmlElement {
    private final XMLStreamReader reader;

    StaXmlElement(XMLStreamReader reader) {
        this.reader = reader;
    }

    @Override
    public String getString(String name) {
        return reader.getAttributeValue(null, name);
    }
}
