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
package com.github.rusakovichma.tictaac.reporter.analytics;

import com.github.rusakovichma.tictaac.model.OwaspCategory;
import com.github.rusakovichma.tictaac.model.ThreatCategory;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationStatus;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreatAnalytics {

    private Map<OwaspCategory, AtomicInteger> byOwasp = new LinkedHashMap<>();
    private Map<ThreatCategory, AtomicInteger> byStride = new LinkedHashMap<>();
    private Map<BoundaryCategory, AtomicInteger> byAttackVector = new LinkedHashMap<>();
    private Map<MitigationStatus, AtomicInteger> byStatus = new LinkedHashMap<>();

    private void init() {
        for (OwaspCategory owasp : OwaspCategory.values()) {
            byOwasp.put(owasp, new AtomicInteger(0));
        }

        for (ThreatCategory stride : ThreatCategory.values()) {
            byStride.put(stride, new AtomicInteger(0));
        }

        for (BoundaryCategory vector : BoundaryCategory.values()) {
            byAttackVector.put(vector, new AtomicInteger(0));
        }

        for (MitigationStatus status : MitigationStatus.values()) {
            byStatus.put(status, new AtomicInteger(0));
        }
    }

    public ThreatAnalytics() {
        init();
    }

    public Map<OwaspCategory, AtomicInteger> getByOwasp() {
        return byOwasp;
    }

    public Map<ThreatCategory, AtomicInteger> getByStride() {
        return byStride;
    }

    public Map<BoundaryCategory, AtomicInteger> getByAttackVector() {
        return byAttackVector;
    }

    public Map<MitigationStatus, AtomicInteger> getByStatus() {
        return byStatus;
    }
}
