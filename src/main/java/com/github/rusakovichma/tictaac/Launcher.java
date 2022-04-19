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
package com.github.rusakovichma.tictaac;

import com.github.rusakovichma.tictaac.engine.StandardThreatEngine;
import com.github.rusakovichma.tictaac.engine.ThreatEngine;
import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.ThreatRisk;
import com.github.rusakovichma.tictaac.model.ThreatsCollection;
import com.github.rusakovichma.tictaac.model.exception.QualityGateFailed;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationStatus;
import com.github.rusakovichma.tictaac.provider.mitigation.*;
import com.github.rusakovichma.tictaac.provider.model.StandardThreatModelProvider;
import com.github.rusakovichma.tictaac.provider.model.ThreatModelProvider;
import com.github.rusakovichma.tictaac.provider.rules.StandardThreatRulesProvider;
import com.github.rusakovichma.tictaac.provider.rules.ThreatRulesProvider;
import com.github.rusakovichma.tictaac.reporter.*;
import com.github.rusakovichma.tictaac.util.ConsoleUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

public class Launcher {

    private static final ReportFormat DEFAULT_OUT_FORMAT = ReportFormat.html;
    private static final String DEFAULT_THREAT_LIBRARY = "classpath:/threats-library/default-threats-library.yml";

    private static ThreatRulesProvider getRulesProvider(Map<String, String> params) {
        String libraryPath = params.get("threatsLibrary");
        if (libraryPath == null) {
            libraryPath = DEFAULT_THREAT_LIBRARY;
        }
        return new StandardThreatRulesProvider(libraryPath, params);
    }

    private static ThreatModelProvider getThreatModel(Map<String, String> params) {
        String threatModelPath = params.get("threatModel");
        if (threatModelPath == null || threatModelPath.isEmpty()) {
            throw new IllegalStateException("Threat modeling file: '--threatModel %path_to_file%' parameters should be provided");
        }
        return new StandardThreatModelProvider(threatModelPath, params);
    }

    private static Mitigator getMitigator(Map<String, String> params) {
        String mitigationsPath = params.get("mitigations");
        if (mitigationsPath == null) {
            return new DullMitigator();
        }
        return new StandardMitigator(
                new StandardMitigationProvider(mitigationsPath, params)
                        .getMitigations());
    }

    private static ThreatEngine getThreatEngine(ThreatRulesProvider rulesProvider, Mitigator mitigator) {
        StandardThreatEngine threatEngine = new StandardThreatEngine(rulesProvider);
        threatEngine.setMitigator(mitigator);
        return threatEngine;
    }

    private static ThreatsReporter getThreatsReporter(Map<String, String> params) {
        String outPath = params.get("out");
        if (outPath == null || outPath.isEmpty()) {
            throw new IllegalStateException("Report output path: '--out %output_report_dir%' parameter should be provided");
        }
        ReportFormat outFormat = ReportFormat.fromString(params.get("outFormat"));
        if (outFormat == null) {
            outFormat = DEFAULT_OUT_FORMAT;
        }
        try {
            return new FileStreamThreatsReporter(outPath, outFormat);
        } catch (FileNotFoundException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static void checkQualityGate(Map<String, String> params, Collection<Threat> threats) {
        String failOnThreatRiskParam = params.get("failOnThreatRisk");
        if (failOnThreatRiskParam != null) {
            ThreatRisk qualityGate = ThreatRisk.fromString(failOnThreatRiskParam.trim());
            if (qualityGate != ThreatRisk.Undefined) {
                String notCompliantThreats = threats.stream()
                        .filter(threat -> threat.getRisk().getOrder() >= qualityGate.getOrder())
                        .filter(threat -> threat.getMitigationStatus() == MitigationStatus.NotMitigated)
                        .map(threat -> threat.getId())
                        .collect(Collectors.joining(", "));

                if (notCompliantThreats != null && !notCompliantThreats.isEmpty()) {
                    throw new QualityGateFailed(String.format("Non-compliant threats found [%s]", notCompliantThreats));
                }
            }
        }
    }

    public static void main(String[] args) {
        final Map<String, String> params = ConsoleUtil.getParamsMap(args);

        ThreatRulesProvider rulesProvider = getRulesProvider(params);
        Mitigator mitigator = getMitigator(params);

        ThreatEngine threatEngine = getThreatEngine(rulesProvider, mitigator);

        ThreatModelProvider threatModelProvider = getThreatModel(params);
        ThreatsCollection threats = threatEngine.generateThreats(threatModelProvider.getModel());

        try {
            getThreatsReporter(params).publish(
                    new ReportHeader(
                            threats.getName(), threats.getVersion(), new Date()
                    ),
                    threats.getThreats()
            );
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot write threat model report to the file [" + params.get("out") + "]", ex);
        }

        checkQualityGate(params, threats.getThreats());
    }

}
