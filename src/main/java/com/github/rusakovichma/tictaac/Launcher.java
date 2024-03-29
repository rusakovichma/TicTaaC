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
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.model.ThreatRisk;
import com.github.rusakovichma.tictaac.model.ThreatsCollection;
import com.github.rusakovichma.tictaac.model.exception.QualityGateFailed;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationStatus;
import com.github.rusakovichma.tictaac.provider.mitigation.*;
import com.github.rusakovichma.tictaac.provider.model.StandardThreatModelProvider;
import com.github.rusakovichma.tictaac.provider.model.ThreatModelProvider;
import com.github.rusakovichma.tictaac.provider.reader.ThreatModelFilter;
import com.github.rusakovichma.tictaac.provider.rules.StandardThreatRulesProvider;
import com.github.rusakovichma.tictaac.provider.rules.ThreatRulesProvider;
import com.github.rusakovichma.tictaac.reporter.*;
import com.github.rusakovichma.tictaac.util.ConsoleUtil;
import com.github.rusakovichma.tictaac.util.FileUtil;
import com.github.rusakovichma.tictaac.util.ResourceUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Launcher {

    private static final String DEFAULT_REPORT_NAME = "threat-model-report";
    private static final String OUT_PATH_DEFAULT = ".";
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

        String threatModelPath = params.get("threatModel");
        if (threatModelPath != null || !threatModelPath.isEmpty()) {
            String threatModelDir = FileUtil.getParentFolderFromFilePath(threatModelPath);
            String threatModelName = FileUtil.getFilenameWithoutExtensionFromPath(threatModelPath);

            File threatModelMitigationsFile = new File(
                    threatModelDir + File.separator + threatModelName + "-mitigations.yml");
            if (threatModelMitigationsFile.exists()) {
                mitigationsPath = threatModelMitigationsFile.getAbsolutePath();
            }
        }

        if (mitigationsPath != null && !mitigationsPath.isEmpty()) {
            File mitigationsFile = new File(mitigationsPath);
            if (mitigationsFile.exists() && mitigationsFile.isDirectory()) {
                if (threatModelPath != null || !threatModelPath.isEmpty()) {
                    String threatModelName = FileUtil.getFilenameWithoutExtensionFromPath(threatModelPath);

                    File threatModelMitigationsFile = new File(
                            mitigationsFile.getAbsolutePath() + File.separator + threatModelName + "-mitigations.yml");
                    if (threatModelMitigationsFile.exists()) {
                        mitigationsPath = threatModelMitigationsFile.getAbsolutePath();
                    } else {
                        mitigationsPath = null;
                    }
                }
            }
        }

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
            outPath = OUT_PATH_DEFAULT;
        }
        ReportFormat outFormat = ReportFormat.fromString(params.get("outFormat"));
        if (outFormat == null) {
            outFormat = DEFAULT_OUT_FORMAT;
        }

        String reportName = DEFAULT_REPORT_NAME;
        String threatModelPath = params.get("threatModel");
        if (threatModelPath != null || !threatModelPath.isEmpty()) {
            reportName = FileUtil.getFilenameWithoutExtensionFromPath(threatModelPath);
        }

        try {
            return new FileStreamThreatsReporter(outPath, reportName, outFormat);
        } catch (FileNotFoundException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static void checkQualityGate(Map<String, List<String>> params, Map<String, Collection<Threat>> threats) {
        List<String> failOnThreatRiskParam = params.get("failOnThreatRisk");
        if (failOnThreatRiskParam != null && failOnThreatRiskParam.size() != 0) {
            ThreatRisk qualityGate = ThreatRisk.fromString(failOnThreatRiskParam.get(0).trim());
            if (qualityGate != ThreatRisk.Undefined) {
                StringBuilder nonComplianceAccumulator = new StringBuilder();

                for (Map.Entry<String, Collection<Threat>> entry : threats.entrySet()) {
                    String notCompliantThreats = entry.getValue().stream()
                            .filter(threat -> threat.getRisk().getOrder() >= qualityGate.getOrder())
                            .filter(threat -> threat.getMitigationStatus() == MitigationStatus.NotMitigated)
                            .map(threat -> threat.getId())
                            .collect(Collectors.joining(", "));

                    if (notCompliantThreats != null && !notCompliantThreats.isEmpty()) {
                        nonComplianceAccumulator.append(String.format("%s: [%s]", entry.getKey(), notCompliantThreats))
                                .append(System.lineSeparator());
                    }
                }

                if (nonComplianceAccumulator.length() != 0) {
                    throw new QualityGateFailed(String.format("Non-compliant threats found: ",
                            nonComplianceAccumulator.toString()));
                }
            }
        }
    }

    public static void main(String[] args) {
        final Map<String, List<String>> multipleValueParams = ConsoleUtil.getParamsMap(args);
        if (multipleValueParams.isEmpty() || !ConsoleUtil.hasOnlyAllowed(multipleValueParams)
                || multipleValueParams.containsKey("help") || multipleValueParams.containsKey("-h")) {
            System.out.println(ResourceUtil.readResource("/help-info"));
            System.exit(0);
        }

        if (multipleValueParams.containsKey("version") || multipleValueParams.containsKey("-v")) {
            System.out.println(ResourceUtil.readResource("/version"));
            System.exit(0);
        }

        List<String> threatModelsParams = multipleValueParams.get("threatModel");

        if (threatModelsParams == null || threatModelsParams.isEmpty()) {
            throw new IllegalStateException("Threat modeling files: '--threatModel %path_to_files_or_folder%' parameters should be provided");
        }

        List<String> threatModelsFilesOnlyParams = FileUtil.extractFiles(threatModelsParams, new ThreatModelFilter());
        Map<String, Collection<Threat>> modelThreats = new LinkedHashMap<>();

        for (String threatModelsParam : threatModelsFilesOnlyParams) {
            Map<String, String> singleValueParams = ConsoleUtil.copySingleValueParamsWithDefinedArg(multipleValueParams,
                    "threatModel", threatModelsParam);

            ThreatRulesProvider rulesProvider = getRulesProvider(singleValueParams);
            Mitigator mitigator = getMitigator(singleValueParams);

            ThreatEngine threatEngine = getThreatEngine(rulesProvider, mitigator);

            ThreatModelProvider threatModelProvider = getThreatModel(singleValueParams);

            ThreatModel threatModel = threatModelProvider.getModel();
            ThreatsCollection threats = threatEngine.generateThreats(threatModel);

            modelThreats.put(threatModelsParam, threats.getThreats());

            try {
                getThreatsReporter(singleValueParams).publish(
                        new ReportHeader(
                                threats.getName(), threats.getVersion(), new Date()
                        ),
                        threatModel,
                        threats.getThreats()
                );
            } catch (IOException ex) {
                throw new IllegalStateException("Cannot write threat model report to the path [" + singleValueParams.get("out") + "]", ex);
            }
        }

        checkQualityGate(multipleValueParams, modelThreats);
    }

}
