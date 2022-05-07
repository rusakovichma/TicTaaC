/*
 * This file is part of TicTaaC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
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

import java.util.*;
import java.util.stream.Collectors;

class StandardEngineContext implements EngineContext {

    private Collection<Threat> threats = new ArrayList<>();
    private RiskCalculationStrategy riskStrategy = new PlainMatrixRiskStrategy();

    private ThreatModel threatModel;
    private Collection<ThreatRule> rules;

    StandardEngineContext() {
    }

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
        threat.setAttackVector(attackVector);

        ThreatSeverity baseImpact = rule.getSeverity();

        threat.setRisk(riskStrategy.calc(baseImpact, attackVector));
    }

    private void setThreatId(Threat threat) {
        threat.setId(threat.calculateHash());
    }

    private void processFlow(DataFlow flow) {
        SimpleExternalContext flowContext = new SimpleExternalContext();

        flowContext.addParameter("boundaries", threatModel.getBoundaries());

        flowContext.addParameter("flow", flow);
        flowContext.addParameters(ReflectionUtil.getFields(flow, "flow"));

        flowContext.addParameter("source", flow.getSource());
        flowContext.addParameters(ReflectionUtil.getFields(flow.getSource(), "source"));

        flowContext.addParameter("target", flow.getTarget());
        flowContext.addParameters(ReflectionUtil.getFields(flow.getTarget(), "target"));

        DefaultExpressionParser parser = new DefaultExpressionParser(flowContext);

        for (ThreatRule rule : rules) {
            Expression<Boolean> includeExpression = parser.parse(rule.getExpression());
            if (includeExpression.getEvaluationResult()) {

                if (rule.getExclude() != null && !rule.getExclude().trim().isEmpty()) {
                    Expression<Boolean> excludeExpression = parser.parse(rule.getExclude());
                    if (excludeExpression.getEvaluationResult()) {
                        continue;
                    }
                }

                Threat threat = new ThreatRuleMapper(rule, flow).getModel();
                setThreatRisk(threat, rule);
                setThreatId(threat);

                threats.add(threat);
            }
        }
    }


    @Override
    public Collection<Threat> eval(ThreatModel threatModel, Collection<ThreatRule> rules) {
        this.threatModel = threatModel;
        this.rules = rules;

        threatModel.getDataFlows().stream()
                .forEach(flow -> processFlow(flow));

        return threats.stream().collect(
                Collectors.groupingBy(Threat::calculateHash,
                        Collectors.collectingAndThen(
                                Collectors.reducing((Threat threatOne, Threat threatAnother) ->
                                        threatOne.getRiskPriority() > threatAnother.getRiskPriority()
                                                ? threatOne : threatAnother),
                                Optional::get)))
                .values();
    }
}


