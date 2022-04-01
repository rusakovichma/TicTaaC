package com.github.rusakovichma.tictaac.reader;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;

import java.util.Collections;
import java.util.Map;

public class UnifiedReader implements Reader {

    private final Map<String, String> parameters;

    public UnifiedReader(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public UnifiedReader() {
        this.parameters = Collections.EMPTY_MAP;
    }


    @Override
    public NodeTree read(String path) {
        if (path == null) {
            throw new IllegalArgumentException("File path parameter cannot be null");
        }

        if (path.toLowerCase().startsWith("http:") || path.toLowerCase().startsWith("https:")) {
            return new UrlReader(parameters).read(path);
        }

        if (path.toLowerCase().startsWith("classpath:")) {
            return new ClassPathReader().read(path);
        }

        return new ExternalReader().read(path);
    }
}
