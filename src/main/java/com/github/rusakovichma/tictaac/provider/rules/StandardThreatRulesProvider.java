package com.github.rusakovichma.tictaac.provider.rules;

import com.github.rusakovichma.tictaac.mapper.ThreatsLibraryMapper;
import com.github.rusakovichma.tictaac.model.ThreatsLibrary;
import com.github.rusakovichma.tictaac.reader.Reader;
import com.github.rusakovichma.tictaac.reader.UnifiedReader;

import java.util.Map;

public class StandardThreatRulesProvider implements ThreatRulesProvider {

    private final String threatRulesPath;
    private final Reader reader;

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
