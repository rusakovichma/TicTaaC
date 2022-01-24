package com.github.rusakovichma.tictaac.provider;

import com.github.rusakovichma.tictaac.model.ThreatRule;
import com.github.rusakovichma.tictaac.model.ThreatsLibrary;

import java.util.LinkedList;

public class CompositeThreatProvider implements ThreatProvider {

    private static final String PROVIDER_NAME = "Composite threat provider";
    private static final String VERSION = "1.0";

    private ThreatProvider[] threatProviders;

    public CompositeThreatProvider(ThreatProvider... threatProviders) {
        this.threatProviders = threatProviders;
    }

    @Override
    public ThreatsLibrary getThreatsLibrary() {
        LinkedList<ThreatRule> allRules = new LinkedList<>();

        for (ThreatProvider threatProvider : threatProviders) {
            allRules.addAll(threatProvider.getThreatsLibrary().getRules());
        }

        ThreatsLibrary compositeLibrary = new ThreatsLibrary();
        compositeLibrary.setName(PROVIDER_NAME);
        compositeLibrary.setVersion(VERSION);
        compositeLibrary.setRules(allRules);

        return compositeLibrary;
    }
}
