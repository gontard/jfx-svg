package org.gontard.jfx.svg.equal;

import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

public class TransformEqualityTester extends DispatchEqualityTester<Transform> {

    public TransformEqualityTester() {
        setTester(Transform.class, new FailingTester<Transform>());
        setTester(Affine.class, new AffineEqualityTester());
    }

    @Override
    public void assertEqual(Transform expected, Transform found) {

    }
}
