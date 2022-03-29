package com.github.rusakovichma.tictaac.model;

public enum ThreatRisk {
    Low,
    Medium,
    High;

    @Override
    public String toString() {
        return this.name();
    }
}
