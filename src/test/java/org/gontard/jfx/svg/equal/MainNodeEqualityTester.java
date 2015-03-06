package org.gontard.jfx.svg.equal;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Transform;

public class MainNodeEqualityTester extends DispatchEqualityTester<Node> {

    public MainNodeEqualityTester() {
        DispatchEqualityTester<Transform> transformTester = new TransformEqualityTester();
        setTester(Node.class, new FailingTester<Node>());
        setTester(Parent.class, new ParentEqualityTester(this, transformTester));
        setTester(Circle.class, new CircleEqualityTester(transformTester));
        setTester(Ellipse.class, new EllipseEqualityTester(transformTester));
        setTester(ImageView.class, new ImageViewEqualityTester(transformTester));
        setTester(Line.class, new LineEqualityTester(transformTester));
        setTester(Polygon.class, new PolygonEqualityTester(transformTester));
        setTester(Polyline.class, new PolylineEqualityTester(transformTester));
        setTester(Rectangle.class, new RectangleEqualityTester(transformTester));
        setTester(SVGPath.class, new SVGPathEqualityTester(transformTester));
    }

}
