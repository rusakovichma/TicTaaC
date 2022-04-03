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

import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.model.ThreatRule;
import com.github.rusakovichma.tictaac.model.ThreatsCollection;
import com.github.rusakovichma.tictaac.provider.mitigation.DullMitigator;
import com.github.rusakovichma.tictaac.provider.mitigation.Mitigator;
import com.github.rusakovichma.tictaac.provider.rules.ThreatRulesProvider;

import java.util.Collection;

public class StandardThreatEngine implements ThreatEngine {

    private final EngineContext engineContext = new StandardEngineContext();
    private final ThreatRulesProvider threatRulesProvider;

    private Mitigator mitigator = new DullMitigator();

    public StandardThreatEngine(ThreatRulesProvider threatRulesProvider) {
        this.threatRulesProvider = threatRulesProvider;
    }

    @Override
    public ThreatsCollection generateThreats(ThreatModel threatModel) {
        Collection<ThreatRule> threatRules = threatRulesProvider.getThreatsLibrary()
                .getRules();

        Collection<Threat> threats = engineContext.eval(threatModel, threatRules);
        mitigator.setMitigationStrategy(threats);

        final ThreatsCollection threatsCollection = new ThreatsCollection();
        threatsCollection.setThreats(threats);
        threatsCollection.setName(threatModel.getName());
        threatsCollection.setVersion(threatModel.getVersion());

        return threatsCollection;
    }

    public void setMitigator(Mitigator mitigator) {
        this.mitigator = mitigator;
    }
}
