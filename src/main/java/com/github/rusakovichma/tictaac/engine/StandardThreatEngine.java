package com.github.rusakovichma.tictaac.engine;

import com.github.rusakovichma.tictaac.engine.el.EvaluationContext;
import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.provider.ThreatProvider;

import java.util.Collection;
import java.util.stream.Collectors;

public class StandardThreatEngine implements ThreatEngine {

    private final ThreatProvider threatProvider;
    private final EvaluationContext evaluationContext;

    public StandardThreatEngine(ThreatProvider threatProvider, EvaluationContext evaluationContext) {
        this.threatProvider = threatProvider;
        this.evaluationContext = evaluationContext;
    }

    @Override
    public Collection<Threat> generateThreats(ThreatModel threatModel) {
        Collection<Threat> threatsLibrary = threatProvider.getThreatsLibrary()
                .getThreats();

        evaluationContext.eval(threatModel, threatsLibrary);

        return threatsLibrary.stream()
                .filter(threat -> threat.getDataFlow() != null)
                .collect(Collectors.toList());
    }
}
