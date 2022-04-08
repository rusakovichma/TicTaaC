package com.github.rusakovichma.tictaac.model;

import com.github.rusakovichma.tictaac.model.mitigation.MitigationStatus;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ThreatTest {

    @Test
    void treatsEmptyGrouping() {
        Collection<Threat> threats = Collections.EMPTY_LIST;

        Collection<Threat> threatsGrouped = threats.stream().collect(
                Collectors.groupingBy(Threat::calculateHash,
                        Collectors.collectingAndThen(
                                Collectors.reducing((Threat threatOne, Threat threatAnother) ->
                                        threatOne.getRiskPriority() > threatAnother.getRiskPriority() ? threatOne : threatAnother),
                                Optional::get)))
                .values();

        assertTrue(threatsGrouped != null);
    }

    private Collection<Threat> getThreatsWithDifferentRisks() {
        Collection<Threat> threats = new ArrayList<>();

        Threat threat1 = new Threat();
        threat1.setId("idX");
        threat1.setTitle("Threat X");
        threat1.setRisk(ThreatRisk.High);
        threat1.setCategories(EnumSet.of(ThreatCategory.informationDisclosure));
        threat1.setDescription("description X");
        threat1.setRemediation("remediation X");
        threat1.setMitigationStatus(MitigationStatus.Accepted);
        threats.add(threat1);

        Threat threat2 = new Threat();
        threat2.setId("idX");
        threat2.setTitle("Threat X");
        threat2.setRisk(ThreatRisk.Medium);
        threat2.setCategories(EnumSet.of(ThreatCategory.informationDisclosure));
        threat2.setDescription("description X");
        threat2.setRemediation("remediation X");
        threat2.setMitigationStatus(MitigationStatus.NotMitigated);
        threats.add(threat2);

        return threats;
    }

    @Test
    void treatsGrouping() {
        Collection<Threat> threats = getThreatsWithDifferentRisks();

        Collection<Threat> threatsGrouped = threats.stream().collect(
                Collectors.groupingBy(Threat::calculateHash,
                        Collectors.collectingAndThen(
                                Collectors.reducing((Threat threatOne, Threat threatAnother) ->
                                        threatOne.getRiskPriority() > threatAnother.getRiskPriority() ? threatOne : threatAnother),
                                Optional::get)))
                .values();

        assertTrue(threatsGrouped.size() == 1);
    }
}