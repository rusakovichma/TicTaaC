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
package com.github.rusakovichma.tictaac.engine.el.parser;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.rusakovichma.tictaac.util.StringUtils.*;

public class StringHashReplacer implements ExpressionPreProcessor {

    private final static Pattern QUOTE_STRING_PATTERN = Pattern.compile("(?:^|\\s)'([^']*?)'(?:$|\\s)",
            Pattern.MULTILINE);

    private final Map<String, String> stringsHashCache;

    public StringHashReplacer(Map<String, String> stringsHashCache) {
        this.stringsHashCache = stringsHashCache;
    }

    private void addToCache(String string, String digest) {
        stringsHashCache.put(string, digest);
    }

    private String replaceStringWithHash(String expression, String entityWithQuotes) {
        String entityWithoutQuotes = removeFirstAndLastChar(entityWithQuotes, '\'');
        String entityDigest = bytesToHex(digest(entityWithoutQuotes.getBytes(StandardCharsets.UTF_8)));

        stringsHashCache.put(entityDigest, entityWithoutQuotes);

        return expression.replaceAll(entityWithQuotes, entityDigest);
    }

    @Override
    public String preProcess(String expression) {
        String expressionWithHashes = expression;
        Matcher expMatcher = QUOTE_STRING_PATTERN.matcher(expression);
        if (expMatcher.find()) {
            expressionWithHashes = replaceStringWithHash(expressionWithHashes, expMatcher.group().trim());
            while (expMatcher.find())
                expressionWithHashes = replaceStringWithHash(expressionWithHashes, expMatcher.group().trim());
        }
        return expressionWithHashes;
    }
}
