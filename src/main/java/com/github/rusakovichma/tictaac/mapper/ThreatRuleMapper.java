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
        threat.setOwasp(rule.getOwasp());
        threat.setDataFlow(flow);
        threat.setDescription(solvePlaceholders(rule.getDescription()));
        threat.setRemediation(solvePlaceholders(rule.getRemediation()));

        return threat;
    }

}
