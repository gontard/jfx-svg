package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertSame;

import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

class ParentEqualityTester implements NodeEqualityTester<Parent> {
    private final NodeEqualityTester<Node> childTester;

    ParentEqualityTester(NodeEqualityTester<Node> childTester) {
        this.childTester = childTester;
    }

    // Getters / Setters .....................................................................................

    // Interface implementations .............................................................................

    // Abstract methods implementations ......................................................................

    // Methods ...............................................................................................
    @Override
    public void assertEqual(Parent expected, Parent found) {
        ObservableList<Node> children1 = expected.getChildrenUnmodifiable();
        ObservableList<Node> children2 = found.getChildrenUnmodifiable();
        assertSame("different amount of child",
                children1.size(),
                children2.size());
        Iterator<Node> iterator1 = children1.iterator();
        Iterator<Node> iterator2 = children2.iterator();
        while (iterator1.hasNext()) {
            Node child1 = iterator1.next();
            Node child2 = iterator2.next();
            childTester.assertEqual(child1, child2);
        }
    }
    // Inner classes .........................................................................................
}
