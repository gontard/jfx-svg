package org.gontard.jfx.svg.factories;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PaintFactory {

    public Paint create(String serialized) {
        return serialized != null && !"none".equalsIgnoreCase(serialized) ? Color.web(serialized) : null;
    }

}
