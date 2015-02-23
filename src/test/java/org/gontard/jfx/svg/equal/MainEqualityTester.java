package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

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
import junit.framework.ComparisonFailure;

public class MainEqualityTester implements NodeEqualityTester<Node> {
    private final Map<Class<?>, NodeEqualityTester<?>> testers = new HashMap<Class<?>, NodeEqualityTester<?>>();

    public MainEqualityTester() {
        testers.put(Node.class, new FailingTester());
        testers.put(Parent.class, new ParentEqualityTester(this));
        testers.put(Circle.class, new CircleEqualityTester());
        testers.put(Ellipse.class, new EllipseEqualityTester());
        testers.put(ImageView.class, new ImageViewEqualityTester());
        testers.put(Line.class, new LineEqualityTester());
        testers.put(Polygon.class, new PolygonEqualityTester());
        testers.put(Polyline.class, new PolylineEqualityTester());
        testers.put(Rectangle.class, new RectangleEqualityTester());
        testers.put(SVGPath.class, new SVGPathEqualityTester());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void assertEqual(Node expected, Node found) {
        NodeEqualityTester tester = getTester(expected);
        assertNotNull("expected is null", expected);
        assertNotNull("found is null", found);
        try {
            assertSame("wrong class", expected.getClass(), found.getClass());
            tester.assertEqual(expected, found);
        }
        catch (AssertionError e) {
            throw new ComparisonFailure(e.getMessage(), toString(expected), toString(found));
        }
    }

    private String toString(Node node) {
        StringBuilder builder = new StringBuilder();
        toString(node, 0, builder);
        return builder.toString().replaceAll("@\\w+", "");
    }

    private void toString(Node node, int deep, StringBuilder builder) {
        for (int i = 0; i < deep; i++) {
            builder.append("  ");
        }
        builder.append(node.toString());
        builder.append("\n");
        if (node instanceof Parent) {
            ((Parent)node).getChildrenUnmodifiable().forEach(child -> toString(child, deep + 1, builder));
        }
    }

    private NodeEqualityTester<?> getTester(Node expected) {
        NodeEqualityTester<?> tester = null;
        Class<?> nodeClass = expected.getClass();
        do {
            tester = testers.get(nodeClass);
            nodeClass = nodeClass.getSuperclass();
        } while (tester == null);
        return tester;
    }

    private static class FailingTester implements NodeEqualityTester<Node> {
        @Override
        public void assertEqual(Node expected, Node found) {
            fail("No equality tester for " + expected.getClass().getName());
        }

    }
}
