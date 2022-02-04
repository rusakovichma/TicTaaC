package com.github.rusakovichma.tictaac.engine.el.parser;

import com.github.rusakovichma.tictaac.engine.el.expression.Expression;

public interface ExpressionParser {

    public Expression parse(String expression);

}
