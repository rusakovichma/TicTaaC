package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

public class InternalServiceGuesser extends ElementGuesser {

    private final static String[] SIGNS = new String[]{
            "internal-service", "internalservice", "internal-party",
            "internal-third-party", "internal-3rd-party"};

    @Override
    ElementType getGuessedType() {
        return ElementType.internalService;
    }

    @Override
    String[] getDesignatingString() {
        return SIGNS;
    }

}
