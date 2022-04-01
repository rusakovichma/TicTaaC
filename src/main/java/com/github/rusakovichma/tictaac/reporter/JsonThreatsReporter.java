package com.github.rusakovichma.tictaac.reporter;

import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.util.ResourceUtil;

import java.util.Collection;

class JsonThreatsReporter implements ThreatsReporter {

    private String structureTemplate;
    private String entryTemplate;

    public void init() {
        this.structureTemplate = ResourceUtil.readResource("/report-templates/json/threat-json-structure");
        this.entryTemplate = ResourceUtil.readResource("/report-templates/json/threat-json-entry");
    }

    @Override
    public void publish(Collection<Threat> threats) {

    }
}
