package com.github.rusakovichma.tictaac.engine;

import com.github.rusakovichma.tictaac.engine.el.expression.Expression;
import com.github.rusakovichma.tictaac.engine.el.parser.DefaultExpressionParser;
import com.github.rusakovichma.tictaac.engine.el.parser.SimpleExternalContext;
import com.github.rusakovichma.tictaac.mapper.ThreatRuleMapper;
import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.model.ThreatRule;
import com.github.rusakovichma.tictaac.model.ThreatSeverity;
import com.github.rusakovichma.tictaac.model.threatmodel.Boundary;
import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;
import com.github.rusakovichma.tictaac.model.threatmodel.Element;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;
import com.github.rusakovichma.tictaac.risk.PlainMatrixRiskStrategy;
import com.github.rusakovichma.tictaac.risk.RiskCalculationStrategy;
import com.github.rusakovichma.tictaac.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class StandardEngineContext implements EngineContext {

    private Collection<Threat> threats = new ArrayList<>();
    private RiskCalculationStrategy riskStrategy = new PlainMatrixRiskStrategy();

    private ThreatModel threatModel;
    private Collection<ThreatRule> rules;

    private BoundaryCategory getElementAttackVector(Element element) {
        return threatModel.getBoundaries().stream()
                .filter(boundary -> boundary.getElements().contains(element))
                .max(Comparator.comparing(Boundary::getCategoryOrder))
                .map(boundary -> boundary.getCategory())
                .orElse(BoundaryCategory.undefined);
    }

    private BoundaryCategory getAttackVector(DataFlow flow) {
        BoundaryCategory sourceAttackVector = getElementAttackVector(flow.getSource());
        BoundaryCategory targetAttackVector = getElementAttackVector(flow.getTarget());

        if (sourceAttackVector.getOrder() > targetAttackVector.getOrder()) {
            return sourceAttackVector;
        }

        if (sourceAttackVector.getOrder() < targetAttackVector.getOrder()) {
            return targetAttackVector;
        }

        return sourceAttackVector;
    }

    private void setThreatRisk(Threat threat, ThreatRule rule) {
        BoundaryCategory attackVector = getAttackVector(threat.getDataFlow());
        ThreatSeverity baseImpact = rule.getSeverity();

        threat.setRisk(riskStrategy.calc(baseImpact, attackVector));
    }

    private void setThreatId(Threat threat) {
        threat.setId(String.valueOf(threat.hashCode()));
    }

    private void processFlow(DataFlow flow) {
        SimpleExternalContext flowContext = new SimpleExternalContext();

        flowContext.addParameter("boundaries", threatModel.getBoundaries());
        flowContext.addParameters(ReflectionUtil.getFields(flow, "flow"));

        flowContext.addParameters(ReflectionUtil.getFields(flow.getSource(), "source"));
        flowContext.addParameters(ReflectionUtil.getFields(flow.getTarget(), "target"));

        DefaultExpressionParser parser = new DefaultExpressionParser(flowContext);

        for (ThreatRule rule : rules) {
            if (((Expression<Boolean>) parser.parse(rule.getExpression()).getEvaluationResult())
                    .getEvaluationResult()) {
                if (rule.getExclude() != null && !rule.getExclude().trim().isEmpty()) {
                    if (!(((Expression<Boolean>) parser.parse(rule.getExclude()).getEvaluationResult())
                            .getEvaluationResult())) {

                        Threat threat = new ThreatRuleMapper(rule, flow).getModel();
                        setThreatRisk(threat, rule);
                        setThreatId(threat);

                        threats.add(threat);
                    }
                }
            }
        }
    }


    @Override
    public Collection<Threat> eval(ThreatModel threatModel, Collection<ThreatRule> rules) {
        this.threatModel = threatModel;
        this.rules = rules;

        Collection<DataFlow> flows = threatModel.getDataFlows();
        for (DataFlow flow : flows) {
            processFlow(flow);
        }

        return threats;
    }
}


