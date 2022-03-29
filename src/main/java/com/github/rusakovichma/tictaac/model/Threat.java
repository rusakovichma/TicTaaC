package com.github.rusakovichma.tictaac.model;

import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;

import java.util.EnumSet;

public class Threat {

    private String id;
    private String title;
    private ThreatRisk risk;
    private EnumSet<ThreatCategory> categories;
    private String description;
    private String remediation;

    private DataFlow dataFlow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EnumSet<ThreatCategory> getCategories() {
        return categories;
    }

    public void setCategories(EnumSet<ThreatCategory> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemediation() {
        return remediation;
    }

    public void setRemediation(String remediation) {
        this.remediation = remediation;
    }

    public DataFlow getDataFlow() {
        return dataFlow;
    }

    public void setDataFlow(DataFlow dataFlow) {
        this.dataFlow = dataFlow;
    }

    public ThreatRisk getRisk() {
        return risk;
    }

    public void setRisk(ThreatRisk risk) {
        this.risk = risk;
    }
}
