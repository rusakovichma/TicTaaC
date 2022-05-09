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
