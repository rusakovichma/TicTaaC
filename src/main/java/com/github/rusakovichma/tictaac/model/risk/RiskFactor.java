package com.github.rusakovichma.tictaac.model.risk;

import com.github.rusakovichma.tictaac.model.ThreatSeverity;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;

import java.util.Objects;

public class RiskFactor {

    private final ThreatSeverity impact;
    private final BoundaryCategory attackVector;

    public RiskFactor(ThreatSeverity impact, BoundaryCategory attackVector) {
        this.impact = impact;
        this.attackVector = attackVector;
    }

    public ThreatSeverity getImpact() {
        return impact;
    }

    public BoundaryCategory getAttackVector() {
        return attackVector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskFactor that = (RiskFactor) o;
        return impact == that.impact && attackVector == that.attackVector;
    }

    @Override
    public int hashCode() {
        return Objects.hash(impact, attackVector);
    }
}
