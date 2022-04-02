package com.github.rusakovichma.tictaac.model;

public enum ThreatCategory {
    spoofing("Spoofing"),
    tampering("Tampering"),
    repudiation("Repudiation"),
    informationDisclosure("Information Disclosure"),
    denialOfService("Denial of Service"),
    elevationOfPrivilege("Elevation of Privilege"),
    Undefined("Undefined");

    private final String description;

    ThreatCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
