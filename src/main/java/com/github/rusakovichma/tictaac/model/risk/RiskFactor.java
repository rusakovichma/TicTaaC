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
