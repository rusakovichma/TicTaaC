package com.github.rusakovichma.tictaac.provider.model;

import com.github.rusakovichma.tictaac.mapper.ThreatModelMapper;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.provider.reader.Reader;
import com.github.rusakovichma.tictaac.provider.reader.UnifiedReader;

import java.util.HashMap;
import java.util.Map;

public class StandardThreatModelProvider implements ThreatModelProvider {

    private final String threatModelPath;
    private final Reader reader;

    private Map<String, String> getAccessParams(Map<String, String> params) {
        Map<String, String> accessParams = new HashMap<>();

        String accessUsername = accessParams.get("threatModelAccessUsername");
        if (accessUsername != null && !accessUsername.trim().isEmpty()) {
            accessParams.put("username", accessUsername);
        }

        String accessPassword = accessParams.get("threatModelAccessPassword");
        if (accessPassword != null && !accessPassword.trim().isEmpty()) {
            accessParams.put("password", accessPassword);
        }

        return accessParams;
    }

    public StandardThreatModelProvider(String threatModelPath, Map<String, String> params) {
        this.threatModelPath = threatModelPath;
        this.reader = new UnifiedReader(getAccessParams(params));
    }

    public StandardThreatModelProvider(String threatModelPath) {
        this.threatModelPath = threatModelPath;
        this.reader = new UnifiedReader();
    }

    @Override
    public ThreatModel getModel() {
        return new ThreatModelMapper(
                reader.read(threatModelPath))
                .getModel();
    }
}
