package org.gontard.jfx.svg.factories;

import static org.gontard.jfx.svg.XMLElementBuilder.xmlElement;
import static org.gontard.jfx.svg.util.JfxAssert.assertDoubleEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class XmlElementTest {
    private static final String A = "a";
    private static final String Z = "z";

    @Test
    public void getDouble() {
        XmlElement element = element("15.17");
        assertDoubleEquals("wrong value", 15.17, element.getDouble(A));
        assertDoubleEquals("defaut value not used", 17.1, element.getDouble(Z, 17.1));
        assertDoubleEquals("wrong default value", 0, element.getDouble(Z));
    }

    @Test(expected = NumberFormatException.class)
    public void getDouble_format_exception() {
        element("15,17").getDouble(A);
    }

    @Test
    public void getInt() {
        XmlElement element = element("17");
        assertEquals("wrong value", 17, element.getInt(A));
        assertEquals("defaut value not used", 33, element.getInt(Z, 33));
        assertEquals("wrong default value", 0, element.getInt(Z));
    }

    @Test(expected = NumberFormatException.class)
    public void getInt_format_exception() {
        element("3.14").getInt(A);
    }

    @Test
    public void getBoolean() {
        XmlElement element = element("true");
        assertTrue("wrong value", element.getBoolean(A));
        assertTrue("defaut value not used", element.getBoolean(Z, true));
        assertFalse("wrong default value", element.getBoolean(Z));
    }

    @Test
    public void getString() {
        XmlElement element = element("a thing");
        assertEquals("wrong value", "a thing", element.getString(A));
        assertEquals("defaut value not used", "another thing", element.getString(Z, "another thing"));
        assertNull("wrong default value", element.getString(Z));
    }

    private XmlElement element(String value) {
        return xmlElement().withAttribute(A, value).build();
    }
}
