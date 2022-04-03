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
package com.github.rusakovichma.tictaac.engine.el;

import com.github.rusakovichma.tictaac.engine.el.expression.Expression;

import java.util.HashMap;
import java.util.Map;

public class DefaultEvaluationContext implements EvaluationContext {

    private Map<Expression, Object> evaluationResults = new HashMap<Expression, Object>();

    @Override
    public Object getEvaluationResult(Expression expression) {
        return evaluationResults.get(expression);
    }

    @Override
    public void addEvaluationResult(Expression expression, Object value) {
        evaluationResults.put(expression, value);
    }
}
