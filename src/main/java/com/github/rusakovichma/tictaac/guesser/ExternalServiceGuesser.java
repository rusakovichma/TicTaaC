package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

public class ExternalServiceGuesser extends ElementGuesser {

    private final static String[] SIGNS = new String[]{
            "external-service", "externalservice", "external-party",
            "external-third-party", "external-3rd-party"};

    @Override
    ElementType getGuessedType() {
        return ElementType.externalService;
    }

    @Override
    String[] getDesignatingString() {
        return SIGNS;
    }
}
