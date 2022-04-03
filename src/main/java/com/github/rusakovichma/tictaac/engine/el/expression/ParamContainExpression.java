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
import com.github.rusakovichma.tictaac.model.threatmodel.Asset;
import com.github.rusakovichma.tictaac.model.threatmodel.asset.AssetSensitivity;
import com.github.rusakovichma.tictaac.parser.model.NodeHelper;

import java.util.Collection;

public class ParamContainExpression extends ParamComparableExpression<Object, Object> {

    public ParamContainExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) {
        Object param = getParameter();
        Object valueToCompare = getValueToCompare();

        if (valueToCompare != null) {
            AssetSensitivity sensitivityParam = Enum.valueOf(AssetSensitivity.class,
                    NodeHelper.getConventionalName(valueToCompare.toString()));

            if (param != null && Collection.class.isAssignableFrom(param.getClass())) {
                Collection<Asset> assets = (Collection<Asset>) param;

                for (Asset asset : assets) {
                    if (asset.getSensitivity() == sensitivityParam) {
                        context.addEvaluationResult(this, true);
                        return;
                    }
                }

                context.addEvaluationResult(this, false);
            }
        }
    }


}
