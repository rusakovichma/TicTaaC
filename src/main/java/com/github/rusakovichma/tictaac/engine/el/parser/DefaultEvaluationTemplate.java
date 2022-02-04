package com.github.rusakovichma.tictaac.engine.el.parser;

public class DefaultEvaluationTemplate implements EvaluationTemplate {

    @Override
    public String getEvaluationStart() {
        return "$${";
    }

    @Override
    public String getEvaluationEnd() {
        return "}";
    }
}
