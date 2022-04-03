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

import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatCategory;
import com.github.rusakovichma.tictaac.util.ResourceUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class StreamThreatsReporter implements ThreatsReporter {

    private final OutputStream outputStream;
    private final ReportFormat reportFormat;

    private String headerTemplate;
    private String entryTemplate;
    private String entrySeparator;
    private String footerTemplate;

    private final void init(ReportFormat format) {
        this.headerTemplate = ResourceUtil.readResource(
                String.format("/report-templates/%s/threat-model-report-header", format.name()));
        this.entryTemplate = ResourceUtil.readResource(
                String.format("/report-templates/%s/threat-model-report-entry", format.name()));
        this.entrySeparator = ResourceUtil.readResource(
                String.format("/report-templates/%s/threat-model-report-entry-separator", format.name()));
        this.footerTemplate = ResourceUtil.readResource(
                String.format("/report-templates/%s/threat-model-report-footer", format.name()));
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

    private void writeModel(ReportHeader header, Collection<Threat> threats)
            throws IOException {
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
                            getCategories(threat),
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
    public void publish(ReportHeader header, Collection<Threat> threats)
            throws IOException {
        writeModel(header, threats);
    }
}
