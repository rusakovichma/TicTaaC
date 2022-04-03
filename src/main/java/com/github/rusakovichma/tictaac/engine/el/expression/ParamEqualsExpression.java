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
package com.github.rusakovichma.tictaac.engine.el.expression;

import com.github.rusakovichma.tictaac.engine.el.EvaluationContext;
import com.github.rusakovichma.tictaac.parser.model.NodeHelper;
import com.github.rusakovichma.tictaac.util.StringUtils;

import java.util.Map;
import java.util.Objects;

public class ParamEqualsExpression extends ParamComparableExpression<Object, Object> {

    private final Map<String, String> stringsHashCache;

    public ParamEqualsExpression(EvaluationContext context, Map<String, String> stringsHashCache) {
        super(context);
        this.stringsHashCache = stringsHashCache;
    }

    @Override
    public void interpret(EvaluationContext context) {
        Object param = getParameter();
        Object valueToCompare = getValueToCompare();

        if (param.getClass().isEnum()) {
            param = param.toString();
            valueToCompare = NodeHelper.getConventionalName(valueToCompare.toString());
        }

        if (param.getClass().isAssignableFrom(Boolean.class)) {
            valueToCompare = Boolean.valueOf(valueToCompare.toString());
        }

        if (valueToCompare != null && StringUtils.isDigest(valueToCompare.toString())) {
            valueToCompare = stringsHashCache.get(valueToCompare.toString());
        }

        context.addEvaluationResult(this, Objects.equals(param, valueToCompare));
    }

}
