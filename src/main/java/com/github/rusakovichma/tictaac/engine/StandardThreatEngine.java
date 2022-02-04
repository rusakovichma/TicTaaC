package com.github.rusakovichma.tictaac.engine;

import com.github.rusakovichma.tictaac.engine.el.EngineContext;
import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.model.ThreatRule;
import com.github.rusakovichma.tictaac.provider.ThreatProvider;

import java.util.Collection;

public class StandardThreatEngine implements ThreatEngine {

    private final ThreatProvider threatProvider;
    private final EngineContext engineContext;

    public StandardThreatEngine(ThreatProvider threatProvider, EngineContext engineContext) {
        this.threatProvider = threatProvider;
        this.engineContext = engineContext;
    }

    @Override
    public Collection<Threat> generateThreats(ThreatModel threatModel) {
        Collection<ThreatRule> threatRules = threatProvider.getThreatsLibrary()
                .getRules();

        return engineContext.eval(threatModel, threatRules);
    }
}
