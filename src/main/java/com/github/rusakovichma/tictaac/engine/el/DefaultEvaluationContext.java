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
