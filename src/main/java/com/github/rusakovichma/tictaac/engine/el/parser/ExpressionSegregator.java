package com.github.rusakovichma.tictaac.engine.el.parser;

public class ExpressionSegregator implements ExpressionPreProcessor {

    private final EvaluationTemplate evaluationTemplate;

    public ExpressionSegregator(EvaluationTemplate evaluationTemplate) {
        this.evaluationTemplate = evaluationTemplate;
    }

    @Override
    public String preProcess(String expression) {
        return new StringBuilder(evaluationTemplate.getEvaluationStart())
                .append(EvaluationTemplate.DELIMITER)
                .append(expression)
                .append(EvaluationTemplate.DELIMITER)
                .append(evaluationTemplate.getEvaluationEnd())
                .toString();
    }
}
