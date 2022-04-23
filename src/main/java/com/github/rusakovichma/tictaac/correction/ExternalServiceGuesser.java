package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.Arrays;
import java.util.List;

public class ExternalServiceGuesser extends ElementGuesser {

    private final static List<String> SIGNS = Arrays.asList(new String[]{
            "external-service", "externalservice", "external-party",
            "external-third-party", "external-3rd-party"});

    @Override
    ElementType getGuessedType() {
        return ElementType.externalService;
    }

    @Override
    List<String> getDesignatingString() {
        return SIGNS;
    }
}
