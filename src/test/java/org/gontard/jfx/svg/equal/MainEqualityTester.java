/*
 * Created on 20 févr. 2015
 * @author intactile
 * Owner : Intactile Design
 * All rights reserved
 */
package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

public class MainEqualityTester implements NodeEqualityTester<Node> {
    // Constants .............................................................................................

    // Class fields ..........................................................................................

    // Class methods .........................................................................................

    // Instance fields .......................................................................................
    private final Map<Class<?>, NodeEqualityTester<?>> testers = new HashMap<Class<?>, NodeEqualityTester<?>>();

    // Constructors ..........................................................................................
    public MainEqualityTester() {
        testers.put(Node.class, new FailingTester());
        testers.put(Parent.class, new ParentEqualityTester(this));
        testers.put(Rectangle.class, new RectangleEqualityTester());
        testers.put(Circle.class, new CircleEqualityTester());
        testers.put(Ellipse.class, new EllipseEqualityTester());
        testers.put(Line.class, new LineEqualityTester());
        testers.put(Polygon.class, new PolygonEqualityTester());
        testers.put(Polyline.class, new PolylineEqualityTester());
        testers.put(SVGPath.class, new SVGPathEqualityTester());
    }

    // Getters / Setters .....................................................................................

    // Interface implementations .............................................................................

    // Abstract methods implementations ......................................................................

    // Methods ...............................................................................................
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void assertEqual(Node expected, Node found) {
        assertNotNull("expected is null", expected);
        assertNotNull("found is null", found);
        assertSame("wrong class", expected.getClass(), found.getClass());
        NodeEqualityTester tester = getTester(expected);
        tester.assertEqual(expected, found);
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

    // Inner classes .........................................................................................
    private static class FailingTester implements NodeEqualityTester<Node> {
        @Override
        public void assertEqual(Node expected, Node found) {
            fail("No equality tester for " + expected.getClass().getName());
        }

    }
}
