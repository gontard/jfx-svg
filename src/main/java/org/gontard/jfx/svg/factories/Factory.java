package org.gontard.jfx.svg.factories;

import javafx.scene.Node;

public interface Factory {
    Node create(XmlElement el);
}
