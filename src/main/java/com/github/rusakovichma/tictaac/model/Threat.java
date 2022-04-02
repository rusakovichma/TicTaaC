package com.github.rusakovichma.tictaac.model;

import com.github.rusakovichma.tictaac.model.mitigation.MitigationStatus;
import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;
import com.github.rusakovichma.tictaac.util.StringUtils;

import java.util.EnumSet;
import java.util.Objects;

public class Threat {

    private String id;
    private String title;
    private ThreatRisk risk = ThreatRisk.Undefined;
    private EnumSet<ThreatCategory> categories;
    private MitigationStatus mitigationStatus = MitigationStatus.NotMitigated;
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

    public MitigationStatus getMitigationStatus() {
        return mitigationStatus;
    }

    public void setMitigationStatus(MitigationStatus mitigationStatus) {
        this.mitigationStatus = mitigationStatus;
    }

    public String calculateHash() {
        StringBuilder dump = new StringBuilder()
                .append(title)
                .append(risk.toString());

        if (categories != null) {
            for (ThreatCategory category : categories) {
                dump.append(category.toString());
            }
        }

        dump.append(description)
                .append(remediation);

        return StringUtils.sha1Hash(dump.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Threat threat = (Threat) o;
        return Objects.equals(id, threat.id) && Objects.equals(title, threat.title)
                && risk == threat.risk && Objects.equals(categories, threat.categories)
                && mitigationStatus == threat.mitigationStatus && Objects.equals(description, threat.description)
                && Objects.equals(remediation, threat.remediation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, risk, categories, mitigationStatus, description, remediation);
    }
}
