package com.github.rusakovichma.tictaac.model;

import java.util.Collection;

public class ThreatsCollection {

    private String name;
    private String version;

    private Collection<Threat> threats;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Collection<Threat> getThreats() {
        return threats;
    }

    public void setThreats(Collection<Threat> threats) {
        this.threats = threats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
