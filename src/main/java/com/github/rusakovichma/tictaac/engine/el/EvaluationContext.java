package com.github.rusakovichma.tictaac.engine.el;

import com.github.rusakovichma.tictaac.engine.el.expression.Expression;

public interface EvaluationContext {

    public Object getEvaluationResult(Expression expression);

    public void addEvaluationResult(Expression expression, Object result);

}
