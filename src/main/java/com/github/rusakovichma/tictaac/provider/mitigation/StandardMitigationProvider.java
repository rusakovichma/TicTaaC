package com.github.rusakovichma.tictaac.provider.mitigation;

import com.github.rusakovichma.tictaac.mapper.MitigationsLibraryMapper;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationsLibrary;
import com.github.rusakovichma.tictaac.provider.reader.Reader;
import com.github.rusakovichma.tictaac.provider.reader.UnifiedReader;

import java.util.HashMap;
import java.util.Map;

public class StandardMitigationProvider implements MitigationLibraryProvider {

    private final String mitigationsLibraryPath;
    private final Reader reader;

    private Map<String, String> getAccessParams(Map<String, String> params) {
        Map<String, String> accessParams = new HashMap<>();

        String accessUsername = accessParams.get("mitigationsAccessUsername");
        if (accessUsername != null && !accessUsername.trim().isEmpty()) {
            accessParams.put("username", accessUsername);
        }

        String accessPassword = accessParams.get("mitigationsAccessPassword");
        if (accessPassword != null && !accessPassword.trim().isEmpty()) {
            accessParams.put("password", accessPassword);
        }

        return accessParams;
    }

    public StandardMitigationProvider(String mitigationsLibraryPath, Map<String, String> params) {
        this.mitigationsLibraryPath = mitigationsLibraryPath;
        this.reader = new UnifiedReader(getAccessParams(params));
    }

    public StandardMitigationProvider(String mitigationsLibraryPath) {
        this.mitigationsLibraryPath = mitigationsLibraryPath;
        this.reader = new UnifiedReader();
    }

    @Override
    public MitigationsLibrary getMitigations() {
        return new MitigationsLibraryMapper(
                reader.read(mitigationsLibraryPath))
                .getModel();
    }
}
