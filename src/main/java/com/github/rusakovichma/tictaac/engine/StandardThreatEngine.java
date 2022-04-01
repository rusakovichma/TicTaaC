package com.github.rusakovichma.tictaac.engine;

import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.model.ThreatRule;
import com.github.rusakovichma.tictaac.provider.mitigation.DullMitigator;
import com.github.rusakovichma.tictaac.provider.mitigation.Mitigator;
import com.github.rusakovichma.tictaac.provider.rules.ThreatRulesProvider;

import java.util.Collection;

public class StandardThreatEngine implements ThreatEngine {

    private final ThreatRulesProvider threatRulesProvider;
    private final EngineContext engineContext;

    private Mitigator mitigator = new DullMitigator();

    public StandardThreatEngine(ThreatRulesProvider threatRulesProvider, EngineContext engineContext) {
        this.threatRulesProvider = threatRulesProvider;
        this.engineContext = engineContext;
    }

    @Override
    public Collection<Threat> generateThreats(ThreatModel threatModel) {
        Collection<ThreatRule> threatRules = threatRulesProvider.getThreatsLibrary()
                .getRules();

        Collection<Threat> threats = engineContext.eval(threatModel, threatRules);
        mitigator.setMitigationStrategy(threats);
        return threats;
    }

    public void setMitigator(Mitigator mitigator) {
        this.mitigator = mitigator;
    }
}
