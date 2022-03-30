package com.github.rusakovichma.tictaac.mapper;

import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatRule;
import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;

import java.util.HashMap;
import java.util.Map;


public class ThreatRuleMapper implements ModelMapper<Threat> {

    private final ThreatRule rule;
    private final DataFlow flow;

    private final Map<String, String> entities = new HashMap<>();

    private final void initEntities() {
        entities.put("\\{flow.name\\}", flow.getTitle());
        entities.put("\\{source.name\\}", flow.getSource().getName());
        entities.put("\\{target.name\\}", flow.getTarget().getName());
    }

    private String solvePlaceholders(String field) {
        for (Map.Entry<String, String> entry : entities.entrySet()) {
            field = field.replaceAll(entry.getKey(), entry.getValue());
        }
        return field;
    }

    public ThreatRuleMapper(ThreatRule rule, DataFlow flow) {
        this.rule = rule;
        this.flow = flow;
        initEntities();
    }

    @Override
    public Threat getModel() {
        Threat threat = new Threat();

        threat.setTitle(solvePlaceholders(rule.getTitle()));
        threat.setCategories(rule.getCategories());
        threat.setDataFlow(flow);
        threat.setDescription(solvePlaceholders(rule.getDescription()));
        threat.setRemediation(solvePlaceholders(rule.getRemediation()));

        return threat;
    }

}
