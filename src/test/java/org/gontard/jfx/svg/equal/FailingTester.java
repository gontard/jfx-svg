package org.gontard.jfx.svg.equal;

import static org.junit.Assert.fail;

public class FailingTester<N> implements EqualityTester<N> {

    @Override
    public void assertEqual(N expected, N found) {
        fail("No equality tester for " + expected.getClass().getName());
    }

}
