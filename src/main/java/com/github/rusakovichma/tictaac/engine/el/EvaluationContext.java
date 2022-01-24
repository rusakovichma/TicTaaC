package com.github.rusakovichma.tictaac.engine.el;

import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.model.ThreatRule;

import java.util.Collection;

public interface EvaluationContext {

    public Collection<Threat> eval(ThreatModel threatModel, Collection<ThreatRule> rules);

}
