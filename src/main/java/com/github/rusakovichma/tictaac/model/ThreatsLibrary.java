package com.github.rusakovichma.tictaac.model;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.RootCollection;

import java.util.LinkedList;

public class ThreatsLibrary {

    private String name;
    private String version;
    @RootCollection
    private LinkedList<ThreatRule> rules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<ThreatRule> getRules() {
        return rules;
    }

    public void setRules(LinkedList<ThreatRule> rules) {
        this.rules = rules;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
