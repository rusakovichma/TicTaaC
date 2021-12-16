package com.github.rusakovichma.tictaac.parser.model;

import java.util.regex.Pattern;

public enum NodeType {
    element,
    property,
    flow,
    unknown;

    private static final Pattern PROPERTY_PATTERN = Pattern.compile("^(\\t|\\s)*[a-zA-Z0-9_-]+:");
    private static final Pattern FLOW_PATTERN = Pattern.compile("[a-zA-Z0-9_ -]+(->)[a-zA-Z0-9_ -]+");
    private static final Pattern ELEMENT_PATTERN = Pattern.compile("-[a-zA-Z0-9_ -]+");

    public static NodeType getType(String raw) {
        if (raw == null) {
            return unknown;
        }
        if (PROPERTY_PATTERN.matcher(raw).find()) {
            return property;
        }
        if (FLOW_PATTERN.matcher(raw).find()) {
            return flow;
        }
        if (ELEMENT_PATTERN.matcher(raw).find()) {
            return element;
        }
        return unknown;
    }
}
