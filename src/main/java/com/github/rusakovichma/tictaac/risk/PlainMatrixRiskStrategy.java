/*
 * This file is part of TicTaaC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
package com.github.rusakovichma.tictaac.risk;

import com.github.rusakovichma.tictaac.model.ThreatRisk;
import com.github.rusakovichma.tictaac.model.ThreatSeverity;
import com.github.rusakovichma.tictaac.model.risk.RiskFactor;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;

import java.util.HashMap;
import java.util.Map;

import static com.github.rusakovichma.tictaac.model.ThreatSeverity.*;
import static com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory.*;

public class PlainMatrixRiskStrategy implements RiskCalculationStrategy {

    private static final Map<RiskFactor, ThreatRisk> RISK_MATRIX = new HashMap<>();

    static {
        RISK_MATRIX.put(new RiskFactor(Low, globalNetwork), ThreatRisk.Medium);
        RISK_MATRIX.put(new RiskFactor(Medium, globalNetwork), ThreatRisk.High);
        RISK_MATRIX.put(new RiskFactor(High, globalNetwork), ThreatRisk.High);

        RISK_MATRIX.put(new RiskFactor(Low, demilitarizedZone), ThreatRisk.Medium);
        RISK_MATRIX.put(new RiskFactor(Medium, demilitarizedZone), ThreatRisk.Medium);
        RISK_MATRIX.put(new RiskFactor(High, demilitarizedZone), ThreatRisk.High);

        RISK_MATRIX.put(new RiskFactor(Low, corporateNetwork), ThreatRisk.Low);
        RISK_MATRIX.put(new RiskFactor(Medium, corporateNetwork), ThreatRisk.Medium);
        RISK_MATRIX.put(new RiskFactor(High, corporateNetwork), ThreatRisk.High);

        RISK_MATRIX.put(new RiskFactor(Low, closedPerimeter), ThreatRisk.Low);
        RISK_MATRIX.put(new RiskFactor(Medium, closedPerimeter), ThreatRisk.Low);
        RISK_MATRIX.put(new RiskFactor(High, closedPerimeter), ThreatRisk.Low);

        RISK_MATRIX.put(new RiskFactor(Low, undefined), ThreatRisk.Undefined);
        RISK_MATRIX.put(new RiskFactor(Medium, undefined), ThreatRisk.Undefined);
        RISK_MATRIX.put(new RiskFactor(High, undefined), ThreatRisk.Undefined);
    }

    @Override
    public ThreatRisk calc(ThreatSeverity impact, BoundaryCategory attackVector) {
        return RISK_MATRIX.get(new RiskFactor(impact, attackVector));
    }
}
