package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.Arrays;
import java.util.List;

public class InternalServiceGuesser extends ElementGuesser {

    private final static List<String> SIGNS = Arrays.asList(new String[]{
            "internal-service", "internalservice", "internal-party",
            "internal-third-party", "internal-3rd-party"});

    @Override
    ElementType getGuessedType() {
        return ElementType.internalService;
    }

    @Override
    List<String> getDesignatingString() {
        return SIGNS;
    }

}
