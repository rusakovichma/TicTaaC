package com.github.rusakovichma.tictaac.model.mitigation;

public enum MitigationStatus {
    Mitigated("Mitigated"),
    Accepted("Accepted"),
    Transferred("Transferred"),
    Avoided("Avoided"),
    NotMitigated("Not Mitigated");

    private final String description;

    MitigationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
