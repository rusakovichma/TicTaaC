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
