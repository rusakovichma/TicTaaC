/*
 * This file is part of TicTaaC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
package com.github.rusakovichma.tictaac.model;

import com.github.rusakovichma.tictaac.model.mitigation.MitigationStatus;
import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;
import com.github.rusakovichma.tictaac.util.StringUtils;

import java.util.EnumSet;
import java.util.Objects;

public class Threat {

    private String id;
    private String title;
    private ThreatRisk risk = ThreatRisk.Undefined;
    private EnumSet<ThreatCategory> categories;
    private EnumSet<OwaspCategory> owasp;
    private MitigationStatus mitigationStatus = MitigationStatus.NotMitigated;
    private String description;
    private String remediation;

    private DataFlow dataFlow;
    private BoundaryCategory attackVector = BoundaryCategory.undefined;
    private String hashCached;

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

    public int getRiskPriority() {
        return risk.getOrder();
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

    public BoundaryCategory getAttackVector() {
        return attackVector;
    }

    public void setAttackVector(BoundaryCategory attackVector) {
        this.attackVector = attackVector;
    }

    public EnumSet<OwaspCategory> getOwasp() {
        return owasp;
    }

    public void setOwasp(EnumSet<OwaspCategory> owasp) {
        this.owasp = owasp;
    }

    public String calculateHash() {
        if (this.hashCached != null) {
            return this.hashCached;
        }

        StringBuilder dump = new StringBuilder()
                .append(title);

        if (categories != null) {
            for (ThreatCategory category : categories) {
                dump.append(category.toString());
            }
        }

        dump.append(description)
                .append(remediation);

        this.hashCached = StringUtils.sha1Hash(dump.toString());
        return this.hashCached;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Threat threat = (Threat) o;
        return Objects.equals(title, threat.title)
                && Objects.equals(categories, threat.categories)
                && Objects.equals(description, threat.description)
                && Objects.equals(remediation, threat.remediation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, categories, description, remediation);
    }
}
