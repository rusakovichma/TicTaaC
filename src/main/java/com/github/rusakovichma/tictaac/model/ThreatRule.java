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

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Id;

import java.util.EnumSet;

public class ThreatRule {

    @Id
    private String id;
    private String title;
    private ThreatSeverity severity;
    private EnumSet<ThreatCategory> categories;
    private EnumSet<OwaspCategory> owasp;
    private String expression;
    private String exclude;
    private String description;
    private String remediation;

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

    public ThreatSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(ThreatSeverity severity) {
        this.severity = severity;
    }

    public EnumSet<ThreatCategory> getCategories() {
        return categories;
    }

    public EnumSet<OwaspCategory> getOwasp() {
        return owasp;
    }

    public void setOwasp(EnumSet<OwaspCategory> owasp) {
        this.owasp = owasp;
    }

    public void setCategories(EnumSet<ThreatCategory> categories) {
        this.categories = categories;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
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
}
