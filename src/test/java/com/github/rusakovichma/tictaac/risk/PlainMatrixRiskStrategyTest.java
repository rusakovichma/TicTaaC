package com.github.rusakovichma.tictaac.risk;

import com.github.rusakovichma.tictaac.model.ThreatRisk;
import com.github.rusakovichma.tictaac.model.ThreatSeverity;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlainMatrixRiskStrategyTest {

    @Test
    void calc() {
        PlainMatrixRiskStrategy strategy = new PlainMatrixRiskStrategy();

        ThreatRisk risk = strategy.calc(ThreatSeverity.Medium, BoundaryCategory.corporateNetwork);
        assertTrue(risk == ThreatRisk.Medium);

        risk = strategy.calc(ThreatSeverity.Low, BoundaryCategory.globalNetwork);
        assertTrue(risk == ThreatRisk.Medium);

        risk = strategy.calc(ThreatSeverity.Low, BoundaryCategory.undefined);
        assertTrue(risk == ThreatRisk.Undefined);
    }
}