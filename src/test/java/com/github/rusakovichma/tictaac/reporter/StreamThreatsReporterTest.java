package com.github.rusakovichma.tictaac.reporter;

import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatCategory;
import com.github.rusakovichma.tictaac.model.ThreatRisk;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationStatus;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class StreamThreatsReporterTest {

    private Collection<Threat> getThreats() {
        Collection<Threat> threats = new ArrayList<>();

        Threat threat1 = new Threat();
        threat1.setId("id1");
        threat1.setTitle("Threat 1");
        threat1.setRisk(ThreatRisk.High);
        threat1.setCategories(EnumSet.of(ThreatCategory.informationDisclosure));
        threat1.setDescription("description 1");
        threat1.setRemediation("remediation 1");
        threat1.setMitigationStatus(MitigationStatus.Accepted);
        threats.add(threat1);

        Threat threat2 = new Threat();
        threat2.setId("id2");
        threat2.setTitle("Threat 2");
        threat2.setCategories(EnumSet.of(ThreatCategory.denialOfService, ThreatCategory.tampering));
        threat2.setDescription("description 2");
        threat2.setRemediation("remediation 2");
        threat2.setMitigationStatus(MitigationStatus.NotMitigated);
        threats.add(threat2);

        return threats;
    }

    private ReportHeader getReportHeader() {
        return new ReportHeader(
                "Big Report header",
                "1.1.1",
                new Date());
    }

    @Test
    void publishJson() throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        StreamThreatsReporter reporter = new StreamThreatsReporter(stream, ReportFormat.json);

        reporter.publish(getReportHeader(), null, getThreats());

        assertTrue(!new String(stream.toByteArray()).isEmpty());
    }

    @Test
    void publishHtml() throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        StreamThreatsReporter reporter = new StreamThreatsReporter(stream, ReportFormat.html);

        reporter.publish(getReportHeader(), null, getThreats());

        assertTrue(!new String(stream.toByteArray()).isEmpty());
    }
}