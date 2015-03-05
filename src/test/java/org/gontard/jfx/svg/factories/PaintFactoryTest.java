package org.gontard.jfx.svg.factories;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PaintFactoryTest {

    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { { "Blue", Color.BLUE }, { "red", Color.RED },
                { "#808080", Color.GRAY }, { "rgb(0, 0, 0)", Color.BLACK },
                { "transparent", Color.TRANSPARENT }, { null, null }, { "None", null } });
    }

    private final String serialized;
    private final Paint expectedPaint;

    public PaintFactoryTest(String serialized, javafx.scene.paint.Paint paint) {
        this.serialized = serialized;
        this.expectedPaint = paint;
    }

    @Test
    public void should_parse_a_paint() {
        Paint parsedPaint = new PaintFactory().create(serialized);

        assertEquals(expectedPaint, parsedPaint);
    }
}
