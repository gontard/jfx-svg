package org.gontard.jfx.svg.equal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Parent;
import junit.framework.ComparisonFailure;

public class DispatchEqualityTester<N> implements EqualityTester<N> {
    private final Map<Class<?>, EqualityTester<?>> testers = new HashMap<Class<?>, EqualityTester<?>>();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void assertEqual(N expected, N found) {
        EqualityTester tester = getTester(expected);
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

    private String toString(N node) {
        StringBuilder builder = new StringBuilder();
        toString(node, 0, builder);
        return builder.toString().replaceAll("@\\w+", "");
    }

    @SuppressWarnings("unchecked")
    private void toString(N node, int deep, StringBuilder builder) {
        for (int i = 0; i < deep; i++) {
            builder.append("  ");
        }
        builder.append(node.toString());
        builder.append("\n");
        if (node instanceof Parent) {
            ((Parent)node).getChildrenUnmodifiable().forEach(child -> toString((N)child, deep + 1, builder));
        }
    }

    protected void setTester(Class<?> c, EqualityTester<?> tester) {
        testers.put(c, tester);
    }

    private EqualityTester<?> getTester(N expected) {
        EqualityTester<?> tester = null;
        Class<?> nodeClass = expected.getClass();
        do {
            tester = testers.get(nodeClass);
            nodeClass = nodeClass.getSuperclass();
        } while (tester == null);
        return tester;
    }

}
