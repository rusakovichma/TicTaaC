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
