package org.gontard.jfx.svg.equal;


public interface EqualityTester<N> {
    void assertEqual(N expected, N found);
}
