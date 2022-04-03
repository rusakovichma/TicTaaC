/*
 * This file is part of TicTaaC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
package com.github.rusakovichma.tictaac.parser.model;

import java.util.regex.Pattern;

public enum NodeType {
    element() {
        @Override
        public String getConventionalName(String rawName) {
            final String rawElementName = rawName.startsWith("-") ? rawName.substring(1) : rawName;
            return rawElementName.replaceAll(" ", "");
        }

        @Override
        public String getValue(String content) {
            throw new UnsupportedOperationException();
        }
    },
    property() {
        @Override
        public String getConventionalName(String rawName) {
            return NodeHelper.getConventionalName(rawName.split(":")[0]);
        }

        @Override
        public String getValue(String content) {
            String[] splitContent = content.split(":");
            if (splitContent.length > 1 && splitContent[1] != null) {
                return splitContent[1].trim();
            }
            return null;
        }
    },
    flow {
        @Override
        public String getConventionalName(String rawName) {
            final String rawFlowName = rawName.startsWith("-") ? rawName.substring(1) : rawName;
            return rawFlowName.replaceAll(" ", "");
        }

        @Override
        public String getValue(String content) {
            throw new UnsupportedOperationException();
        }
    },
    unknown {
        @Override
        public String getConventionalName(String rawName) {
            return "";
        }

        @Override
        public String getValue(String content) {
            throw new UnsupportedOperationException();
        }
    };

    private static final Pattern PROPERTY_PATTERN = Pattern.compile("^(\\t|\\s)*[a-zA-Z0-9_-]+:(.)*$");
    private static final Pattern FLOW_PATTERN = Pattern.compile("[a-zA-Z0-9_-]+(\\s*)(->)(\\s*)[a-zA-Z0-9_-]+");
    private static final Pattern ELEMENT_PATTERN = Pattern.compile("-(\\s*)[a-zA-Z0-9_-]+");

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

    public abstract String getConventionalName(String rawName);

    public abstract String getValue(String content);
}
