package org.gontard.jfx.svg.equal;

import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import javafx.scene.transform.Affine;

public class AffineEqualityTester implements EqualityTester<Affine> {

    @Override
    public void assertEqual(Affine expected, Affine found) {
        assertDoubleEquals("wrong mxx", expected.getMxx(), found.getMxx());
        assertDoubleEquals("wrong mxy", expected.getMxy(), found.getMxy());

        assertDoubleEquals("wrong myx", expected.getMyx(), found.getMyx());
        assertDoubleEquals("wrong myy", expected.getMyy(), found.getMxy());

        assertDoubleEquals("wrong mzx", expected.getMzx(), found.getMzx());
        assertDoubleEquals("wrong mzy", expected.getMzy(), found.getMzy());

        assertDoubleEquals("wrong tx", expected.getTx(), found.getTx());
        assertDoubleEquals("wrong ty", expected.getTy(), found.getTy());
        assertDoubleEquals("wrong tz", expected.getTz(), found.getTz());
    }

}
