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
