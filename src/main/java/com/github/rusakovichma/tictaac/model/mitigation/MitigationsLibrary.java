package com.github.rusakovichma.tictaac.model.mitigation;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.RootCollection;

import java.util.LinkedList;

public class MitigationsLibrary {

    private String name;
    private String version;

    @RootCollection
    private LinkedList<ThreatRef> mitigated;

    @RootCollection
    private LinkedList<ThreatRef> accepted;

    @RootCollection
    private LinkedList<ThreatRef> avoided;

    @RootCollection
    private LinkedList<ThreatRef> transferred;

    public LinkedList<ThreatRef> getMitigated() {
        return mitigated;
    }

    public void setMitigated(LinkedList<ThreatRef> mitigated) {
        this.mitigated = mitigated;
    }

    public LinkedList<ThreatRef> getAccepted() {
        return accepted;
    }

    public void setAccepted(LinkedList<ThreatRef> accepted) {
        this.accepted = accepted;
    }

    public LinkedList<ThreatRef> getAvoided() {
        return avoided;
    }

    public void setAvoided(LinkedList<ThreatRef> avoided) {
        this.avoided = avoided;
    }

    public LinkedList<ThreatRef> getTransferred() {
        return transferred;
    }

    public void setTransferred(LinkedList<ThreatRef> transferred) {
        this.transferred = transferred;
    }
}
