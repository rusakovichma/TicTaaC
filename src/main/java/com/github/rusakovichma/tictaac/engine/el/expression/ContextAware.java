package com.github.rusakovichma.tictaac.engine.el.expression;

public interface ContextAware {

    public void setContextParam(Object contextObject);

    public String getContextParamName();

}
