package com.github.rusakovichma.tictaac.risk;

import com.github.rusakovichma.tictaac.model.ThreatRisk;
import com.github.rusakovichma.tictaac.model.ThreatSeverity;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;

public interface RiskCalculationStrategy {

    public ThreatRisk calc(ThreatSeverity impact, BoundaryCategory attackVector);
}
