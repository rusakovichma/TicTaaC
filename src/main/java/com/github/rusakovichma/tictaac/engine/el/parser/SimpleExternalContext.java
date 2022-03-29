package com.github.rusakovichma.tictaac.engine.el.parser;

import java.util.HashMap;
import java.util.Map;

public class SimpleExternalContext implements ExternalContext {

    private Map<String, Object> parameters = new HashMap<String, Object>();

    @Override
    public Object getParameter(String parameterName) {
        return parameters.get(parameterName);
    }

    public void addParameter(String name, Object value) {
        parameters.put(name, value);
    }

    public void addParameters(Map<String, Object> params) {
        parameters.putAll(params);
    }

    public void clear() {
        parameters.clear();
    }
}
