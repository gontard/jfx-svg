/*
 * Created on 18 févr. 2015
 * @author intactile
 * Owner : Intactile Design
 * All rights reserved
 */
package matcher;

import javafx.scene.shape.Circle;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsCircleEquals extends TypeSafeMatcher<Circle> {
    // Constants .............................................................................................

    // Class fields ..........................................................................................

    // Class methods .........................................................................................

    // Instance fields .......................................................................................

    // Constructors ..........................................................................................

    // Getters / Setters .....................................................................................

    // Interface implementations .............................................................................

    // Abstract methods implementations ......................................................................

    // Methods ...............................................................................................
    @Override
    public void describeTo(Description description) {
        // TODO Auto-generated method stub

    }

    @Override
    protected boolean matchesSafely(Circle item) {
        return false;
    }

    // Inner classes .........................................................................................
}
