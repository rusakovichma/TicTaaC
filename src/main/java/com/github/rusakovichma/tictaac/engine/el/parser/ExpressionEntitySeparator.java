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

public class ExpressionEntitySeparator implements ExpressionPreProcessor {

    private static final String DELIM = EvaluationTemplate.DELIMITER;
    private static final String[] toSeparate = {"(", ")", "=="};

    private void seclude(StringBuilder exprBuilder, String occur) {
        int index = exprBuilder.indexOf(occur);
        int offset = 0;

        while (index >= 0) {
            offset = index;

            if (offset - 1 > 0) {
                exprBuilder = exprBuilder.insert(index, DELIM);
            }

            exprBuilder.insert(index + occur.length() + 1, DELIM);
            index = exprBuilder.indexOf(occur, index + 2 * DELIM.length() + 1);
        }

    }

    @Override
    public String preProcess(String expression) {
        StringBuilder exprBuilder = new StringBuilder(expression);

        for (int i = 0; i < toSeparate.length; i++) {
            String token = toSeparate[i];
            seclude(exprBuilder, token);
        }

        return exprBuilder.toString();
    }
}
