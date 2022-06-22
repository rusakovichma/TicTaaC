package com.github.rusakovichma.tictaac.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleUtilTest {

    @Test
    void getParams() {
        String[] params = {"--threatModel", "${WORKSPACE}/threat-model.yml", "--failOnThreatRisk", "High",
                "--threatsLibrary", "https://my-site.com/my-threats-library.yml",
                "--threatsLibraryAccessUsername", "username", "--threatsLibraryAccessPassword", "pass123456",
                "--mitigations", "\"${WORKSPACE}/mitigations.yml\"", "--outFormat", "html", "--out", "${WORKSPACE}/reports"};

        Map<String, List<String>> paramsMap = ConsoleUtil.getParamsMap(params);
        assertTrue(paramsMap.size() == 8);
    }

    @Test
    void getParamsWhitelistPass() {
        String[] params = {"--threatModel", "${WORKSPACE}/threat-model.yml", "--failOnThreatRisk", "High",
                "--threatsLibrary", "https://my-site.com/my-threats-library.yml",
                "--threatsLibraryAccessUsername", "username", "--threatsLibraryAccessPassword", "pass123456",
                "--mitigations", "\"${WORKSPACE}/mitigations.yml\"", "--outFormat", "html", "--out", "${WORKSPACE}/reports"};

        Map<String, List<String>> paramsMap = ConsoleUtil.getParamsMap(params);
        assertTrue(ConsoleUtil.hasOnlyAllowed(paramsMap));
    }

    @Test
    void getParamsWhitelistNotPassed() {
        String[] params = {"--threatModel", "${WORKSPACE}/threat-model.yml", "--failOnThreatRisk", "High",
                "--threatsLibrary", "https://my-site.com/my-threats-library.yml", "--someParam",
                "--threatsLibraryAccessUsername", "username", "--threatsLibraryAccessPassword", "pass123456",
                "--mitigations", "\"${WORKSPACE}/mitigations.yml\"", "--outFormat", "html", "--out", "${WORKSPACE}/reports"};

        Map<String, List<String>> paramsMap = ConsoleUtil.getParamsMap(params);
        assertFalse(ConsoleUtil.hasOnlyAllowed(paramsMap));
    }
}