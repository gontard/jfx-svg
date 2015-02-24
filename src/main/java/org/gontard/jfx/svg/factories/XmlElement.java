package org.gontard.jfx.svg.factories;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@FunctionalInterface
public interface XmlElement {
    String getString(String name);

    default double getDouble(String name) {
        return getDouble(name, 0);
    }

    default int getInt(String name) {
        return getInt(name, 0);
    }

    default boolean getBoolean(String name) {
        return getBoolean(name, false);
    }

    default String getString(String name, String def) {
        String value = getString(name);
        return value == null ? def : value;
    }

    default double getDouble(String name, double def) {
        String value = getString(name);
        return value == null ? def : parseDouble(value);
    }

    default int getInt(String name, int def) {
        String value = getString(name);
        return value == null ? def : parseInt(value);
    }

    default boolean getBoolean(String name, boolean def) {
        String value = getString(name);
        return value == null ? def : Boolean.parseBoolean(value);
    }
}
