package com.github.rusakovichma.tictaac.model;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.RootCollection;

import java.util.LinkedList;

public class ThreatsSet {

    private String name;
    @RootCollection
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
