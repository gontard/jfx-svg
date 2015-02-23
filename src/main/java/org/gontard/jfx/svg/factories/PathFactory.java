package org.gontard.jfx.svg.factories;

import javafx.scene.Node;
import javafx.scene.shape.SVGPath;

public class PathFactory implements Factory {
    @Override
    public Node create(XmlElement el) {
        SVGPath path = new SVGPath();
        path.setContent(el.getString("d"));
        return path;
    }
}
