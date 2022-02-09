package com.github.rusakovichma.tictaac.model;

public enum ThreatCategory {
    spoofing,
    tampering,
    repudiation,
    informationDisclosure,
    denialOfService,
    elevationOfPrivilege;

    @Override
    public String toString() {
        return this.name();
    }
}
