package com.github.rusakovichma.tictaac.provider.rules;

import com.github.rusakovichma.tictaac.mapper.ThreatsLibraryMapper;
import com.github.rusakovichma.tictaac.model.ThreatsLibrary;
import com.github.rusakovichma.tictaac.provider.reader.Reader;
import com.github.rusakovichma.tictaac.provider.reader.UnifiedReader;

import java.util.HashMap;
import java.util.Map;

public class StandardThreatRulesProvider implements ThreatRulesProvider {

    private final String threatRulesPath;
    private final Reader reader;

    private Map<String, String> getAccessParams(Map<String, String> params) {
        Map<String, String> accessParams = new HashMap<>();

        String accessUsername = accessParams.get("threatsLibraryAccessUsername");
        if (accessUsername != null && !accessUsername.trim().isEmpty()) {
            accessParams.put("username", accessUsername);
        }

        String accessPassword = accessParams.get("threatsLibraryAccessPassword");
        if (accessPassword != null && !accessPassword.trim().isEmpty()) {
            accessParams.put("password", accessPassword);
        }

        return accessParams;
    }

    public StandardThreatRulesProvider(String threatRulesPath, Map<String, String> params) {
        this.threatRulesPath = threatRulesPath;
        this.reader = new UnifiedReader(params);
    }

    @Override
    public ThreatsLibrary getThreatsLibrary() {
        return new ThreatsLibraryMapper(
                reader.read(threatRulesPath))
                .getModel();
    }
}
