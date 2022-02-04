package com.github.rusakovichma.tictaac.engine.el.expression;

import com.github.rusakovichma.tictaac.engine.el.EvaluationContext;

public abstract class AbstractExpression implements Expression {

    protected final EvaluationContext context;

    public AbstractExpression(EvaluationContext context) {
        this.context = context;
    }

    public abstract void interpret(EvaluationContext context);

    @Override
    public Object getEvaluationResult() {
        interpret(context);
        return context.getEvaluationResult(this);
    }

}
