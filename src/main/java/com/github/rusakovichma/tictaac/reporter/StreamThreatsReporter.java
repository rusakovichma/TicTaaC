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
package com.github.rusakovichma.tictaac.reporter;

import com.github.rusakovichma.tictaac.model.OwaspCategory;
import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatCategory;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationStatus;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;
import com.github.rusakovichma.tictaac.reporter.analytics.ThreatAnalytics;
import com.github.rusakovichma.tictaac.reporter.chart.ChartPlotter;
import com.github.rusakovichma.tictaac.reporter.chart.XChartPlotter;
import com.github.rusakovichma.tictaac.reporter.dfd.DataFlowRender;
import com.github.rusakovichma.tictaac.reporter.dfd.GraphvizDataFlowRender;
import com.github.rusakovichma.tictaac.util.ResourceUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamThreatsReporter implements ThreatsReporter {

    private static final String TEMPLATE_DIR = "/report-templates";

    private final OutputStream outputStream;
    private final ReportFormat reportFormat;

    private DataFlowRender dataFlowRender = new GraphvizDataFlowRender();

    private String headerTemplate;
    private String entryTemplate;
    private String entrySeparator;
    private String footerTemplate;

    private final void init(ReportFormat format) {
        this.headerTemplate = ResourceUtil.readResource(
                String.format(TEMPLATE_DIR + "/%s/threat-model-report-header", format.name()));
        this.entryTemplate = ResourceUtil.readResource(
                String.format(TEMPLATE_DIR + "/%s/threat-model-report-entry", format.name()));
        this.entrySeparator = ResourceUtil.readResource(
                String.format(TEMPLATE_DIR + "/%s/threat-model-report-entry-separator", format.name()));
        this.footerTemplate = ResourceUtil.readResource(
                String.format(TEMPLATE_DIR + "/%s/threat-model-report-footer", format.name()));
    }

    public StreamThreatsReporter(OutputStream outputStream, ReportFormat reportFormat) {
        this.outputStream = outputStream;
        this.reportFormat = reportFormat;
        init(reportFormat);
    }

    private String getCategories(Threat threat) {
        StringBuilder categoriesBuilder = new StringBuilder();

        if (threat.getCategories() != null) {
            for (ThreatCategory category : threat.getCategories()) {
                categoriesBuilder.append(category.getDescription())
                        .append(", ");
            }
        }

        if (categoriesBuilder.length() == 0) {
            categoriesBuilder.append(ThreatCategory.Undefined.getDescription());
        } else {
            categoriesBuilder.delete(categoriesBuilder.length() - 2, categoriesBuilder.length());
        }

        return categoriesBuilder.toString();
    }

    private String getOwaspTop10Categories(Threat threat) {
        StringBuilder categoriesBuilder = new StringBuilder();

        if (threat.getOwasp() != null) {
            for (OwaspCategory category : threat.getOwasp()) {
                categoriesBuilder.append(category.getDescription())
                        .append(", ");
            }
        }

        if (categoriesBuilder.length() == 0) {
            categoriesBuilder.append(OwaspCategory.undefined.getDescription());
        } else {
            categoriesBuilder.delete(categoriesBuilder.length() - 2, categoriesBuilder.length());
        }

        return categoriesBuilder.toString();
    }

    private String[] getCharts(Collection<Threat> threats) {
        List<String> charts = new ArrayList<>();
        ThreatAnalytics analytics = new ThreatAnalytics();

        for (Threat threat : threats) {
            if (threat.getOwasp() != null) {
                for (OwaspCategory category : threat.getOwasp()) {
                    analytics.getByOwasp().get(category).incrementAndGet();
                }
            }

            if (threat.getCategories() != null) {
                for (ThreatCategory category : threat.getCategories()) {
                    analytics.getByStride().get(category).incrementAndGet();
                }
            }

            if (threat.getAttackVector() != null) {
                analytics.getByAttackVector().get(threat.getAttackVector()).incrementAndGet();
            }
            if (threat.getMitigationStatus() != null) {
                analytics.getByStatus().get(threat.getMitigationStatus()).incrementAndGet();
            }
        }

        ChartPlotter owaspChartPlotter = new XChartPlotter("By OWASP Top 10");
        for (Map.Entry<OwaspCategory, AtomicInteger> entry : analytics.getByOwasp().entrySet()) {
            owaspChartPlotter.addSeries(entry.getKey().getShortDescription(), entry.getValue().intValue());
        }
        charts.add(Base64.getEncoder().encodeToString(owaspChartPlotter.getImageBytes()));

        ChartPlotter strideChartPlotter = new XChartPlotter("By STRIDE");
        for (Map.Entry<ThreatCategory, AtomicInteger> entry : analytics.getByStride().entrySet()) {
            strideChartPlotter.addSeries(entry.getKey().getDescription(), entry.getValue().intValue());
        }
        charts.add(Base64.getEncoder().encodeToString(strideChartPlotter.getImageBytes()));

        ChartPlotter vectorChartPlotter = new XChartPlotter("By Attack Vector");
        for (Map.Entry<BoundaryCategory, AtomicInteger> entry : analytics.getByAttackVector().entrySet()) {
            vectorChartPlotter.addSeries(entry.getKey().getDescription(), entry.getValue().intValue());
        }
        charts.add(Base64.getEncoder().encodeToString(vectorChartPlotter.getImageBytes()));

        ChartPlotter statusChartPlotter = new XChartPlotter("By Mitigation Status");
        for (Map.Entry<MitigationStatus, AtomicInteger> entry : analytics.getByStatus().entrySet()) {
            statusChartPlotter.addSeries(entry.getKey().getDescription(), entry.getValue().intValue());
        }
        charts.add(Base64.getEncoder().encodeToString(statusChartPlotter.getImageBytes()));

        return charts.toArray(new String[charts.size()]);
    }

    private void writeModel(ReportHeader header, ThreatModel threatModel, Collection<Threat> threats)
            throws IOException {
        if (reportFormat == ReportFormat.html) {
            String[] charts = getCharts(threats);
            headerTemplate = headerTemplate.replace("%owasp-chart%", charts[0])
                    .replace("%stride-chart%", charts[1])
                    .replace("%vectors-chart%", charts[2])
                    .replace("%mitigations-chart%", charts[3])
                    .replace("%data-flow-diagram%",
                            Base64.getEncoder().encodeToString(
                                    dataFlowRender.createDataFlow(threatModel)));
        }

        outputStream.write(
                String.format(headerTemplate, header.getName(), header.getVersion(),
                                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()))
                        .getBytes()
        );

        ArrayList<Threat> threatList = new ArrayList<>(threats);
        for (int index = 0; index < threatList.size(); index++) {
            Threat threat = threatList.get(index);
            outputStream.write(
                    String.format(entryTemplate,
                            threat.getId(),
                            threat.getTitle(),
                            threat.getRisk().name(),
                            threat.getAttackVector().getDescription(),
                            getCategories(threat),
                            getOwaspTop10Categories(threat),
                            threat.getDescription(),
                            threat.getRemediation(),
                            threat.getMitigationStatus().getDescription()
                    ).getBytes()
            );

            if (index != threats.size() - 1) {
                outputStream.write(entrySeparator.getBytes());
            }
        }

        outputStream.write(footerTemplate.getBytes());
    }

    @Override
    public void publish(ReportHeader header, ThreatModel threatModel, Collection<Threat> threats)
            throws IOException {
        writeModel(header, threatModel, threats);
    }
}
