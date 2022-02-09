package com.github.rusakovichma.tictaac.model;

public enum ThreatSeverity {
    Low,
    Medium,
    High;

    @Override
    public String toString() {
        return this.name();
    }
}
