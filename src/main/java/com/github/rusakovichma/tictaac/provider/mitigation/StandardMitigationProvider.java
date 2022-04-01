package com.github.rusakovichma.tictaac.provider.mitigation;

import com.github.rusakovichma.tictaac.mapper.MitigationsLibraryMapper;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationsLibrary;
import com.github.rusakovichma.tictaac.reader.Reader;
import com.github.rusakovichma.tictaac.reader.UnifiedReader;

import java.util.Map;

public class StandardMitigationProvider implements MitigationLibraryProvider {

    private final String mitigationsLibraryPath;
    private final Reader reader;

    public StandardMitigationProvider(String mitigationsLibraryPath, Map<String, String> params) {
        this.mitigationsLibraryPath = mitigationsLibraryPath;
        this.reader = new UnifiedReader(params);
    }

    @Override
    public MitigationsLibrary getMitigations() {
        return new MitigationsLibraryMapper(
                reader.read(mitigationsLibraryPath))
                .getModel();
    }
}
