package com.github.rusakovichma.tictaac.parser.model;

import java.util.regex.Pattern;

public class NodeHelper {

    private static final Pattern COLLECTION_PROPERTY_PATTERN = Pattern.compile("^(\\t|\\s)*[a-zA-Z0-9_-]+:$");

    private NodeHelper() {
    }

    public static String getConventionalName(String rawName) {
        StringBuilder conventional = new StringBuilder();

        boolean nextIsUpperCase = false;
        for (int i = 0; i < rawName.length(); i++) {
            if (rawName.charAt(i) == ':' || rawName.charAt(i) == ' ') {
                continue;
            }

            if (rawName.charAt(i) == '-' || rawName.charAt(i) == '_') {
                nextIsUpperCase = true;
                continue;
            }

            conventional.append(nextIsUpperCase ?
                    Character.toUpperCase(rawName.charAt(i)) : rawName.charAt(i));
            nextIsUpperCase = false;
        }
        return conventional.toString();
    }

    public static boolean isCollectionProperty(String content) {
        return content != null && COLLECTION_PROPERTY_PATTERN.matcher(content.trim()).find();
    }

    public static String getFlowEntity(String flowContent, boolean source) {
        final int sourceIndex = (source) ? 0 : 1;
        if (NodeType.getType(flowContent) == NodeType.flow) {
            return NodeType.element.getConventionalName(
                    NodeType.flow.getConventionalName(flowContent).split("->")[sourceIndex]
            );
        }
        return null;
    }

    public static String getFlowTarget(String flowContent) {
        if (NodeType.getType(flowContent) == NodeType.flow) {
            return NodeType.element.getConventionalName(
                    NodeType.flow.getConventionalName(flowContent).split("->")[1]
            );
        }
        return null;
    }

}
