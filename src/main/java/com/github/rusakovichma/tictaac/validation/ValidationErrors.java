package com.github.rusakovichma.tictaac.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ValidationErrors {

    private List<String> errors = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

    public String getSummary() {
        return errors.stream()
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
