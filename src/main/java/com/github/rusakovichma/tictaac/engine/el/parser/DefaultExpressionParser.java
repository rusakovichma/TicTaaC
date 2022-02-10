package com.github.rusakovichma.tictaac.engine.el.parser;

import com.github.rusakovichma.tictaac.engine.el.DefaultEvaluationContext;
import com.github.rusakovichma.tictaac.engine.el.EvaluationContext;
import com.github.rusakovichma.tictaac.engine.el.expression.*;

import java.util.*;

import static com.github.rusakovichma.tictaac.engine.el.parser.EvaluationTemplate.DELIMITER;

public class DefaultExpressionParser implements ExpressionParser {

    private EvaluationTemplate evaluationTemplate = new DefaultEvaluationTemplate();
    private final EvaluationContext evaluationContext = new DefaultEvaluationContext();
    private final ExternalContext externalContext;
    private Deque<Object> vals = new ArrayDeque<Object>();
    private Deque<Object> params = new ArrayDeque<Object>();
    private Deque<Expression> transExpr = new ArrayDeque<Expression>();
    private Deque<Expression> internalExpr = new ArrayDeque<Expression>();
    private List<ExpressionPreProcessor> preProcessors = new LinkedList<ExpressionPreProcessor>();
    private Map<String, String> stringsHashCache = new HashMap<>();


    public DefaultExpressionParser(ExternalContext externalContext) {
        this.externalContext = externalContext;

        preProcessors.add(new ExpressionSegregator(evaluationTemplate));
        preProcessors.add(new ExpressionEntitySeparator());
        preProcessors.add(new StringHashReplacer(stringsHashCache));
    }

    private void eval() {
        if (transExpr.isEmpty()) {
            return;
        }

        Expression lastExpression = transExpr.pop();

        if (lastExpression instanceof ValueComparable) {
            ValueComparable valueComparable = (ValueComparable) lastExpression;
            valueComparable.setValueToCompare(vals.pop());
        }

        if (lastExpression instanceof ExpressionAware) {
            ExpressionAware expressionAware = (ExpressionAware) lastExpression;
            expressionAware.setExprOne(internalExpr.pop());
            expressionAware.setExprAnother(internalExpr.pop());
        }

        if (lastExpression instanceof ParametrableExpression) {
            ParametrableExpression parametrableExpression = (ParametrableExpression) lastExpression;
            parametrableExpression.setParameter(params.pop());
        }

        internalExpr.push(lastExpression);
    }

    private void processEntity(String entity) {
        Object parameter = externalContext.getParameter(entity);
        if (parameter != null) {
            params.add(parameter);
        } else {
            vals.add(entity);
        }
    }

    @Override
    public Expression parse(String expression) {
        String expressionProcessed = expression;

        for (ExpressionPreProcessor preProcessor : preProcessors) {
            expressionProcessed = preProcessor.preProcess(expressionProcessed);
        }

        StringTokenizer tokenizer = new StringTokenizer(expressionProcessed, DELIMITER);
        while (tokenizer.hasMoreElements()) {
            String element = tokenizer.nextToken();

            if (element.equals(evaluationTemplate.getEvaluationStart())) {
                continue;
            }

            if (element.equals(evaluationTemplate.getEvaluationEnd())) {
                eval();
                continue;
            }

            switch (element) {
                case "(":
                    break;
                case "and":
                case "AND":
                    transExpr.push(new AndExpression(evaluationContext));
                    break;
                case "or":
                case "OR":
                    transExpr.push(new OrExpression(evaluationContext));
                    break;
                case "==":
                case "equals":
                case "EQUALS": {
                    transExpr.push(new ParamEqualsExpression(evaluationContext, stringsHashCache));
                    break;
                }
                case "contain":
                case "CONTAIN": {
                    transExpr.push(new ParamContainExpression(evaluationContext));
                    break;
                }
                case ")":
                    eval();
                    break;
                default:
                    processEntity(element);
            }
        }

        return internalExpr.pop();
    }


}
