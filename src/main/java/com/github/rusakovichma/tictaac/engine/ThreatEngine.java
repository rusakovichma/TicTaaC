package com.github.rusakovichma.tictaac.engine;

import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatModel;

import java.util.Collection;

public interface ThreatEngine {

    public Collection<Threat> generateThreats(ThreatModel threatModel);

}
