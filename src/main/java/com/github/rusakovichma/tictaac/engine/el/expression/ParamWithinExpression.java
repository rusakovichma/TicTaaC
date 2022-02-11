package com.github.rusakovichma.tictaac.engine.el.expression;

import com.github.rusakovichma.tictaac.engine.el.EvaluationContext;
import com.github.rusakovichma.tictaac.model.threatmodel.Boundary;
import com.github.rusakovichma.tictaac.model.threatmodel.Element;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.Category;
import com.github.rusakovichma.tictaac.parser.model.NodeHelper;

import java.util.Collection;

public class ParamWithinExpression extends ParamComparableExpression<Object, Object> implements ContextAware {

    private Collection<Boundary> boundaries;

    public ParamWithinExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) {
        Object param = getParameter();
        Object valueToCompare = getValueToCompare();

        if (valueToCompare != null) {
            Category boundaryCategory = Enum.valueOf(Category.class,
                    NodeHelper.getConventionalName(valueToCompare.toString()));

            if (boundaries != null) {
                Element element = (Element) param;
                if (boundaries.stream()
                        .filter(boundary -> boundary.getCategory() == boundaryCategory)
                        .anyMatch(boundary -> boundary.getElements().contains(element))) {
                    context.addEvaluationResult(this, true);
                    return;
                }
            }
        }

        context.addEvaluationResult(this, false);
    }

    @Override
    public void setContextParam(Object contextObject) {
        this.boundaries = (Collection<Boundary>) contextObject;
    }

    @Override
    public String getContextParamName() {
        return "boundaries";
    }
}
