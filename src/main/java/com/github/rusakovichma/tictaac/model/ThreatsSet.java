package com.github.rusakovichma.tictaac.model;

import java.util.LinkedList;

public class ThreatsSet {

    private String name;
    private LinkedList<Threat> threats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Threat> getThreats() {
        return threats;
    }

    public void setThreats(LinkedList<Threat> threats) {
        this.threats = threats;
    }
}
