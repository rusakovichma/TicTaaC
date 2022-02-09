package com.github.rusakovichma.tictaac.engine.el.expression;

import com.github.rusakovichma.tictaac.engine.el.EvaluationContext;

public class EqualsExpression extends CompoundExpression {

    public EqualsExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) {
        exprOne.interpret(context);
        exprAnother.interpret(context);

        Object oneResult = context.getEvaluationResult(exprOne);
        Object anotherResult = context.getEvaluationResult(exprOne);

        context.addEvaluationResult(this, oneResult.equals(anotherResult));
    }
}
