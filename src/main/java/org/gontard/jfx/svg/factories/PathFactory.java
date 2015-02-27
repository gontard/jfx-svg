package org.gontard.jfx.svg.factories;

import javafx.scene.shape.SVGPath;

public class PathFactory extends ShapeFactory {

    public PathFactory(PaintFactory paintFactory) {
        super(paintFactory);
    }

    @Override
    protected SVGPath createShape(XmlElement el) {
        SVGPath path = new SVGPath();
        path.setContent(el.getString("d"));
        return path;
    }

}
