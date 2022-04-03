package com.github.rusakovichma.tictaac.engine;

import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.model.ThreatsCollection;

public interface ThreatEngine {

    public ThreatsCollection generateThreats(ThreatModel threatModel);

}
