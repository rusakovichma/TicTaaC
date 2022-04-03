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

abstract class ParamComparableExpression<V, P> extends AbstractExpression implements ValueComparable<V>, ParametrableExpression<P> {

    protected V valueToCompare;
    protected P parameter;

    public ParamComparableExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void setValueToCompare(V valueToCompare) {
        this.valueToCompare = valueToCompare;
    }

    public V getValueToCompare() {
        return valueToCompare;
    }

    public P getParameter() {
        return parameter;
    }

    public void setParameter(P parameter) {
        this.parameter = parameter;
    }
}
