package com.github.rusakovichma.tictaac.engine.el.expression;

import com.github.rusakovichma.tictaac.engine.el.EvaluationContext;

abstract class CompoundExpression extends AbstractExpression implements ExpressionAware {

    protected AbstractExpression exprOne;
    protected AbstractExpression exprAnother;

    public CompoundExpression(EvaluationContext context) {
        super(context);
    }

    public Expression getExprOne() {
        return exprOne;
    }

    @Override
    public void setExprOne(Expression exprOne) {
        this.exprOne = (AbstractExpression) exprOne;
    }

    public Expression getExprAnother() {
        return exprAnother;
    }

    @Override
    public void setExprAnother(Expression exprAnother) {
        this.exprAnother = (AbstractExpression) exprAnother;
    }

}
