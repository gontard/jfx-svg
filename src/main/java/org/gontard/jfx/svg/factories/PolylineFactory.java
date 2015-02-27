package org.gontard.jfx.svg.factories;

import java.util.Collection;
import java.util.StringTokenizer;

import javafx.scene.shape.Polyline;

public class PolylineFactory extends ShapeFactory {

    public PolylineFactory(PaintFactory paintFactory) {
        super(paintFactory);
    }

    @Override
    protected Polyline createShape(XmlElement el) {
        Polyline polyline = new Polyline();
        readPoints(el, polyline.getPoints());
        return polyline;
    }

    static void readPoints(XmlElement el, Collection<Double> points) {
        String pointsValue = el.getString("points", "");
        parsePoints(pointsValue, points);
    }

    private static void parsePoints(String pointsValue, Collection<Double> points) {
        StringTokenizer tokenizer = new StringTokenizer(pointsValue);
        while (tokenizer.hasMoreTokens()) {
            String[] coordinates = tokenizer.nextToken().split(",");
            points.add(Double.valueOf(coordinates[0]));
            points.add(Double.valueOf(coordinates[1]));
        }
    }
}
