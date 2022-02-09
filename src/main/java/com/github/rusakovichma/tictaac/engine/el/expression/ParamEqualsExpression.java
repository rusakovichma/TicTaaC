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

        if (valueToCompare != null && StringUtils.isDigest(valueToCompare.toString())) {
            valueToCompare = stringsHashCache.get(valueToCompare.toString());
        }

        context.addEvaluationResult(this, Objects.equals(param, valueToCompare));
    }

}
