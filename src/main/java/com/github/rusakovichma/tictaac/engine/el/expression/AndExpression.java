package com.github.rusakovichma.tictaac.engine.el.expression;


import com.github.rusakovichma.tictaac.engine.el.EvaluationContext;

public class AndExpression extends CompoundExpression {

    public AndExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) {
        exprOne.interpret(context);
        exprAnother.interpret(context);

        boolean oneResult = (Boolean) context.getEvaluationResult(exprOne);
        boolean anotherResult = (Boolean) context.getEvaluationResult(exprAnother);
        context.addEvaluationResult(this, oneResult && anotherResult);
    }
}

