package com.github.rusakovichma.tictaac.provider.model;

import com.github.rusakovichma.tictaac.mapper.MitigationsLibraryMapper;
import com.github.rusakovichma.tictaac.mapper.ThreatModelMapper;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.provider.reader.Reader;
import com.github.rusakovichma.tictaac.provider.reader.UnifiedReader;

import java.util.Map;

public class StandardThreatModelProvider implements ThreatModelProvider {

    private final String threatModelPath;
    private final Reader reader;

    public StandardThreatModelProvider(String threatModelPath, Map<String, String> params) {
        this.threatModelPath = threatModelPath;
        this.reader = new UnifiedReader(params);
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
